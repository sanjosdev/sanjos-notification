import 'dart:async';

import 'package:flutter/foundation.dart';
import 'package:flutter/services.dart';

class SanjosNotification {
  static const MethodChannel _channel =
      const MethodChannel('sanjos_notification');

  static Future<void> showBasicNotification({@required String title,@required  String content}) async {

    // TODO : Add More Paramater For Notification Utility...

    final Map<String, dynamic> params = <String, dynamic> {
      'title': title,
      'content': content,
    };

    print('Calling Basic Notifcation');

    await _channel.invokeMethod('showBasicNotification', params);
  }

  static Future<void> showReplyNotification({@required String title,@required  String content}) async {

    final Map<String, dynamic> params = <String, dynamic> {
      'title': title,
      'content': content,
    };

    print('Calling Basic Notifcation');

    await _channel.invokeMethod('showReplyNotification', params);

  }

}
