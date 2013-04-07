//
//  Part.h
//  PerftestsIOS
//
//  Created by Administrator on 06.04.13.
//  Copyright (c) 2013 ObjectCode GmbH. All rights reserved.
//

#import <Foundation/Foundation.h>
#import "RandomValues.h"

@interface Part : NSObject

@property (nonatomic) long long id_;
@property (strong, nonatomic) NSString *name;
@property (strong, nonatomic) NSString *businessKey;
@property (strong, nonatomic) NSString *description;
@property (strong, nonatomic) NSMutableDictionary *properties;

+(id)create:(RandomValues*)rnd;
- (id) initWithRnd:(RandomValues*)rnd;

@end
