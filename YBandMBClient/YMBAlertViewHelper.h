//
//  CDIRemoteAlertViewHelper.h
//  CDI Remote
//
//  Created by Emerson on 13-10-30.
//  Copyright (c) 2013年 CDI. All rights reserved.
//

#import <Foundation/Foundation.h>

@interface YMBAlertViewHelper : NSObject<UIAlertViewDelegate>

+ (YMBAlertViewHelper *) sharedCDIRemoteAlertViewHelper;

- (void)showAlertViewWithTitle:(NSString *)title
                    andMessage:(NSString *)message
            cancelButtonString:(NSString *)cancelButtonString
             otherButtonString:(NSString *)otherButtonString;

- (void)showActivityIndicatorInView:(UIView *)view;
- (void)stopActivityIndicator;

@end
