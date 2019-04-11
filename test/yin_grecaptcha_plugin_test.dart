import 'package:flutter/services.dart';
import 'package:flutter_test/flutter_test.dart';
import 'package:yin_grecaptcha_plugin/yin_grecaptcha_plugin.dart';

void main() {
  const MethodChannel channel = MethodChannel('yin_grecaptcha_plugin');

  setUp(() {
    channel.setMockMethodCallHandler((MethodCall methodCall) async {
      return '42';
    });
  });

  tearDown(() {
    channel.setMockMethodCallHandler(null);
  });

  test('getPlatformVersion', () async {
    expect(await YinGrecaptchaPlugin.verify("6LeXVJ0UAAAAACGNen5nViphlEOmLx7sULKu-RB0"), '42');
  });
}
