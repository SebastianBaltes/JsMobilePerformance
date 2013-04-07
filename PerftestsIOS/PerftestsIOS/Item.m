//
//  Item.m
//  PerftestsIOS
//
//  Created by Administrator on 06.04.13.
//  Copyright (c) 2013 ObjectCode GmbH. All rights reserved.
//

#import "Item.h"

@implementation Item

@synthesize id_;
@synthesize name;
@synthesize businessKey;
@synthesize start;
@synthesize end;
@synthesize description;
@synthesize width;
@synthesize height;
@synthesize important;
@synthesize parts;

+(id)create:(RandomValues*)rnd {
    return [[Part alloc] initWithRnd:rnd];
}

- (id) initWithRnd:(RandomValues*)rnd {
    if (self = [super init]) {
        id_ = [rnd nextInt];
        name = [rnd nextString:40];
        businessKey = [rnd nextString:10];
        start = [rnd nextDate];
        end = [rnd nextDate];
        description = [rnd nextString:100];
        /*
         this.description = rnd.nextString(500);
         this.width = rnd.nextDouble();
         this.height = rnd.nextDouble();
         this.important = rnd.nextBoolean();
         this.parts = [];
         for (var i = 0; i < 10; i++) {
         this.parts.push(new Part(rnd));
         }
         */
        width = [rnd nextDouble];
        height = [rnd nextDouble];
        important = [rnd nextBoolean];
        parts = [[NSMutableArray alloc] init];
        for (int i = 0; i < 10; i++) {
			[parts addObject:[Part create:rnd]];
		}
    }
    return self;
}

@end
