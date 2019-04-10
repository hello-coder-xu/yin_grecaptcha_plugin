#import "YinGrecaptchaPlugin.h"
#import <yin_grecaptcha_plugin/yin_grecaptcha_plugin-Swift.h>

@implementation YinGrecaptchaPlugin
+ (void)registerWithRegistrar:(NSObject<FlutterPluginRegistrar>*)registrar {
  [SwiftYinGrecaptchaPlugin registerWithRegistrar:registrar];
}
@end
