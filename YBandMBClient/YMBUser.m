//
//  CDIUser.m
//  CDI_iPad_App
//
//  Created by Gabriel Yeah on 13-4-1.
//  Copyright (c) 2013年 Gabriel Yeah. All rights reserved.
//

#import "YMBUser.h"

static YMBUser *currentUser;

@implementation YMBUser

+ (YMBUser *)currentUser
{
    if (!currentUser) {
        currentUser = [[YMBUser alloc] init];
    }
    return currentUser;
}

+ (void)setCurrentUserWithUserId:(NSString *)userID
                   andSessionKey:(NSString *)sessionKey
{
    YMBUser *user = [YMBUser currentUser];
    user.userID = userID;
    user.sessionKey = sessionKey;
}

+ (void) deallocCurrentUser
{
    currentUser = NULL;
}


@end
