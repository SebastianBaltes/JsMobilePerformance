//
//  User.h
//  PerftestsIOS
//
//  Created by Administrator on 06.04.13.
//  Copyright (c) 2013 ObjectCode GmbH. All rights reserved.
//

#import <Foundation/Foundation.h>
#import "RandomValues.h"
#import "Address.h"

@interface User : NSObject

@property (nonatomic) long long id_;
@property (strong, nonatomic) NSString *name;
@property (strong, nonatomic) NSString *email;
@property (strong, nonatomic) NSDate *birthday;
@property (strong, nonatomic) Address *address;

+(id)create:(RandomValues*)rnd;
- (id) initWithRnd:(RandomValues*)rnd;

@end
