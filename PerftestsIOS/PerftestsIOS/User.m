//
//  User.m
//  PerftestsIOS
//
//  Created by Administrator on 06.04.13.
//  Copyright (c) 2013 ObjectCode GmbH. All rights reserved.
//

#import "User.h"

@implementation User

@synthesize id_;
@synthesize name;
@synthesize email;
@synthesize birthday;
@synthesize address;

+(id)create:(RandomValues*)rnd {
    return [[User alloc] initWithRnd:rnd];
}

- (id) initWithRnd:(RandomValues*)rnd {
    if (self = [super init]) {
        id_ = [rnd nextInt];
        name = [rnd nextString:40];
        email = [rnd nextString:40];
        birthday = [rnd nextDate];
        address = [Address create:rnd];
    }
    return self;
}

@end
