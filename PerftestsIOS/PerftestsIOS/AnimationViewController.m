//
//  AnimationViewController.m
//  PerftestsIOS
//
//  Created by Administrator on 07.04.13.
//  Copyright (c) 2013 ObjectCode GmbH. All rights reserved.
//

#import "AnimationViewController.h"
#import "ImageViewRawDataHelper.h"
#import "PerfAppDelegate.h"

@interface AnimationViewController () {
    int width;
    int height;
    int size;
    
    unsigned char* rawData;
    ImageViewRawDataHelper *imageRawHelper;
    BOOL stop;
    int framesCount;
    int framesCountAvg;
    int n;
    NSDate *framesTimer;

}


@end

@implementation AnimationViewController

@synthesize imageView;
@synthesize label;

int width;
int height;
int size;

unsigned char* rawData;
ImageViewRawDataHelper *imageRawHelper;
BOOL stop;
int framesCount;
int framesCountAvg;
int n;
NSDate *framesTimer;


- (void)performAction
{
    stop = NO;
    framesCount = 0;
    framesTimer = [NSDate date];
    n=0;
    
    imageRawHelper = [[ImageViewRawDataHelper alloc] initWithImageView:imageView Width:320 Height:504];
    
    width = [imageRawHelper getWidth];
    height = [imageRawHelper getHeight];
    size = width*height;
    
    rawData = [imageRawHelper getRawData];
    
    [self doFrame];
}

-(void)viewWillDisappear:(BOOL)animated {
    stop=YES;
}

-(void)viewDidDisappear:(BOOL)animated {
    imageRawHelper = nil;
}

-(void)doFrame {
    if (stop) {
        return;
    }
    calculateOneIterationX(rawData);
    [imageRawHelper showRawDataInImageView];
    [NSTimer scheduledTimerWithTimeInterval:(0.001) target:self selector:@selector(doFrame) userInfo:nil repeats:NO];
    
    NSDate* now = [NSDate date];
    framesCount++;
    if ([now timeIntervalSinceDate: framesTimer] > 3) {
        framesTimer = now;
        framesCountAvg = framesCount/3;
        framesCount = 0;
        [label setText:[NSString stringWithFormat:@"%d fps",framesCountAvg]];
        [[PerfAppDelegate get] log:[NSString stringWithFormat:@"%d fps",framesCountAvg]];
    }
}

void calculateOneIterationX(unsigned char* rawData) {
    n=(n+1)%255;
    for (int y=0; y<height; y++) {
        for (int x=0; x<width; x++) {
            int i=(y*width+x)*4;
            rawData[i+0] = (x+n)%255;
            rawData[i+1] = (y+n)%255;
            rawData[i+2] = 128;
            rawData[i+3] = 255;
        }
    }
}

@end
