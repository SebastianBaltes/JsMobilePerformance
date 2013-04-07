//
//  PerfAppDelegate.h
//  PerftestsIOS
//
//  Created by Administrator on 05.04.13.
//  Copyright (c) 2013 ObjectCode GmbH. All rights reserved.
//

#import <UIKit/UIKit.h>

@interface PerfAppDelegate : UIResponder <UIApplicationDelegate>

@property (strong, nonatomic) UIWindow *window;
@property (strong, nonatomic) NSString *allogs;

-(void)log:(NSString*)message;
+(PerfAppDelegate*)get;


@end
