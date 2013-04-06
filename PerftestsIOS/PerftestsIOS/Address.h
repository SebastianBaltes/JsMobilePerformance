//
//  Address.h
//  PerftestsIOS
//
//  Created by Administrator on 06.04.13.
//  Copyright (c) 2013 ObjectCode GmbH. All rights reserved.
//

#import <Foundation/Foundation.h>
#import "RandomValues.h"

@interface Address : NSObject

@property (strong, nonatomic) NSArray *line;
@property (strong, nonatomic) NSString *city;
@property (strong, nonatomic) NSString *plz;
@property (strong, nonatomic) NSString *country;

+(id)create:(RandomValues*)rnd;
- (id) initWithRnd:(RandomValues*)rnd;

@end
