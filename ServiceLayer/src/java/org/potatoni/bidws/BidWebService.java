/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.potatoni.bidws;

import com.potatoni.entity.Bid;
import com.potatoni.exception.InternalException;
import com.potatoni.restclient.BidRESTClient;
import java.util.Date;
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
            @WebParam(name = "bookId") int bookId, 
            @WebParam(name = "userId") int userId, 
            @WebParam(name = "price") float price) throws InternalException {
         BidRESTClient client = new BidRESTClient();
         Date currentDate = new Date();
        Bid newBid = new Bid(bookId,userId,price,currentDate);
        try {
            client.create_JSON(newBid);
        } catch (ClientErrorException e) {
            throw new InternalException(String.valueOf(e.getResponse().getStatus()));
        } finally {
            client.close();
        }
    }
}
