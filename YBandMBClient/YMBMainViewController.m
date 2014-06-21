//
//  YMBMainViewController.m
//  YBandMBClient
//
//  Created by Emerson on 14-6-18.
//  Copyright (c) 2014年 Emerson. All rights reserved.
//

#import "YMBMainViewController.h"
#import "YMBPersonalCenterViewController.h"
#import "YMBMainTableViewCell.h"
#import "YMBServiceRequest.h"
#import "YMBBookDetailInfoViewController.h"
#import "Soap.h"
#import "UIImageView+Addition.h"
#import "YMBBookInfo.h"

@interface YMBMainViewController ()
@property (weak, nonatomic) IBOutlet UITextField *searchTF;
@property (weak, nonatomic) IBOutlet UIButton *personalCenterButton;
@property (weak, nonatomic) IBOutlet UITableView *allBookTableView;

@end

@implementation YMBMainViewController
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
    // Do any additional setup after loading the view.
    UITapGestureRecognizer * touch = [[UITapGestureRecognizer alloc]initWithTarget:self action:@selector(touchView)];
    [self.view addGestureRecognizer:touch];

    keyElements = [[NSArray alloc] initWithObjects:@"return", nil];
    rootElements = [[NSArray alloc] initWithObjects:@"return", nil];
    
    ResponseData * responseData = [YMBServiceRequest getAllBook];
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

- (IBAction)clickPersonalButton:(id)sender {
    [self performSegueWithIdentifier:@"personalCenter" sender:self];
}

#pragma mark - tableView delegate and datasource
- (NSInteger)numberOfSectionsInTableView:(UITableView *)tableView
{
    return 1;
}

- (NSInteger)tableView:(UITableView *)tableView numberOfRowsInSection:(NSInteger)section
{
    return [finalArray count] - 1;
}

- (UITableViewCell *)tableView:(UITableView *)tableView cellForRowAtIndexPath:(NSIndexPath *)indexPath
{
    static NSString *CellIdentifier = @"AllBookTableCell";
    YMBMainTableViewCell *cell = (YMBMainTableViewCell *)[tableView dequeueReusableCellWithIdentifier:CellIdentifier forIndexPath:indexPath];
    cell.bookInfo = [[YMBBookInfo alloc]init];
    cell.bookInfo.bookId = [[finalArray objectAtIndex:[indexPath row]] objectForKey:@"id"];
    cell.bookInfo.isbn = [[finalArray objectAtIndex:[indexPath row]] objectForKey:@"isbn"];
    cell.bookTitle.text = [[finalArray objectAtIndex:[indexPath row]] objectForKey:@"title"];
    cell.bookAuthor.text =  [[finalArray objectAtIndex:[indexPath row]] objectForKey:@"author"];
    cell.bookPublisher.text =  [[finalArray objectAtIndex:[indexPath row]] objectForKey:@"publisher"];
    
    cell.bookInfo.bookTitle = cell.bookTitle.text;
    cell.bookInfo.author = cell.bookAuthor.text;
    cell.bookInfo.bookDescription =
    [[finalArray objectAtIndex:[indexPath row]] objectForKey:@"description"];
    cell.bookInfo.publisher = cell.bookPublisher.text;
    cell.bookInfo.imageurl = [[finalArray objectAtIndex:[indexPath row]] objectForKey:@"imgurl"];
    cell.bookInfo.summary = [[finalArray objectAtIndex:[indexPath row]] objectForKey:@"summary"];

    NSString * imageUrl = [[finalArray objectAtIndex:[indexPath row]] objectForKey:@"imgurl"];
    [cell.bookImageView loadImageFromURL:imageUrl completion:^(BOOL succeed){
        [self fadeInWithView:cell.bookImageView WithDuration:0.3 completion:nil];
    }];
    return cell;
}

- (void)tableView:(UITableView *)tableView didSelectRowAtIndexPath:(NSIndexPath *)indexPath {
    [tableView deselectRowAtIndexPath:indexPath animated:YES];
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
    [_allBookTableView reloadData];
}

- (void)prepareForSegue:(UIStoryboardSegue *)segue sender:(id)sender
{
    if ([[segue identifier]isEqualToString:@"bookInfoDetail"]) {
        YMBMainTableViewCell *cell = (YMBMainTableViewCell *)sender;
        YMBBookDetailInfoViewController * detailViewController = (YMBBookDetailInfoViewController *)[segue destinationViewController];
        detailViewController.book = cell.bookInfo;
    }
}

@end
