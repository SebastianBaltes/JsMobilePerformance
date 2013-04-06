//
//  TreeNode.h
//  PerftestsIOS
//
//  Created by Administrator on 06.04.13.
//  Copyright (c) 2013 ObjectCode GmbH. All rights reserved.
//

#import <Foundation/Foundation.h>

@interface TreeNode : NSObject

@property (strong,nonatomic) TreeNode *left;
@property (strong,nonatomic) TreeNode *right;
@property (nonatomic) int item;

-(id)initWithLeft:(TreeNode*)left_ right:(TreeNode*)right_ item:(int)item_;
-(int)itemCheck;

@end
