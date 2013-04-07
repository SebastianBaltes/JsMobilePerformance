//
//  JsonRequestViewController.m
//  PerftestsIOS
//
//  Created by Administrator on 07.04.13.
//  Copyright (c) 2013 ObjectCode GmbH. All rights reserved.
//

#import "JsonRequestViewController.h"
#import "StopWatch.h"
#import "RandomValues.h"
#import "ResultObject.h"

@interface JsonRequestViewController ()

@end

@implementation JsonRequestViewController

//NSString const *url = @"http://172.22.2.50:9000";
NSString *url = @"http://192.168.1.74:9000";

- (void)performAction
{
    RandomValues *rnd = [RandomValues create];

    StopWatch *w = [StopWatch create];
    [self log:@"perform 10 json requests with 100 objects each and parse the response"];
    
    for (int i=0; i<10; i++) {
        NSURLRequest *request = [NSURLRequest requestWithURL:[NSURL URLWithString:[NSString stringWithFormat:@"%@/%d",url,[rnd nextInt]]]];
        NSURLResponse *response;
        NSData *responseData = [NSURLConnection sendSynchronousRequest:request returningResponse:&response error:nil];
        NSError* error;
        NSArray* json = [NSJSONSerialization
                         JSONObjectWithData:responseData
                         options:kNilOptions
                         error:&error];
        
        for (int j=0; j<100; j++) {
            ResultObject *result = [[ResultObject alloc] init];
            [result parse:[json objectAtIndex:j]];
        }        
    }
    
    [self log:[w stop]];
}

@end
