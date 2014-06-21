//
//  YMBViewController.m
//  YBandMBClient
//
//  Created by Emerson on 14-6-18.
//  Copyright (c) 2014年 Emerson. All rights reserved.
//

#import "YMBViewController.h"
#import "Soap.h"
#import "YMBServiceRequest.h"
#import "YMBAlertViewHelper.h"
#import "YMBMainViewController.h"
#import "YMBUser.h"

@interface YMBViewController ()
@property (weak, nonatomic) IBOutlet UITextField *userNameTF;
@property (weak, nonatomic) IBOutlet UITextField *passwordTF;
@property (weak, nonatomic) IBOutlet UITextField *phoneNumberTF;
@property (weak, nonatomic) IBOutlet UITextField *addressTF;
@property (weak, nonatomic) IBOutlet UITextField *emailTF;
@property (weak, nonatomic) IBOutlet UIButton *loginButton;
@property (weak, nonatomic) IBOutlet UIButton *registerButton;

@end

@implementation YMBViewController
{
    BOOL isReadyJumped;
    NSString *currentElement;
    NSString *currentValue;
    NSMutableDictionary *rootDic;
    NSMutableArray *finalArray;
    NSArray *keyElements;
    NSArray *rootElements;
}
- (void)viewDidLoad
{
    [super viewDidLoad];
	// Do any additional setup after loading the view, typically from a nib.
    UITapGestureRecognizer * touch = [[UITapGestureRecognizer alloc]initWithTarget:self action:@selector(touchView)];
    [self.view addGestureRecognizer:touch];
    
    keyElements = [[NSArray alloc] initWithObjects:@"return", nil];
    rootElements = [[NSArray alloc] initWithObjects:@"sessionId",@"userid", nil];
    isReadyJumped = NO;
}

- (void)didReceiveMemoryWarning
{
    [super didReceiveMemoryWarning];
    // Dispose of any resources that can be recreated.
}

- (void)registerNewUserisSync:(BOOL)isSync
{
//    ResponseData * responseData =
    [YMBServiceRequest registerNewUserWithUserName:_userNameTF.text
                                      password:_passwordTF.text
                                         email:_emailTF.text
                                   phoneNumber:_phoneNumberTF.text
                                           address:_addressTF.text];
//    NSXMLParser *xmlParser = [[NSXMLParser alloc] initWithData:responseData];
//    [xmlParser setDelegate:self];
    
}

-(void)touchView
{
    [self.view endEditing:YES];
}

- (void)logIn:(BOOL)isSync
{
    ResponseData * responseData =
    [YMBServiceRequest logInWithUserName:_userNameTF.text
                                password:_passwordTF.text];
    NSXMLParser *xmlParser = [[NSXMLParser alloc] initWithData:
                              [responseData.Content dataUsingEncoding:NSUTF8StringEncoding]];
    [xmlParser setDelegate:self];
    [xmlParser parse];
}

- (IBAction)clickLoginButton:(id)sender {
    [self logIn:YES];
}

- (IBAction)clickRegisterButton:(id)sender {
    [self registerNewUserisSync:YES];
}

#pragma mark - xml Delegate
#pragma - mark 开始解析时
-(void)parserDidStartDocument:(NSXMLParser *)parser
{
    finalArray = [[NSMutableArray alloc] init];
}
#pragma - mark 发现节点时
-(void)parser:(NSXMLParser *)parser didStartElement:(NSString *)elementName namespaceURI:(NSString *)namespaceURI qualifiedName:(NSString *)qName attributes:(NSDictionary *)attributeDict
{
    for(NSString *key in keyElements){
        if ([elementName isEqualToString:key]) {
            rootDic = nil;
            rootDic = [[NSMutableDictionary alloc] initWithCapacity:0];
            
        }
        else {
            for(NSString *element in rootElements){
                if ([element isEqualToString:element]) {
                    currentElement = elementName;
                    currentValue = [NSString string];
                }
            }
        }
    }
    
}
#pragma - mark 发现节点值时

-(void)parser:(NSXMLParser *)parser foundCharacters:(NSString *)string
{
    if (currentElement) {
        currentValue = string;
        [rootDic setObject:string forKey:currentElement];
    }
    
}
#pragma - mark 结束节点时
-(void)parser:(NSXMLParser *)parser didEndElement:(NSString *)elementName namespaceURI:(NSString *)namespaceURI qualifiedName:(NSString *)qName
{
    if (currentElement) {
        [rootDic setObject:currentValue forKey:currentElement];
        currentElement = nil;
        currentValue = nil;
    }
    for(NSString *key in keyElements){
        
        if ([elementName isEqualToString:key]) {
            if (rootDic) {
                [finalArray addObject:rootDic];
            }
        }
    }
    
    if ([[rootDic objectForKey:@"sessionId"] isEqualToString:@"NOUSER"]) {
        YMBAlertViewHelper * alertViewHelper = [YMBAlertViewHelper sharedCDIRemoteAlertViewHelper];
        [alertViewHelper showAlertViewWithTitle:@""
                                     andMessage:@"用户不存在"
                             cancelButtonString:@"好"
                              otherButtonString:nil];

    }
    else if ([[rootDic objectForKey:@"sessionId"] isEqualToString:@"PASSWORDWRONG"]) {
        YMBAlertViewHelper * alertViewHelper = [YMBAlertViewHelper sharedCDIRemoteAlertViewHelper];
        [alertViewHelper showAlertViewWithTitle:@""
                                     andMessage:@"密码错误"
                             cancelButtonString:@"好"
                              otherButtonString:nil];
    }
    else {
        if (!isReadyJumped) {
            [YMBUser setCurrentUserWithUserId:[rootDic objectForKey:@"userid"]
                                 andSessionKey:[rootDic objectForKey:@"sessionId"]];
            [self performSegueWithIdentifier:@"toMainView" sender:self];
            isReadyJumped = YES;
        }
    }
}
#pragma - mark 结束解析
-(void)parserDidEndDocument:(NSXMLParser *)parser
{
    isReadyJumped = NO;
}

@end
