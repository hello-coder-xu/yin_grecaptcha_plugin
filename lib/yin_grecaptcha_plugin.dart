import 'dart:async';

import 'package:flutter/services.dart';

class YinGrecaptchaPlugin {
  static const MethodChannel _channel = const MethodChannel('yin_grecaptcha_plugin');

  static Future<String> verify(String apiKey) async {
    final String token = await _channel.invokeMethod('verify', {"apiKey": apiKey});
    return token;
  }
}
