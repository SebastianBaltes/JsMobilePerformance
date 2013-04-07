//
//  Part.m
//  PerftestsIOS
//
//  Created by Administrator on 06.04.13.
//  Copyright (c) 2013 ObjectCode GmbH. All rights reserved.
//

#import "Part.h"

@implementation Part

@synthesize id_;
@synthesize name;
@synthesize businessKey;
@synthesize description;
@synthesize properties;


+(id)create:(RandomValues*)rnd {
    return [[Part alloc] initWithRnd:rnd];
}

- (id) initWithRnd:(RandomValues*)rnd {
    if (self = [super init]) {
        id_ = [rnd nextInt];
        name = [rnd nextString:40];
        businessKey = [rnd nextString:10];
        description = [rnd nextString:100];
        properties = [[NSMutableDictionary alloc] init];
        for (int i = 0; i < 10; i++) {
			[properties setValue:[rnd nextString:10] forKey:[rnd nextString:30]];
		}
    }
    return self;
}

@end
