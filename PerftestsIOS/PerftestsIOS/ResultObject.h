//
//  ResultObject.h
//  PerftestsIOS
//
//  Created by Administrator on 07.04.13.
//  Copyright (c) 2013 ObjectCode GmbH. All rights reserved.
//

#import <Foundation/Foundation.h>

@interface ResultObject : NSObject

@property (strong,nonatomic) NSString *id_;
@property (strong,nonatomic) NSString *name;
@property (strong,nonatomic) NSString *description;
@property (nonatomic) NSNumber *flag;
@property (nonatomic) NSNumber *real;
@property (nonatomic) NSDictionary *properties;
@property (nonatomic) NSArray *values;

-(void)parse:(NSData*)json;

@end
