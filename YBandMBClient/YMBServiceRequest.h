//
//  YMBServiceASK.h
//  YBandMBClient
//
//  Created by Emerson on 14-6-18.
//  Copyright (c) 2014年 Emerson. All rights reserved.
//

#import <Foundation/Foundation.h>

@class ResponseData;
@interface YMBServiceRequest : NSObject

+ (ResponseData *)registerNewUserWithUserName:(NSString *)userName
                           password:(NSString *)password
                              email:(NSString *)email
                        phoneNumber:(NSString *)phoneNumber
                            address:(NSString *)address;

+ (ResponseData *)logInWithUserName:(NSString *)userName
                 password:(NSString *)password;

+ (ResponseData *)getAllBookByOwnerSession:(NSString *)session;

+ (ResponseData *)getAllBook;

+ (ResponseData *)publishBookToSell:(NSString *)session
                    WithDescription:(NSString *)description
                          withPrice:(float)price
                           withISBN:(NSString *)isbn;

+ (ResponseData *)getBookByISBN:(NSString *)isbn;

+ (ResponseData *)giveABindOnBookByBookId:(NSString *)bookId
                               andPrice:(float)price;

+ (ResponseData *)getBidsByUsers;
@end
