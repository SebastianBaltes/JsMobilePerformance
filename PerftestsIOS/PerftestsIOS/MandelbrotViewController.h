//
//  MandelbrotViewController.h
//  PerftestsIOS
//
//  Created by Administrator on 06.04.13.
//  Copyright (c) 2013 ObjectCode GmbH. All rights reserved.
//

#import "PerfLoggingViewController.h"

@interface MandelbrotViewController : PerfLoggingViewController

@property (strong, nonatomic) IBOutlet UIImageView *imageView;
@property (strong, nonatomic) IBOutlet UILabel *label;

@end
