#import "SanjosNotificationPlugin.h"
#if __has_include(<sanjos_notification/sanjos_notification-Swift.h>)
#import <sanjos_notification/sanjos_notification-Swift.h>
#else
// Support project import fallback if the generated compatibility header
// is not copied when this plugin is created as a library.
// https://forums.swift.org/t/swift-static-libraries-dont-copy-generated-objective-c-header/19816
#import "sanjos_notification-Swift.h"
#endif

@implementation SanjosNotificationPlugin
+ (void)registerWithRegistrar:(NSObject<FlutterPluginRegistrar>*)registrar {
  [SwiftSanjosNotificationPlugin registerWithRegistrar:registrar];
}
@end
