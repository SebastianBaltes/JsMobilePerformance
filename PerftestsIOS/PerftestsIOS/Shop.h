//
//  Shop.h
//  PerftestsIOS
//
//  Created by Administrator on 06.04.13.
//  Copyright (c) 2013 ObjectCode GmbH. All rights reserved.
//

#import <Foundation/Foundation.h>
#import "RandomValues.h"
#import "Item.h"
#import "User.h"

@interface Shop : NSObject

@property (strong, nonatomic) NSMutableArray *items;
@property (strong, nonatomic) NSMutableArray *users;

+(id)create:(RandomValues*)rnd;
- (id) initWithRnd:(RandomValues*)rnd;

@end
