[![License: GPL v3](https://img.shields.io/badge/License-GPLv3-blue.svg)](https://www.gnu.org/licenses/gpl-3.0)


# Sanjos Notification

a simple and sophisticated notification plugin from 'Sanjos Developer'. 

## Support Platfrom

- Android API 26+  - Suggested for use this minimum because some utility is under development

- IOS -  Still looking for IOS Maintainer

## Features : 

- [X] Basic Notification - Just title and content
- [X] Reply Notification - Just displaying notification but reply callback still under development

## Soon : 

- [ ] Support Android Notification Style Guide
     - [ ] Expandable notification with image or text
     - [ ] Show progress notification, eg:download
     - [ ] Create group notifications
     - [ ] Notification Badge

## Example : 

```
    import 'package:sanjos_notification/sanjos_notification.dart';

    // Example of calling basic notification

    SanjosNotification.showBasicNotification(
        title: 'Santri Njoso',
        content: 'Santri Kok Ngoding..',
    ).then((value) => print('Oke')).catchError((e) => print('ERROR : $e'));

```

## Notes : 

- Adding some parameter for more costumizeable notification


