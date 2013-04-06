//
//  HoughCircleViewController.m
//  PerftestsIOS
//
//  Created by Administrator on 06.04.13.
//  Copyright (c) 2013 ObjectCode GmbH. All rights reserved.
//

#import "HoughCircleViewController.h"
#import "StopWatch.h"
#import "RandomValues.h"
#import "ImageViewRawDataHelper.h"

@interface HoughCircleViewController ()

@end

@implementation HoughCircleViewController

@synthesize imageView;

- (void)performAction
{
    StopWatch *w = [StopWatch create];
    
    UIImage *image = [UIImage imageNamed:@"balls.jpg"];
    
    self.imageView.image = image;
    ImageViewRawDataHelper *imageRawHelper = [ImageViewRawDataHelper create:self.imageView];
    [self markCircle:imageRawHelper radius:39 originalImage:(UIImage*)image];
    [self log:[w stop]];
}

- (UIImage *)imageByDrawingCircleOnImage:(UIImage *)image X:(int)x Y:(int)y radius:(int)radius
{
	// begin a graphics context of sufficient size
	UIGraphicsBeginImageContext(image.size);
    
	// draw original image into the context
	[image drawAtPoint:CGPointZero];
    
	// get the context for CoreGraphics
	CGContextRef ctx = UIGraphicsGetCurrentContext();
    
	// set stroking color and draw circle
	[[UIColor redColor] setStroke];
    CGContextSetLineWidth(ctx, 5.0);
    
	// make circle rect 5 px from border
	//CGRect circleRect = CGRectMake(x-radius, y-radius, x+radius, y+radius);
	CGRect circleRect = CGRectMake(x-radius, y-radius, radius*2, radius*2);
    
	// draw circle
	CGContextStrokeEllipseInRect(ctx, circleRect);
    
	// make image out of bitmap context
	UIImage *retImage = UIGraphicsGetImageFromCurrentImageContext();
    
	// free the context
	UIGraphicsEndImageContext();
    
	return retImage;
}

- (void) markCircle:(ImageViewRawDataHelper *)rawData radius:(int)radius originalImage:(UIImage*)image {
    // set gray pixels in 2d array
    int width = [rawData getWidth];
    int height = [rawData getHeight];
    int imageValues[width][height];
    unsigned char *rgb = [rawData getRawData];
    int byteIndex = 0;    
    for (int y = 0; y < height; y++) {
        for (int x = 0; x < width; x++) {
            int r = rgb[byteIndex];
            int g = rgb[byteIndex+1];
            int b = rgb[byteIndex+2];
            int gray = (r + g + b) / 3;
            imageValues[x][y] = gray;
            byteIndex += 4;
        }
    }
    
    // fill sobel field
    int min = 1;
    int maxW = width - min;
    int maxH = height - min;
    int maxSoebelSize = width * height;
    int sobelCoordsX[maxSoebelSize];
	int sobelCoordsY[maxSoebelSize];
	double sobelValues[maxSoebelSize];
    int sobelEdges = 0;
    for (int y = min; y < maxH; y++) {
        for (int x = min; x < maxW; x++) {
            double sobelSum = 0.0;
            int d[3][3];
            for (int yi = 0; yi < 3; yi++) {
                for (int xi = 0; xi < 3; xi++) {
                    d[xi][yi] = imageValues[x + xi - 1][y + yi - 1];
                }
            }
            double sobelH = ((double) d[0][2] + (double) (2 * d[1][2]) + d[2][2]
                             - d[0][0] - (2 * d[1][0]) - d[2][0]);
            double sobelV = ((double) d[2][0] + (double) (2 * d[2][1]) + d[2][2]
                             - d[0][0] - (2 * d[0][1]) - d[0][2]);
            sobelSum = (ABS(sobelH) + ABS(sobelV)) / 2;

            if (sobelSum != 0) {
                sobelCoordsX[sobelEdges] = x;
                sobelCoordsY[sobelEdges] = y;
                sobelValues[sobelEdges] = sobelSum;
                sobelEdges++;
            }
        }
    }
    
    // define circle shape
    int numCirclePoints = 0;
    int numPts = round(8 * radius);
    int circleBorder[2][numPts];
    for (int i = 0; i < numPts; i++) {
        circleBorder[0][i] = 0;
        circleBorder[1][i] = 0;
    }
    for (int i = 0; i < numPts; i++) {
        double theta = 6.283185307179586 * i / numPts;
        int xx = (int) round(radius * cos(theta));
        int yy = (int) round(radius * sin(theta));
        if ((numCirclePoints == 0)
            || ((xx != circleBorder[0][numCirclePoints]) && (yy != circleBorder[1][numCirclePoints]))) {
            circleBorder[0][numCirclePoints] = xx;
            circleBorder[1][numCirclePoints] = yy;
            numCirclePoints++;
        }
    }

    // do huge transformation
    double houghValues[width][height];
    for (int y = 0; y < height; y++) {
        for (int x = 0; x < width; x++) {
            houghValues[x][y] = 0.0;
        }
    }
    for (int i = 0; i < sobelEdges; i++) {
        int x = sobelCoordsX[i];
        int y = sobelCoordsY[i];
        double sobel = sobelValues[i];
        for (int j = 0; j < numCirclePoints; j++) {
            int xCoord = x + circleBorder[0][j];
            int yCoord = y + circleBorder[1][j];
            if (xCoord >= 0 && xCoord < width && yCoord >= 0
                && yCoord < height) {
                houghValues[xCoord][yCoord] += sobel;
            }
        }
    }
    
    // get global huge maximum
    double treshold = 0;
    for (int y = 0; y < height; y++) {
        for (int x = 0; x < width; x++) {
            treshold = MAX(treshold, houghValues[x][y]);
        }
    }
    int circleX = 0;
    int circleY = 0;
    for (int y = 0; y < height; y++) {
        for (int x = 0; x < width; x++) {
            if (houghValues[x][y] >= treshold) {
                circleX = x;
                circleY = y;
            }
        }
    }
    
    // draw circle
    self.imageView.image = [self imageByDrawingCircleOnImage:image X:circleX Y:circleY radius:radius];
}


@end
