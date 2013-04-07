//
//  PerfMasterViewController.h
//  PerftestsIOS
//
//  Created by Administrator on 05.04.13.
//  Copyright (c) 2013 ObjectCode GmbH. All rights reserved.
//

#import <UIKit/UIKit.h>
#import <MessageUI/MessageUI.h>

@class PerfDetailViewController;

@interface PerfMasterViewController : UITableViewController <MFMailComposeViewControllerDelegate>

@property (strong, nonatomic) PerfDetailViewController *detailViewController;

- (IBAction)openMail:(id)sender;

@end
