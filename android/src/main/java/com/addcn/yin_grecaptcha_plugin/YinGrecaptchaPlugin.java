package com.addcn.yin_grecaptcha_plugin;

import android.text.TextUtils;
import io.flutter.plugin.common.MethodCall;
import io.flutter.plugin.common.MethodChannel;
import io.flutter.plugin.common.MethodChannel.MethodCallHandler;
import io.flutter.plugin.common.MethodChannel.Result;
import io.flutter.plugin.common.PluginRegistry.Registrar;


import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.CommonStatusCodes;
import com.google.android.gms.safetynet.SafetyNet;
import com.google.android.gms.safetynet.SafetyNetApi;
import com.google.android.gms.safetynet.SafetyNetClient;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;

import android.support.annotation.NonNull;

/**
 * YinGrecaptchaPlugin
 */
public class YinGrecaptchaPlugin implements MethodCallHandler {
    private static final String TAG = "yin_grecaptcha";

    private Registrar registrar;

    public YinGrecaptchaPlugin(Registrar registrar) {
        this.registrar = registrar;
    }

    /**
     * Plugin registration.
     */
    public static void registerWith(Registrar registrar) {
        final MethodChannel channel = new MethodChannel(registrar.messenger(),
                "yin_grecaptcha_plugin");
        channel.setMethodCallHandler(new YinGrecaptchaPlugin(registrar));
    }

    @Override
    public void onMethodCall(MethodCall call, final Result result) {
        if (call.method.equals("verify")) {
            String apiKey = call.argument("apiKey");
            if (!TextUtils.isEmpty(apiKey)) {
                SafetyNetClient safetyNetClient = SafetyNet.getClient(registrar.activity());

                final Task<SafetyNetApi.RecaptchaTokenResponse> task =
                        safetyNetClient.verifyWithRecaptcha(apiKey);
                task.addOnSuccessListener(registrar.activity(),
                        new OnSuccessListener<SafetyNetApi.RecaptchaTokenResponse>() {
                            @Override
                            public void onSuccess(SafetyNetApi.RecaptchaTokenResponse response) {
                                String userResponseToken = response.getTokenResult();
                                if (!TextUtils.isEmpty(userResponseToken)) {
                                    result.success(userResponseToken);
                                    System.out.println("test =" + userResponseToken);
                                }
                            }
                        });
                task.addOnFailureListener(registrar.activity(), new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        if (e instanceof ApiException) {
                            ApiException apiException = (ApiException) e;
                            int statusCode = apiException.getStatusCode();
                            result.error(TAG, CommonStatusCodes.getStatusCodeString(statusCode),
                                    null);
                            System.out.println("Error: " + CommonStatusCodes.getStatusCodeString(statusCode));
                        } else {
                            result.error(TAG, e.getMessage(), null);
                            System.out.println("Error: " + e.getMessage());
                        }
                    }
                });
            } else {
                result.error(TAG, "apiKey is null", null);
            }
        } else {
            result.notImplemented();
        }
    }
}
