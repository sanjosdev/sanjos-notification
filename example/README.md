## Example : 

```
    import 'package:sanjos_notification/sanjos_notification.dart';

    // Example of calling basic notification

    SanjosNotification.showBasicNotification(
        title: 'Santri Njoso',
        content: 'Santri Kok Ngoding..',
    ).then((value) => print('Oke')).catchError((e) => print('ERROR : $e'));

```
