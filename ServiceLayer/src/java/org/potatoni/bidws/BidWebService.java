/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.potatoni.bidws;

import com.potatoni.bookws.BookWebService;
import com.potatoni.entity.Bid;
import com.potatoni.entity.Book;
import com.potatoni.entity.User;
import com.potatoni.exception.InternalException;
import com.potatoni.exception.InvalidSessionException;
import com.potatoni.exception.PermissionDeniedException;
import com.potatoni.exception.UserNotExistingException;
import com.potatoni.exception.UserNotLoggedInException;
import com.potatoni.helper.BidHelper;
import com.potatoni.helper.BookHelper;
import com.potatoni.helper.SessionHelper;
import com.potatoni.helper.UserHelper;
import com.potatoni.restclient.BidRESTClient;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.ws.rs.ClientErrorException;

/**
 *
 * @author LinFan
 */
@WebService(serviceName = "BidWebService")
public class BidWebService {

    /**
     * 添加书籍竞价
     */
    @WebMethod(operationName = "bidABook")
    public void bidABook(
            @WebParam(name = "sessionId") String sessionId,
            @WebParam(name = "bookId") int bookId,  
            @WebParam(name = "price") float price) throws InternalException, UserNotExistingException {
         BidRESTClient client = new BidRESTClient();
        try {
            int userId = SessionHelper.getUserId(sessionId);
            Bid newBid = new Bid(bookId, userId, price, new Date());
            client.create_JSON(newBid);
        } catch (ClientErrorException e) {
            throw new InternalException(String.valueOf(e.getResponse().getStatus()));
        } catch (InvalidSessionException ex) {
            throw new UserNotExistingException();
        } finally {
            client.close();
        }
    }
    
    /**
     * 获得一本书的全部竞价
     */
    @WebMethod(operationName = "getAllBids")
    public List<Bid> getAllBids(@WebParam(name = "sessionId") String sessionId, @WebParam(name = "bookId") Integer bookId) throws InternalException, PermissionDeniedException, UserNotLoggedInException {
        try {
            int userId = SessionHelper.getUserId(sessionId);
            Book book = BookHelper.getBook(bookId);
            if (book.getOwnerId() != userId)
                throw new PermissionDeniedException();
            return BidHelper.getBidsByBookId(bookId);
        } catch (InvalidSessionException ex) {
            throw new UserNotLoggedInException();
        } catch (ClientErrorException e) {
            throw new InternalException();
        }
    }
    
    /**
     * 获取一个人的所有竞价
     */
    @WebMethod(operationName = "getAllBidsOfAUser")
    public List<Bid> getAllBidsOfAUser(@WebParam(name = "sessionId") String sessionId) throws InternalException, UserNotLoggedInException {
        try {
            int userId = SessionHelper.getUserId(sessionId);
            return BidHelper.getBidsByUserId(userId);
        } catch (InvalidSessionException ex) {
            throw new UserNotLoggedInException();
        } catch (ClientErrorException ex) {
            throw new InternalException();
        }
    }

    /**
     * Web 服务操作
     */
    @WebMethod(operationName = "getHighestBidPrice")
    public Double getHighestBidPrice(@WebParam(name = "bookId") int bookId) throws InternalException {
        List<Bid> bids = BidHelper.getBidsByBookId(bookId);
        double pri = 0;
        for (Bid bid : bids) {
            if (bid.getPrice() > pri)
                pri = bid.getPrice();
        }
        return pri;
    }

    /**
     * 更新竞价
     */
    @WebMethod(operationName = "updateBidPrice")
    public void updateBidPrice(@WebParam(name = "sessionId") String sessionId, @WebParam(name = "bidId") int bidId, @WebParam(name = "price") float price) throws InternalException, PermissionDeniedException, UserNotLoggedInException {
        try {
            int userId = SessionHelper.getUserId(sessionId);
            Bid bid = BidHelper.getBid(bidId);
            if (bid.getUserId() != userId)
                throw new PermissionDeniedException();
            bid.setPrice(price);
            bid.setBidDate(new Date());
            BidHelper.updateBid(bid);
        } catch (InvalidSessionException ex) {
            throw new UserNotLoggedInException();
        } catch (ClientErrorException e) {
            throw new InternalException();
        } 
    }

    /**
     * Web 服务操作
     */
    @WebMethod(operationName = "getAllCustomers")
    public List<User> getAllCustomers(@WebParam(name = "bookId") Integer bookId) throws InternalException {
        try {
            List<Bid> bids = BidHelper.getBidsByBookId(bookId);
            List<User> users = new ArrayList<User>();
            for (Bid bid : bids) {
                users.add(UserHelper.getUser(bid.getUserId()));
            }
            return users;
        } catch (ClientErrorException e) {
            throw new InternalException();
        }
    }

    
}
