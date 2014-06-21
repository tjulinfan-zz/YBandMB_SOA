//
//  YMBMainTableViewCell.m
//  YBandMBClient
//
//  Created by Emerson on 14-6-18.
//  Copyright (c) 2014年 Emerson. All rights reserved.
//

#import "YMBMainTableViewCell.h"

@implementation YMBMainTableViewCell

- (id)initWithStyle:(UITableViewCellStyle)style reuseIdentifier:(NSString *)reuseIdentifier
{
    self = [super initWithStyle:style reuseIdentifier:reuseIdentifier];
    if (self) {
        // Initialization code
    }
    return self;
}

- (void)awakeFromNib
{
    // Initialization code
}

- (void)setSelected:(BOOL)selected animated:(BOOL)animated
{
    [super setSelected:selected animated:animated];

    // Configure the view for the selected state
}

@end
