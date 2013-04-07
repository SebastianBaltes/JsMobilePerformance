//
//  TreeNode.m
//  PerftestsIOS
//
//  Created by Administrator on 06.04.13.
//  Copyright (c) 2013 ObjectCode GmbH. All rights reserved.
//

#import "TreeNode.h"

@implementation TreeNode

@synthesize left;
@synthesize right;
@synthesize item;

-(id)initWithLeft:(TreeNode*)left_ right:(TreeNode*)right_ item:(int)item_ {
    if (self = [super init]) {
        left = left_;
        right = right_;
        item = item_;
    }
    return self;
}

-(int)itemCheck {
    if (self.left == nil) {
        return self.item;
    }
    else {
        return self.item + [self.left itemCheck] - [self.right itemCheck];
    }
}

@end
