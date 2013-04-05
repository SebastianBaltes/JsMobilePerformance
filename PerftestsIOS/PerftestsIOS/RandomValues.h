//
//  RandomValues.h
//  PerftestsIOS
//
//  Created by Administrator on 05.04.13.
//  Copyright (c) 2013 ObjectCode GmbH. All rights reserved.
//

#import <Foundation/Foundation.h>

@interface RandomValues : NSObject

+(id)create;
-(int)nextInt;
-(double)nextDouble;
-(BOOL)nextBoolean;
-(NSData*)nextDate;
-(id)nextFromArray:(NSArray*)arr;
-(NSString*)nextTextWord;
-(NSString*)nextString:(int)length;
-(NSString*)nextTextWithWords:(int)countWords;

@end
