//
//  PerfLoggingViewController.h
//  PerftestsIOS
//
//  Created by Administrator on 05.04.13.
//  Copyright (c) 2013 ObjectCode GmbH. All rights reserved.
//

#import <UIKit/UIKit.h>

@interface PerfLoggingViewController : UIViewController <UISplitViewControllerDelegate>

@property (strong, nonatomic) IBOutlet UITextView *logView;

- (void)log: (NSString *) message;

@end
