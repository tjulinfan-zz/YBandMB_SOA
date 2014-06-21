//
//  YMBServiceASK.m
//  YBandMBClient
//
//  Created by Emerson on 14-6-18.
//  Copyright (c) 2014年 Emerson. All rights reserved.
//

#import "YMBServiceRequest.h"
#import "Soap.h"
#import "YMBUser.h"

@implementation YMBServiceRequest

+ (ResponseData *)sendRequestWithSoapRequest:(SoapService *)soaprequest
                                 andPostData:(NSString *)postData
{
    NSLog(@"The postData is %@",postData);
    ResponseData *result= [soaprequest PostSync:postData];
    NSLog(@"%@",result.Content);
    return result;
}

+ (ResponseData *)registerNewUserWithUserName:(NSString *)userName
                           password:(NSString *)password
                              email:(NSString *)email
                        phoneNumber:(NSString *)phoneNumber
                            address:(NSString *)address
{
    NSDictionary *dic=@{@"username": userName,
                        @"password": password,
                        @"email": email,
                        @"phonenumber": phoneNumber,
                        @"address": address};
    
    NSString *methodName=@"registerUser";
    SoapUtility *soaputility=[[SoapUtility alloc] initFromFile:@"user"];
    NSString *postData=[soaputility BuildSoapwithMethodName:methodName
                                               WithWSDLName:@"user"
                                                  withParas:dic
                                             withtargetName:@"http://userws.potatoni.com/"
                                          withNamespaceName:@"user"];
    
    SoapService *soaprequest=[[SoapService alloc] init];
    soaprequest.PostUrl=@"http://localhost:9080/UserRegisterWSDLService/UserRegisterWSDLPort";
    soaprequest.SoapAction=[soaputility GetSoapActionByMethodName:methodName SoapType:SOAP];
    
    return [YMBServiceRequest sendRequestWithSoapRequest:soaprequest
                                      andPostData:postData];
}

+ (ResponseData *)logInWithUserName:(NSString *)userName password:(NSString *)password
{
    NSDictionary *dic=@{@"username": userName,
                        @"password": password};
    
    NSString *methodName=@"logIn";
    SoapUtility *soaputility=[[SoapUtility alloc] initFromFile:@"user"];
    NSString *postData=[soaputility BuildSoapwithMethodName:methodName
                                               WithWSDLName:@"user"
                                                  withParas:dic
                        withtargetName:@"http://userws.potatoni.com/"
                                          withNamespaceName:@"user"];
    
    SoapService *soaprequest=[[SoapService alloc] init];
    soaprequest.PostUrl=@"http://localhost:9080/UserLoginWSDLService/UserLoginWSDLPort";
    soaprequest.SoapAction=[soaputility GetSoapActionByMethodName:methodName SoapType:SOAP];
    
    return [YMBServiceRequest sendRequestWithSoapRequest:soaprequest
                                      andPostData:postData];

}

+ (ResponseData *)getAllBook
{
    NSDictionary *dic=@{};
    
    NSString *methodName=@"getAllBooks";
    SoapUtility *soaputility=[[SoapUtility alloc] initFromFile:@"book"];
    NSString *postData=[soaputility BuildSoapwithMethodName:methodName
                                               WithWSDLName:@"book"
                                                  withParas:dic
                                             withtargetName:@"http://bookws.potatoni.com/"
                                          withNamespaceName:@"book"];
    
    SoapService *soaprequest=[[SoapService alloc] init];
    soaprequest.PostUrl=@"http://localhost:9080/BookGetAllProcessService/BookGetAllProcessPort";
    soaprequest.SoapAction=[soaputility GetSoapActionByMethodName:methodName SoapType:SOAP];
    
    return [YMBServiceRequest sendRequestWithSoapRequest:soaprequest
                                             andPostData:postData];
    
}

+ (ResponseData *)getAllBookByOwnerSession:(NSString *)session
{
    NSDictionary *dic=@{@"sessionId": session};
    
    NSString *methodName=@"getOwnedBooks";
    SoapUtility *soaputility=[[SoapUtility alloc] initFromFile:@"book"];
    NSString *postData=[soaputility BuildSoapwithMethodName:methodName
                                               WithWSDLName:@"book"
                                                  withParas:dic
                                             withtargetName:@"http://bookws.potatoni.com/"
                                          withNamespaceName:@"book"];
    
    SoapService *soaprequest=[[SoapService alloc] init];
    soaprequest.PostUrl=@"http://localhost:9080/BookGetByOwnerWSDLService/BookGetByOwnerWSDLPort";
    soaprequest.SoapAction=[soaputility GetSoapActionByMethodName:methodName SoapType:SOAP];
    
    return [YMBServiceRequest sendRequestWithSoapRequest:soaprequest
                                             andPostData:postData];
    
}

