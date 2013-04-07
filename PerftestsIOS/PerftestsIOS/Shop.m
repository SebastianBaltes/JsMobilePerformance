//
//  Shop.m
//  PerftestsIOS
//
//  Created by Administrator on 06.04.13.
//  Copyright (c) 2013 ObjectCode GmbH. All rights reserved.
//

#import "Shop.h"

@implementation Shop

@synthesize items;
@synthesize users;

+(id)create:(RandomValues*)rnd {
    return [[Shop alloc] initWithRnd:rnd];
}

- (id) initWithRnd:(RandomValues*)rnd {
    if (self = [super init]) {
        items = [[NSMutableArray alloc] init];
        for (int i = 0; i < 200; i++) {
			[items addObject:[Item create:rnd]];
		}
    }
    if (self = [super init]) {
        users = [[NSMutableArray alloc] init];
        for (int i = 0; i < 200; i++) {
			[users addObject:[User create:rnd]];
		}
    }
    return self;
}

@end
