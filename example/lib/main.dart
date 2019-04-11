import 'package:flutter/material.dart';
import 'dart:async';

import 'package:flutter/services.dart';
import 'package:yin_grecaptcha_plugin/yin_grecaptcha_plugin.dart';

void main() => runApp(MyApp());

class MyApp extends StatefulWidget {
  @override
  _MyAppState createState() => _MyAppState();
}

class _MyAppState extends State<MyApp> {
  String _token = 'Unknown';

  @override
  void initState() {
    super.initState();
  }

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

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      home: Scaffold(
        appBar: AppBar(
          title: const Text('Plugin example app'),
        ),
        body: new Padding(
          padding: EdgeInsets.all(30),
          child: new Column(
            mainAxisAlignment: MainAxisAlignment.center,
            children: <Widget>[
              Text('token: $_token\n'),
              RaisedButton(
                onPressed: getToken,
                child: new Text('获取'),
              )
            ],
          ),
        ),
      ),
    );
  }
}
