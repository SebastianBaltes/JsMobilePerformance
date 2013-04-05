//
//  StopWatch.h
//  PerftestsIOS
//
//  Created by Administrator on 05.04.13.
//  Copyright (c) 2013 ObjectCode GmbH. All rights reserved.
//

#import <Foundation/Foundation.h>

@interface StopWatch : NSObject
+(id)create;
-(void)start;
-(NSString*)stop;
@end
