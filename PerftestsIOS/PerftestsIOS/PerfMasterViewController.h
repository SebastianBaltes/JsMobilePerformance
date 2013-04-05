//
//  PerfMasterViewController.h
//  PerftestsIOS
//
//  Created by Administrator on 05.04.13.
//  Copyright (c) 2013 ObjectCode GmbH. All rights reserved.
//

#import <UIKit/UIKit.h>

@class PerfDetailViewController;

@interface PerfMasterViewController : UITableViewController

@property (strong, nonatomic) PerfDetailViewController *detailViewController;

@end
