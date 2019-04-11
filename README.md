# yin_grecaptcha_plugin

support Google ReCAPTCHA V2 (not V3)

get ReCAPTCHA V2 tokenResult

## Getting Started


how to use

import package
```
  dependencies:
  	yin_grecaptcha_plugin: ^0.0.1
```

code
```
  import 'package:yin_grecaptcha_plugin/yin_grecaptcha_plugin.dart';
```
```
  Future<void> getToken() async {
    String token;
    try {
      token = await YinGrecaptchaPlugin.verify("6LeXVJ0UAAAAACGNen5nViphlEOmLx7sULKu-RB0");
    } on PlatformException {
      token = 'Failed to get token.';
    }

    if (!mounted) return;

    print("token =" + token);
    setState(() {
      _token = token;
    });
  }
```
That's it!