+ (ResponseData *)publishBookToSell:(NSString *)session
                    WithDescription:(NSString *)description
                          withPrice:(float)price
                           withISBN:(NSString *)isbn
{
    NSDictionary *dic=@{@"sessionId": session,
                        @"isbn": isbn,
                        @"description": description,
                        @"price": [NSString stringWithFormat:@"%f",price]};
    
    NSString *methodName=@"publishBookToSell";
    SoapUtility *soaputility=[[SoapUtility alloc] initFromFile:@"book"];
    NSString *postData=[soaputility BuildSoapwithMethodName:methodName
                                               WithWSDLName:@"book"
                                                  withParas:dic
                                             withtargetName:@"http://bookws.potatoni.com/"
                                          withNamespaceName:@"book"];
    
    SoapService *soaprequest=[[SoapService alloc] init];
    soaprequest.PostUrl=@"http://localhost:9080/BookPublishWSDLService/BookPublishWSDLPort";
    soaprequest.SoapAction=[soaputility GetSoapActionByMethodName:methodName SoapType:SOAP];
    
    return [YMBServiceRequest sendRequestWithSoapRequest:soaprequest
                                             andPostData:postData];
    
}

+ (ResponseData *)getBookByISBN:(NSString *)isbn
{
    NSDictionary *dic=@{@"isbn": isbn};
    
    NSString *methodName=@"searchBookByIsbn";
    SoapUtility *soaputility=[[SoapUtility alloc] initFromFile:@"book"];
    NSString *postData=[soaputility BuildSoapwithMethodName:methodName
                                               WithWSDLName:@"book"
                                                  withParas:dic
                                             withtargetName:@"http://bookws.potatoni.com/"
                                          withNamespaceName:@"book"];
    
    SoapService *soaprequest=[[SoapService alloc] init];
    soaprequest.PostUrl=@"http://localhost:9080/BookGetBookByISBNService/BookGetBookByISBNPort";
    soaprequest.SoapAction=[soaputility GetSoapActionByMethodName:methodName SoapType:SOAP];
    
    return [YMBServiceRequest sendRequestWithSoapRequest:soaprequest
                                             andPostData:postData];
}

+ (ResponseData *)giveABindOnBookByBookId:(NSString *)bookId andPrice:(float)price
{
    NSDictionary *dic=@{@"sessionId": [YMBUser currentUser].sessionKey,
                        @"bookId": bookId,
                        @"price": [NSString stringWithFormat:@"%f",price]};
    
    NSString *methodName=@"bidABook";
    SoapUtility *soaputility=[[SoapUtility alloc] initFromFile:@"bid"];
    NSString *postData=[soaputility BuildSoapwithMethodName:methodName
                                               WithWSDLName:@"bid"
                                                  withParas:dic
                                             withtargetName:@"http://bidws.potatoni.org/"
                                          withNamespaceName:@"bid"];
    
    SoapService *soaprequest=[[SoapService alloc] init];
    soaprequest.PostUrl=@"http://localhost:9080/BidBookWSDLService/BidBookWSDLPort";
    soaprequest.SoapAction=[soaputility GetSoapActionByMethodName:methodName SoapType:SOAP];
    
    return [YMBServiceRequest sendRequestWithSoapRequest:soaprequest
                                             andPostData:postData];
}

+ (ResponseData *)getBidsByUsers
{
    NSDictionary *dic=@{@"sessionId": [YMBUser currentUser].sessionKey};
    
    NSString *methodName=@"getAllBidsOfAUser";
    SoapUtility *soaputility=[[SoapUtility alloc] initFromFile:@"bid"];
    NSString *postData=[soaputility BuildSoapwithMethodName:methodName
                                               WithWSDLName:@"bid"
                                                  withParas:dic
                                             withtargetName:@"http://bidws.potatoni.org/"
                                          withNamespaceName:@"bid"];
    
    SoapService *soaprequest=[[SoapService alloc] init];
    soaprequest.PostUrl=@"http://localhost:9080/BidsGetAllBidsProcessService/BidsGetAllBidsProcessPort";
    soaprequest.SoapAction=[soaputility GetSoapActionByMethodName:methodName SoapType:SOAP];
    
    return [YMBServiceRequest sendRequestWithSoapRequest:soaprequest
                                             andPostData:postData];
}
@end
