import 'package:flutter/material.dart';
import 'package:sanjos_notification/sanjos_notification.dart';

void main() {
  runApp(MyApp());
}

class MyApp extends StatefulWidget {
  @override
  _MyAppState createState() => _MyAppState();
}

class _MyAppState extends State<MyApp> {
  @override
  void initState() {
    super.initState();
  }

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      home: Scaffold(
        appBar: AppBar(
          title: const Text('Sanjos Developer'),
        ),
        body: Center(
          child: Column(
            mainAxisAlignment: MainAxisAlignment.center,
            children: [
              RaisedButton(
                child: Text('Show Basic Notification'),
                onPressed: () {
                  SanjosNotification.showBasicNotification(
                    title: 'Santri Njoso',
                    content: 'Santri Kok Ngoding..',
                  ).then((value) => print('Oke')).catchError((e) => print('ERROR : $e'));
                },
              ),
              SizedBox(height: 8),
              RaisedButton(
                child: Text('Show Reply Notification'),
                onPressed: () {
                  SanjosNotification.showReplyNotification(
                    title: 'Santri Njoso',
                    content: 'Santri Kok Ngoding..',
                  ).then((value) => print('Oke')).catchError(
                        (e) => print('ERROR : $e'),
                      );
                },
              )
            ],
          ),
        ),
      ),
    );
  }
}
