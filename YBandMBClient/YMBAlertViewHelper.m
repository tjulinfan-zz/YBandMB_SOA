//
//  CDIRemoteAlertViewHelper.m
//  CDI Remote
//
//  Created by Emerson on 13-10-30.
//  Copyright (c) 2013年 CDI. All rights reserved.
//

#import "YMBAlertViewHelper.h"

static YMBAlertViewHelper * sharedCDIRemoteAlertViewHelper;


@implementation YMBAlertViewHelper
{
    BOOL isAlertViewShowing;
    UIAlertView * myAlertView;
    UIActivityIndicatorView * activityIndicatorView;
    NSTimer * timer;
    NSInteger timerCounter;
}

+ (YMBAlertViewHelper *)sharedCDIRemoteAlertViewHelper
{
    if (!sharedCDIRemoteAlertViewHelper) {
        sharedCDIRemoteAlertViewHelper = [[YMBAlertViewHelper alloc]init];
    }
    return sharedCDIRemoteAlertViewHelper;
}


- (id)init
{
    if (self = [super init]) {
        isAlertViewShowing = NO;
    }
    return self;
}

- (void)showAlertViewWithTitle:(NSString *)title
                    andMessage:(NSString *)message
            cancelButtonString:(NSString *)cancelButtonString
             otherButtonString:(NSString *)otherButtonString
{
    if (!isAlertViewShowing) {
        myAlertView = [[UIAlertView alloc] initWithTitle:title
                                               message:message
                                              delegate:self
                                     cancelButtonTitle:cancelButtonString
                                     otherButtonTitles:nil];
        
        [myAlertView show];
        isAlertViewShowing = YES;
    }
}

- (void)alertView:(UIAlertView *)alertView didDismissWithButtonIndex:(NSInteger)buttonIndex
{
    [self performSelector:@selector(setIsAlertViewShowingToNO) withObject:nil afterDelay:2.0];
    if ([alertView.title isEqualToString:@"Authentication Required"]) {
#pragma -mark 回到登陆界面
    }
}

- (void)setIsAlertViewShowingToNO
{
    isAlertViewShowing = NO;
}

- (void)showActivityIndicatorInView:(UIView *)view
{
    activityIndicatorView = [[UIActivityIndicatorView alloc]initWithFrame:CGRectMake([[UIScreen mainScreen]bounds].size.width / 2 - 32.0,
                                                                                     [[UIScreen mainScreen]bounds].size.height / 2 - 32.0,
                                                                                     64.0,
                                                                                     64.0)];
    activityIndicatorView.activityIndicatorViewStyle = UIActivityIndicatorViewStyleWhite;
    activityIndicatorView.hidesWhenStopped = YES;
    activityIndicatorView.backgroundColor = [UIColor blackColor];
    activityIndicatorView.layer.cornerRadius = 6;
    activityIndicatorView.layer.masksToBounds = YES;
    [view addSubview:activityIndicatorView];
    [activityIndicatorView startAnimating];
    view.userInteractionEnabled = NO;
}

- (void)stopActivityIndicator
{
    [activityIndicatorView stopAnimating];
    activityIndicatorView.superview.userInteractionEnabled = YES;
    [activityIndicatorView removeFromSuperview];
}


//- (void)setShowActivityIndicatorViewTimer
//{
//    timer = [NSTimer scheduledTimerWithTimeInterval:1.0 target:self selector:@selector(overTimer:) userInfo:nil repeats:YES];
//}
//
//- (void)overTimer:(NSTimer*)timer
//{
//    if (timerCounter >= 5) {
//        isOverTime = YES;
//        loginTime = 0.0;
//        [timer invalidate];
//        [loginWaitingView stopAnimating];
//        [self loginFailed];
//    }
//}

@end
