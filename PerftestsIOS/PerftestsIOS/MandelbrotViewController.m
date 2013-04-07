//
//  MandelbrotViewController.m
//  PerftestsIOS
//
//  Created by Administrator on 06.04.13.
//  Copyright (c) 2013 ObjectCode GmbH. All rights reserved.
//

#import "MandelbrotViewController.h"
#import "ImageViewRawDataHelper.h"
#import "PerfAppDelegate.h"

@interface MandelbrotViewController ()

@end

@implementation MandelbrotViewController

@synthesize imageView;
@synthesize label;

struct Complex {
    double r, i;
};
typedef struct Complex Complex;

int iteration = 0;
Complex o;
Complex d;
int width = 0;
int height = 0;
int size = 0;

double* zr;
double* zi;
double* iterationPixels;
unsigned char* rawData;
ImageViewRawDataHelper *imageRawHelper;
BOOL stop;
int framesCount = 0;
int framesCountAvg = 0;
NSDate *framesTimer;

- (void)performAction
{
    stop = NO;
    framesCount = 0;
    framesTimer = [NSDate date];
    
    o.r=2;
    o.i=2;
    d.r=4;
    d.i=4;
    
//    ImageViewRawDataHelper *imageRawHelper = [[ImageViewRawDataHelper alloc] initWithImageView:imageView Width:320 Height:504];
    imageRawHelper = [[ImageViewRawDataHelper alloc] initWithImageView:imageView Width:100 Height:100];
    
    width = [imageRawHelper getWidth];
    height = [imageRawHelper getHeight];
    size = width*height;
    
    zr = malloc(sizeof(double)*size);
    zi = malloc(sizeof(double)*size);
    iterationPixels = malloc(sizeof(double)*size);
    rawData = [imageRawHelper getRawData];
    
    for (int i=0; i<size; i++) {
        zr[i]=0.0;
        zi[i]=0.0;
        iterationPixels[i]=0.0;
    }
    
    [self doFrame];
}

-(void)viewWillDisappear:(BOOL)animated {
    stop=YES;
}

-(void)viewDidDisappear:(BOOL)animated {
    free(zr);
    free(zi);
    free(iterationPixels);
    imageRawHelper = nil;
}

-(void)doFrame {
    if (stop) {
        return;
    }
    calculateOneIteration(iterationPixels,zr,zi,rawData);
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

void calculateOneIteration(double iterationPixels[], double zr[],  double zi[], unsigned char* rawData) {
    double cr = 0.0;
    double ci = 0.0;
    double ab = 0.0;
    double z1r = 0.0;
    double z1i = 0.0;
    double zr2 = 0.0;
    double zi2 = 0.0;
    double boundary = 1e4;
    iteration++;
    for ( int x = 0; x < width; x++) {
        for ( int y = 0; y < height; y++) {
            int i = x + y * width;
            cr = x * d.r / width - o.r;
            ci = o.i - y * d.i / height;
            zr2 = zr[i] * zr[i];
            zi2 = zi[i] * zi[i];
            ab = zr2 + zi2;
            if (ab < boundary) {
                z1r = zr2 - zi2 + cr;
                z1i = 2 * zr[i] * zi[i] + ci;
                zr[i] = z1r;
                zi[i] = z1i;
            } else {
                if (iterationPixels[i] == 0) {
                    iterationPixels[i] = iteration;
                }
            }
        }
    }
    for ( int x = 0; x < width; x++) {
        for ( int y = 0; y < height; y++) {
            int i = x + y * width;
            double f;
            double a;
            if (iterationPixels[i] == 0) {
                Complex c;
                c.r=zr[i];
                c.i=zi[i];
                a = complex_angle(c);
                f = ABS(sin(1 / ((M_PI + 0.7) * complex_abs(c))));
            } else {
                a = 0;
                f = iterationPixels[i] / iteration;
            }
            int rgb[3];
            Hsb2Rgb(a, 1, f, rgb);
            rawData[i*4+0] = rgb[0];
            rawData[i*4+1] = rgb[1];
            rawData[i*4+2] = rgb[2];
            rawData[i*4+3] = 255;
        }
    }
}


void Hsb2Rgb( double hue, double saturation, double brightness, int rgb[3]) {
    int r = 0, g = 0, b = 0;
    if (saturation == 0) {
        r = g = b = brightness * 255.0 + 0.5;
    } else {
        double h = (hue - floor(hue)) * 6.0;
        double f = h - floor(h);
        double p = brightness * (1.0 - saturation);
        double q = brightness * (1.0 - saturation * f);
        double t = brightness * (1.0 - (saturation * (1.0 - f)));
        int hint = floor(h);
        switch (hint) {
            case 0:
                r = (brightness * 255.0 + 0.5);
                g = (t * 255.0 + 0.5);
                b = (p * 255.0 + 0.5);
                break;
            case 1:
                r = (q * 255.0 + 0.5);
                g = (brightness * 255.0 + 0.5);
                b = (p * 255.0 + 0.5);
                break;
            case 2:
                r = (p * 255.0 + 0.5);
                g = (brightness * 255.0 + 0.5);
                b = (t * 255.0 + 0.5);
                break;
            case 3:
                r = (p * 255.0 + 0.5);
                g = (q * 255.0 + 0.5);
                b = (brightness * 255.0 + 0.5);
                break;
            case 4:
                r = (t * 255.0 + 0.5);
                g = (p * 255.0 + 0.5);
                b = (brightness * 255.0 + 0.5);
                break;
            case 5:
                r = (brightness * 255.0 + 0.5);
                g = (p * 255.0 + 0.5);
                b = (q * 255.0 + 0.5);
                break;
        }
    }
    rgb[0]=r;
    rgb[1]=g;
    rgb[2]=b;
}

double complex_abs(Complex c) {
    return sqrt(c.r * c.r + c.i * c.i);
}

double complex_angle(Complex c) {
    return atan2(c.i, c.r);
}

@end
