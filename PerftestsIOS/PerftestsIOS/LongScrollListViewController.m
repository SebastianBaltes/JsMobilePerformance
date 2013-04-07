//
//  LongScrollListViewController.m
//  PerftestsIOS
//
//  Created by Administrator on 07.04.13.
//  Copyright (c) 2013 ObjectCode GmbH. All rights reserved.
//

#import "LongScrollListViewController.h"
#import "RandomValues.h"

@interface LongScrollListViewController ()

@end

@implementation LongScrollListViewController

NSMutableArray *titles;
NSMutableArray *descriptions;

- (id)initWithStyle:(UITableViewStyle)style
{
    self = [super initWithStyle:style];
    if (self) {
    }
    return self;
}

- (void)viewDidLoad
{
    RandomValues *rnd = [RandomValues create];
    titles = [NSMutableArray array];
    descriptions = [NSMutableArray array];
    for (int i=0; i<100; i++) {
        [titles addObject:[rnd nextTextWithWords:3]];
        [descriptions addObject:[rnd nextTextWithWords:20]];
    }
    [super viewDidLoad];
}

- (void)didReceiveMemoryWarning
{
    [super didReceiveMemoryWarning];
    // Dispose of any resources that can be recreated.
}

- (NSInteger)numberOfSectionsInTableView:(UITableView *)tableView
{
    // Return the number of sections.
    return 1;
}

- (NSInteger)tableView:(UITableView *)tableView numberOfRowsInSection:(NSInteger)section
{
    // Return the number of rows in the section.
    return [titles count];
}

- (UITableViewCell *)tableView:(UITableView *)tableView cellForRowAtIndexPath:(NSIndexPath *)indexPath
{
    static NSString *CellIdentifier = @"MyCell";
    UITableViewCell *cell = [tableView dequeueReusableCellWithIdentifier:CellIdentifier forIndexPath:indexPath];
    
    UIImageView *imgView = (UIImageView *) [cell viewWithTag:0];
    imgView.image = [UIImage imageNamed:[NSString stringWithFormat:@"img%d.png",indexPath.row]];
    
    UILabel *label = (UILabel *) [cell viewWithTag:1];
    label.text = [titles objectAtIndex:indexPath.row];

    UITextView *text = (UITextView *) [cell viewWithTag:2];
    text.text = [descriptions objectAtIndex:indexPath.row];
    
    return cell;
}

- (void)tableView:(UITableView *)tableView didSelectRowAtIndexPath:(NSIndexPath *)indexPath
{
}

@end
