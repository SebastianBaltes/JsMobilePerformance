//
//  ImageViewRawDataHelper.h
//  PerftestsIOS
//
//  Created by Administrator on 06.04.13.
//  Copyright (c) 2013 ObjectCode GmbH. All rights reserved.
//

#import <Foundation/Foundation.h>

@interface ImageViewRawDataHelper : NSObject {
}

+(id)create:(UIImageView*)imageView;
-(void)showRawDataInImageView;
-(void)grayscale;
-(NSUInteger)getRawDataLength;
-(unsigned char*)getRawData;
-(NSUInteger)getWidth;
-(NSUInteger)getHeight;
- (id)initWithImageView:(UIImageView*)imageView_ Width:(int)width Height:(int)height;

@end
