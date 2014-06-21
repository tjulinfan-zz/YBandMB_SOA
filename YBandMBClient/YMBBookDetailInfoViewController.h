//
//  YMBBookDetailInfoViewController.h
//  YBandMBClient
//
//  Created by Emerson on 14-6-19.
//  Copyright (c) 2014年 Emerson. All rights reserved.
//

#import <UIKit/UIKit.h>

@class YMBBookInfo;
@interface YMBBookDetailInfoViewController : UIViewController<NSXMLParserDelegate>

@property (nonatomic, strong) YMBBookInfo * book;

@end
