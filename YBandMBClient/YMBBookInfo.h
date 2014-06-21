//
//  YMBBookInfo.h
//  YBandMBClient
//
//  Created by Emerson on 14-6-19.
//  Copyright (c) 2014年 Emerson. All rights reserved.
//

#import <Foundation/Foundation.h>

@interface YMBBookInfo : NSObject

@property (nonatomic, strong) NSString * bookId;
@property (nonatomic, strong) NSString * bookTitle;
@property (nonatomic, strong) NSString * author;
@property (nonatomic, strong) NSString * bookDescription;
@property (nonatomic, strong) NSString * imageurl;
@property (nonatomic, strong) NSString * publisher;
@property (nonatomic, strong) NSString * summary;
@property (nonatomic, strong) NSString * hotelAddress;
@property (nonatomic, strong) NSString * isbn;

@property (nonatomic, strong) NSString * sellerId;
@property (nonatomic, strong) NSMutableArray * biderArray;

@property (nonatomic) int searchingType;

@end
