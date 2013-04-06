//
//  BinaryTreesViewController.m
//  PerftestsIOS
//
//  Created by Administrator on 06.04.13.
//  Copyright (c) 2013 ObjectCode GmbH. All rights reserved.
//

#import "BinaryTreesViewController.h"
#import "StopWatch.h"
#import "RandomValues.h"
#import "TreeNode.h"

@interface BinaryTreesViewController ()

@end

@implementation BinaryTreesViewController

RandomValues* rnd;

- (void)performAction
{
    [self log:@"a bottum-up binary tree structure of depth 13 that is modified and walked"];
    StopWatch *w = [StopWatch create];
    rnd = [RandomValues create];
    [self test:12];
    [self log:[w stop]];
}

-(TreeNode*) bottomUpTree:(int)item depth:(int)depth {
    if (depth>0) {
        return [[TreeNode alloc] initWithLeft:[self bottomUpTree:2 * item - 1 depth:depth - 1] right:[self bottomUpTree:2 * item depth:depth - 1] item:item];
    } else {
        return [[TreeNode alloc] initWithLeft:nil right:nil item:item];
    }
}

-(void) test:(int)n {
    int minDepth = 4;
    int maxDepth = MAX(minDepth + 2, n);
    int stretchDepth = maxDepth + 1;
    
    int check = [[self bottomUpTree:0 depth:stretchDepth] itemCheck];
    [self log:[NSString stringWithFormat:@"stretch tree of depth %d\t check: %d", stretchDepth, check]];
    
    TreeNode *longLivedTree = [self bottomUpTree:0 depth:maxDepth];
    for (int depth = minDepth; depth <= maxDepth; depth += 2) {
        int iterations = 1 << (maxDepth - depth + minDepth);
        
        check = 0;
        for (int i = 1; i <= iterations; i++) {
            check += [[self bottomUpTree:i depth:depth] itemCheck];
            check += [[self bottomUpTree:-i depth:depth] itemCheck];
        }
        [self log:[NSString stringWithFormat:@"%d\t trees of depth %d\t check: %d", iterations * 2, depth, check]];
    }
    
}

@end
