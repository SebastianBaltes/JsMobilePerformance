//
//  PerfDetailViewController.m
//  PerftestsIOS
//
//  Created by Administrator on 05.04.13.
//  Copyright (c) 2013 ObjectCode GmbH. All rights reserved.
//

#import "StringViewController.h"
#import "StopWatch.h"
#import "RandomValues.h"

@interface StringViewController ()
@end

@implementation StringViewController

RandomValues* rnd;
NSString* text;

- (void)performAction
{
    rnd = [RandomValues create];
    [self appendTest];
    [self splitEs];
    [self replaceRandomRegexpTest];
}


- (void)appendTest
{
    StopWatch *w = [StopWatch create];
    [self log:@"appending 10000 words into a String"];
    text = [rnd nextTextWithWords:10000];
    [self log:[NSString stringWithFormat:@"the string is %d characters long",[text length]]];
    [self log:[w stop]];
    [self checksum:text];
}

- (void)splitEs
{
    StopWatch *w = [StopWatch create];
    [self log:@"split 'e'"];
    NSArray *parts = [text componentsSeparatedByString:@"e"];
    text = [rnd nextTextWithWords:10000];
    [self log:[NSString stringWithFormat:@"into %d parts",[parts count]]];
    [self log:[w stop]];
}

- (void)replaceRandomRegexpTest
{
    StopWatch *w = [StopWatch create];
    [self log:@"compile, search and replace 100 regexps"];
    NSString *result = text;
    for (int i = 0; i <100; i++) {
        NSError *error = NULL;
        NSString *pattern = [NSString stringWithFormat:@"[%@]{1,2}[aeiou]+\\s+",[[rnd nextString:10] lowercaseString]];
        NSString *replace = [rnd nextString:10];
        NSRegularExpression *regex = [NSRegularExpression regularExpressionWithPattern:pattern options:0 error:&error];
        result = [regex stringByReplacingMatchesInString:result options:0 range:NSMakeRange(0, [result length]) withTemplate:replace];
    }
    [self log:[w stop]];
    [self checksum:text];
}

- (void)checksum:(NSString*)result
{
    StopWatch *w = [StopWatch create];
    int check = 0;
    int length = [result length];
    for (int i = 0; i<length; i++) {
        check = (check + [result characterAtIndex:i]) % 10000;
    }
    [self log:[NSString stringWithFormat:@"checksum: %d",check]];
    [self log:[w stop]];
}

@end
