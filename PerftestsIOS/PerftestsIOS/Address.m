//
//  Address.m
//  PerftestsIOS
//
//  Created by Administrator on 06.04.13.
//  Copyright (c) 2013 ObjectCode GmbH. All rights reserved.
//

#import "Address.h"

@implementation Address

@synthesize line;
@synthesize city;
@synthesize plz;
@synthesize country;

+(id)create:(RandomValues*)rnd {
    return [[Address alloc] initWithRnd:rnd];
}

- (id) initWithRnd:(RandomValues*)rnd {
    if (self = [super init]) {
        line = @[[rnd nextString:60],[rnd nextString:60],[rnd nextString:60]];
        city = [rnd nextString:10];
		plz = [rnd nextString:5];
		country = [rnd nextString:10];
    }
    return self;
}

@end
