//
//  ResultObject.m
//  PerftestsIOS
//
//  Created by Administrator on 07.04.13.
//  Copyright (c) 2013 ObjectCode GmbH. All rights reserved.
//

#import "ResultObject.h"

@implementation ResultObject

@synthesize id_;
@synthesize name;
@synthesize description;
@synthesize flag;
@synthesize properties;
@synthesize values;

-(void)parse:(NSDictionary*)json {
    self.id_ = [json objectForKey:@"id"];
    self.name = [json objectForKey:@"name"];
    self.description = [json objectForKey:@"description"];
    self.flag = [json objectForKey:@"flag"];
    self.real = [json objectForKey:@"real"];
    NSDictionary *props = [json objectForKey:@"properties"];
    for(id key in props) {
        id value = [props objectForKey:key];
        [self.properties setValue:value forKey:key];
    }
    self.values = [json objectForKey:@"values"];
}

@end
