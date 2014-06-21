//
//  YMBBookDetailInfoViewController.m
//  YBandMBClient
//
//  Created by Emerson on 14-6-19.
//  Copyright (c) 2014年 Emerson. All rights reserved.
//

#import "YMBBookDetailInfoViewController.h"
#import "YMBBookInfo.h"
#import "UIImageView+Addition.h"
#import "YMBServiceRequest.h"
#import "Soap.h"
@interface YMBBookDetailInfoViewController ()

@property (weak, nonatomic) IBOutlet UILabel *bookTitleLabel;
@property (weak, nonatomic) IBOutlet UIImageView *bookImageView;
@property (weak, nonatomic) IBOutlet UITextView *bookTextView;
@property (weak, nonatomic) IBOutlet UISegmentedControl *sellerSegmentControl;
@property (weak, nonatomic) IBOutlet UITextField *givePriveTF;

@end

@implementation YMBBookDetailInfoViewController
{
    NSString *currentElement;
    NSString *currentValue;
    NSMutableDictionary *rootDic;
    NSMutableArray *finalArray;
    NSArray *keyElements;
    NSArray *rootElements;
    
}

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
    // Do any additional setup after loading the view
    
    [_bookTitleLabel setText:_book.bookTitle];
    [_bookImageView loadImageFromURL:_book.imageurl completion:^(BOOL succeed){
        [self fadeInWithView:_bookImageView WithDuration:0.3 completion:nil];
    }];
    [_bookTextView setText:_book.summary];
    
    UITapGestureRecognizer * touch = [[UITapGestureRecognizer alloc]initWithTarget:self action:@selector(touchView)];
    [self.view addGestureRecognizer:touch];
    
    keyElements = [[NSArray alloc] initWithObjects:@"return", nil];
    rootElements = [[NSArray alloc] initWithObjects:@"return", nil];
    
    ResponseData * responseData = [YMBServiceRequest getBookByISBN:_book.isbn];
    NSXMLParser *xmlParser = [[NSXMLParser alloc] initWithData:
                              [responseData.Content dataUsingEncoding:NSUTF8StringEncoding]];
    [xmlParser setDelegate:self];
    [xmlParser parse];
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

- (void)fadeInWithView:(UIView *) view
          WithDuration:(float)duration
            completion:(void (^)(void))completion
{
    view.alpha = 0;
    [UIView animateWithDuration:duration delay:0 options:UIViewAnimationOptionCurveEaseInOut animations:^{
        view.alpha = 1;
    } completion:^(BOOL finished) {
        if(completion)
            completion();
    }];
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
    
    
}
#pragma - mark 结束解析
-(void)parserDidEndDocument:(NSXMLParser *)parser
{
    [_sellerSegmentControl removeAllSegments];
    for (int i = 0; i < [finalArray count] - 1; i ++) {
        NSString * title =[NSString stringWithFormat:@"价格 :%@",[[finalArray objectAtIndex:i]objectForKey:@"price"]];
        [_sellerSegmentControl insertSegmentWithTitle:title
                                              atIndex:[_sellerSegmentControl numberOfSegments] animated:YES];
    }
}

- (IBAction)clickBidButton:(id)sender {
    NSInteger selectedSegment;
    if ([_sellerSegmentControl selectedSegmentIndex] == -1) {
        selectedSegment = 0;
    }
    else {
        selectedSegment = [_sellerSegmentControl selectedSegmentIndex];
    }
    NSString * bookId = [[finalArray objectAtIndex:selectedSegment]objectForKey:@"id"];
    [YMBServiceRequest giveABindOnBookByBookId:bookId andPrice:[_givePriveTF.text floatValue]];
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
