//
//  CDIUser.h
//  CDI_iPad_App
//
//  Created by Gabriel Yeah on 13-4-1.
//  Copyright (c) 2013年 Gabriel Yeah. All rights reserved.
//

#import <Foundation/Foundation.h>

@interface YMBUser : NSObject

@property (nonatomic, strong) NSString *userID;
@property (nonatomic, strong) NSString *sessionKey;

+ (YMBUser *)currentUser;
+ (void)setCurrentUserWithUserId:(NSString *)userID
                   andSessionKey:(NSString *)sessionKey;
+ (void) deallocCurrentUser;
@end
