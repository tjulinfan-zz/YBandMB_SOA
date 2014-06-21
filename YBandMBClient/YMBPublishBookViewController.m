//
//  YMBPublishBookViewController.m
//  YBandMBClient
//
//  Created by Emerson on 14-6-19.
//  Copyright (c) 2014年 Emerson. All rights reserved.
//

#import "YMBPublishBookViewController.h"
#import "YMBServiceRequest.h"
#import "YMBUser.h"
@interface YMBPublishBookViewController ()
@property (weak, nonatomic) IBOutlet UITextField *priceTF;
@property (weak, nonatomic) IBOutlet UITextField *descriptionTF;
@property (weak, nonatomic) IBOutlet UITextField *ISBNTF;
@property (weak, nonatomic) IBOutlet UIButton *summitButton;

@end

@implementation YMBPublishBookViewController

- (id)initWithNibName:(NSString *)nibNameOrNil bundle:(NSBundle *)nibBundleOrNil
{
    self = [super initWithNibName:nibNameOrNil bundle:nibBundleOrNil];
    if (self) {
        // Custom initialization
    }
    return self;
}

- (void)viewDidLoad
{
    [super viewDidLoad];
    // Do any additional setup after loading the view.
    UITapGestureRecognizer * touch = [[UITapGestureRecognizer alloc]initWithTarget:self action:@selector(touchView)];
    [self.view addGestureRecognizer:touch];
}
- (IBAction)clickSummitButton:(id)sender {
    [YMBServiceRequest publishBookToSell:[YMBUser currentUser].sessionKey
                         WithDescription:_descriptionTF.text
                               withPrice:[_priceTF.text floatValue] withISBN:_ISBNTF.text];
}

- (void)didReceiveMemoryWarning
{
    [super didReceiveMemoryWarning];
    // Dispose of any resources that can be recreated.
}

-(void)touchView
{
    [self.view endEditing:YES];
}

/*
#pragma mark - Navigation

// In a storyboard-based application, you will often want to do a little preparation before navigation
- (void)prepareForSegue:(UIStoryboardSegue *)segue sender:(id)sender
{
    // Get the new view controller using [segue destinationViewController].
    // Pass the selected object to the new view controller.
}
*/

@end
