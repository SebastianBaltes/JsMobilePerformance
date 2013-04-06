//
//  ManyObjectsViewController.m
//  PerftestsIOS
//
//  Created by Administrator on 06.04.13.
//  Copyright (c) 2013 ObjectCode GmbH. All rights reserved.
//

#import "ManyObjectsViewController.h"
#import "StopWatch.h"
#import "RandomValues.h"
#import "Shop.h"

@interface ManyObjectsViewController ()

@end

@implementation ManyObjectsViewController

RandomValues* rnd;

- (void)performAction
{
    [self log:@"create 200 Items x 10 Parts x 10 Properties + 200 Users"];
    StopWatch *w = [StopWatch create];
    rnd = [RandomValues create];
    Shop *shop = [Shop create:rnd];
    [self log:[w stop]];
    [self log:[NSString stringWithFormat:@"items: %d",[[shop items] count]]];
}


@end
