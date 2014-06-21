//
//  YMBPersonalCenterTableViewCell.h
//  YBandMBClient
//
//  Created by Emerson on 14-6-19.
//  Copyright (c) 2014年 Emerson. All rights reserved.
//

#import <UIKit/UIKit.h>
@class YMBBookInfo;
@interface YMBPersonalCenterTableViewCell : UITableViewCell

@property (nonatomic, strong) YMBBookInfo * bookInfo;

@property (weak, nonatomic) IBOutlet UIImageView *bookImageView;
@property (weak, nonatomic) IBOutlet UILabel *bookTitle;
@property (weak, nonatomic) IBOutlet UILabel *bookAuthor;
@property (weak, nonatomic) IBOutlet UILabel *bookPublisher;

@end
