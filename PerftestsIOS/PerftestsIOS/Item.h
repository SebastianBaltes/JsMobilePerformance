//
//  Item.h
//  PerftestsIOS
//
//  Created by Administrator on 06.04.13.
//  Copyright (c) 2013 ObjectCode GmbH. All rights reserved.
//

#import <Foundation/Foundation.h>
#import "RandomValues.h"
#import "Part.h"

@interface Item : NSObject

@property (nonatomic) long long id_;
@property (strong, nonatomic) NSString *name;
@property (strong, nonatomic) NSString *businessKey;
@property (strong, nonatomic) NSDate *start;
@property (strong, nonatomic) NSDate *end;
@property (strong, nonatomic) NSString *description;
@property (nonatomic) double width;
@property (nonatomic) double height;
@property (nonatomic) BOOL important;
@property (strong, nonatomic) NSMutableArray *parts;

+(id)create:(RandomValues*)rnd;
- (id) initWithRnd:(RandomValues*)rnd;

@end
