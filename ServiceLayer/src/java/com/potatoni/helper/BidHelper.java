/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.potatoni.helper;

import com.potatoni.entity.Bid;
import com.potatoni.restclient.BidRESTClient;
import java.util.Date;
import java.util.List;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;

/**
 *
 * @author LinFan
 */
public class BidHelper {
    
    private static WebTarget webTarget;
    private static Client client;
    private static final String BASE_URI = "http://localhost:9999/PersistenceLayer/webresources";
    
    private static void init() {
         client = javax.ws.rs.client.ClientBuilder.newClient();
         webTarget = client.target(BASE_URI).path("com.potatoni.entity.bid");
    }
    
    private static void close() {
        client.close();
    }
    
    public static List<Bid> getBidsByBookId(Integer bookId) {
        init();
        WebTarget resource = webTarget;
        resource = resource.path(java.text.MessageFormat.format("bookid/{0}", new Object[]{bookId}));
        GenericType<List<Bid>> bidsType = new GenericType<List<Bid>>() {};
        List<Bid> bids = resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(bidsType);
        close();
        return bids;
    }
    
    public static List<Bid> getBidsByUserId(Integer userId) {
        init();
        WebTarget resource = webTarget;
        resource = resource.path(java.text.MessageFormat.format("userid/{0}", new Object[]{userId}));
        GenericType<List<Bid>> bidsType = new GenericType<List<Bid>>() {};
        List<Bid> bids = resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(bidsType);
        close();
        return bids;
    }   
    
    public static Bid getBid(Integer bidId) {
        return new BidRESTClient().find_JSON(Bid.class, String.valueOf(bidId));
    }
    
    public static void updateBid(Bid bid) {
        BidRESTClient client = new BidRESTClient();
        client.edit_JSON(bid, String.valueOf(bid.getId()));
    }
    
/*----------------------------------Test code----------------------------------------------*/    
    public static void testGetBidsByBookId() {
        List<Bid> bids = getBidsByBookId(2);
        for (Bid bid : bids) {
            System.out.println(bid.getUserId());
        }
    }
    
    public static void testGetBidsByUserId() {
        List<Bid> bids = getBidsByUserId(3);
        for (Bid bid : bids) {
            System.out.println(bid.getBookId());
        }
    }
    
    public static void testUpdateBid() {
        Bid bid = BidHelper.getBid(3);
        bid.setPrice(333);
        bid.setBidDate(new Date());
        BidHelper.updateBid(bid);
    }
    
    public static void main(String[] args) {
        //testGetBidsByBookId();
        //testUpdateBid();
        testGetBidsByUserId();
    }
}
