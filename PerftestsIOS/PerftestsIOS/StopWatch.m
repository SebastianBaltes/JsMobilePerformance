//
//  StopWatch.m
//  PerftestsIOS
//
//  Created by Administrator on 05.04.13.
//  Copyright (c) 2013 ObjectCode GmbH. All rights reserved.
//

#import "StopWatch.h"

@implementation StopWatch

NSDate *start;

+(id)create {
    return [[StopWatch alloc] init];
}

- (id) init {
    // Initializer der Superklasse aufrufen ([super init] liefert nil im Fehlerfall)
    if (self = [super init]) {
        [self start];
    }
    return self;
}

-(void)start {
    start = [NSDate date];
}

-(NSString*)stop {
    NSDate *stop = [NSDate date];
    NSTimeInterval executionTime = [stop timeIntervalSinceDate:start];
    return [NSString stringWithFormat:@"%f ms", executionTime*1000];
}

@end
