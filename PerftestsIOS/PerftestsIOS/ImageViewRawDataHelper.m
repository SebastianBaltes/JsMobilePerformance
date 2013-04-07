//
//  ImageViewRawDataHelper.m
//  PerftestsIOS
//
//  Created by Administrator on 06.04.13.
//  Copyright (c) 2013 ObjectCode GmbH. All rights reserved.
//

#import "ImageViewRawDataHelper.h"

@implementation ImageViewRawDataHelper

NSUInteger width;
NSUInteger height;
unsigned char *rawData;
NSUInteger bytesPerPixel;
NSUInteger bytesPerRow;
NSUInteger bitsPerComponent;
UIImageView *imageView;
CGColorSpaceRef colorSpace;

+(id)create:(UIImageView*)imageView {
    return [[ImageViewRawDataHelper alloc] initWithImageView:imageView];
}

- (id) initWithImageView:(UIImageView*)imageView_ {
    if (self = [super init]) {
        imageView = imageView_;
        [self extractRawData];
    }
    return self;
}

- (id)initWithImageView:(UIImageView*)imageView_ Width:(int)width Height:(int)height {
    if (self = [super init]) {
        imageView = imageView_;
        UIGraphicsBeginImageContext(CGSizeMake(width, height));
        UIImage *img = UIGraphicsGetImageFromCurrentImageContext();
        UIGraphicsEndImageContext();
        imageView.image = img;
        [self extractRawData];
    }
    return self;
}

- (void) dealloc
{
    free(rawData);
}

-(NSUInteger)getWidth {
    return width;
}

-(NSUInteger)getHeight {
    return height;
}

-(NSUInteger)getRawDataLength {
    return width * height;
}

-(unsigned char*)getRawData {
    return rawData;
}

-(void)extractRawData {
    UIImage* workingImage = imageView.image;
    CGImageRef imageRef = [workingImage CGImage];
    width = CGImageGetWidth(imageRef);
    height = CGImageGetHeight(imageRef);
    colorSpace = CGColorSpaceCreateDeviceRGB();
    rawData = malloc(height * width * 4);
    bytesPerPixel = 4;
    bytesPerRow = bytesPerPixel * width;
    bitsPerComponent = 8;
    CGContextRef context = CGBitmapContextCreate(rawData, width, height,
												 bitsPerComponent, bytesPerRow, colorSpace,
												 kCGImageAlphaPremultipliedLast | kCGBitmapByteOrder32Big);
    CGColorSpaceRelease(colorSpace);
	
    CGContextDrawImage(context, CGRectMake(0, 0, width, height), imageRef);
    CGContextRelease(context);
}

-(void)showRawDataInImageView {
	CGContextRef ctx = CGBitmapContextCreate(rawData,
                                             width,
                                             height,
                                             8,
                                             bytesPerRow,
                                             colorSpace,
                                             kCGImageAlphaPremultipliedLast );
	
	CGImageRef imageRef = CGBitmapContextCreateImage (ctx);
	UIImage* rawImage = [UIImage imageWithCGImage:imageRef];
	
	CGContextRelease(ctx);
	
	[imageView setImage:rawImage];
}


-(void)grayscale {
    // Now your rawData contains the image data in the RGBA8888 pixel format.
    int byteIndex = (bytesPerRow * 0) + 0 * bytesPerPixel;
    for (int ii = 0 ; ii < width * height ; ++ii)
    {
        // Get color values to construct a UIColor
        //		  CGFloat red   = (rawData[byteIndex]     * 1.0) / 255.0;
        //        CGFloat green = (rawData[byteIndex + 1] * 1.0) / 255.0;
        //        CGFloat blue  = (rawData[byteIndex + 2] * 1.0) / 255.0;
        //        CGFloat alpha = (rawData[byteIndex + 3] * 1.0) / 255.0;
		
		int outputColor = 0;
		
		rawData[byteIndex] = (char) (outputColor);
		rawData[byteIndex+1] = (char) (outputColor);
		rawData[byteIndex+2] = (char) (outputColor);
		
        byteIndex += 4;
    }
}

@end
