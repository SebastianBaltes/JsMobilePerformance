//
//  PerfLoggingViewController.m
//  PerftestsIOS
//
//  Created by Administrator on 05.04.13.
//  Copyright (c) 2013 ObjectCode GmbH. All rights reserved.
//

#import "PerfLoggingViewController.h"
#import "PerfAppDelegate.h"

@interface PerfLoggingViewController ()
@end

@implementation PerfLoggingViewController

@synthesize logView;

- (void)viewDidLoad
{
    [super viewDidLoad];
	// Do any additional setup after loading the view, typically from a nib.
    [self performAction];
}


- (void)performAction
{
}

- (void)log: (NSString *) message
{
    self.logView.text = [self.logView.text stringByAppendingFormat:@"%@\n",message];
    [[PerfAppDelegate get] log:message];
}
- (void)didReceiveMemoryWarning
{
    [super didReceiveMemoryWarning];
    // Dispose of any resources that can be recreated.
}

@end
