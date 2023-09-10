import 'package:flutter/services.dart';

class MyModule {
  static const platform = MethodChannel('samples.flutter.dev/battery');

  static Future<String> getMessageFromNative() async {
    try {
      final String message = await platform.invokeMethod('getMessage');
      return message;
    } on PlatformException catch (e) {
      print("Error: ${e.message}");
      return '';
    }
  }
}
