/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.potatoni.bookws;

import com.potatoni.entity.Book;
import com.potatoni.entity.Session;
import com.potatoni.exception.InternalException;
import com.potatoni.restclient.BookRESTClient;
import com.potatoni.restclient.SessionRESTClient;
import java.util.Date;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.ws.rs.ClientErrorException;

/**
 *
 * @author LinFan
 */
@WebService(serviceName = "BookWebService")
public class BookWebService {

    /**
     * 添加新拍卖书籍
     */
    @WebMethod(operationName = "publishBookToSell")
    public void publishBookToSell(
            @WebParam(name = "sessionkey") String sessionkey,
            @WebParam(name = "isbn") String isbn,
            @WebParam(name = "description") String description,
            @WebParam(name = "price") float price
            ) throws InternalException {
        BookRESTClient client = new BookRESTClient();
        SessionRESTClient sessionClient = new SessionRESTClient();
        try {
            Session session = sessionClient.find_JSON(Session.class, sessionkey);
            Book newBook = new Book(session.getUserid(), price, description, isbn);
            client.create_JSON(newBook);
        } catch (ClientErrorException e) {
            throw new InternalException(String.valueOf(e.getResponse().getStatus()));
        } finally {
            client.close();
            sessionClient.close();
        }
    }

    /**
     * Web 服务操作
     */
    @WebMethod(operationName = "setBookSold")
    public void setBookSold(
            @WebParam(name = "bookId") int bookId,
            @WebParam(name = "soldDate") Date soldDate,
            @WebParam(name = "sessionkey") String sessionkey) throws InternalException {
        BookRESTClient client = new BookRESTClient();
        Book soldBook = client.find_JSON(Book.class, String.valueOf(bookId));
        soldBook.setSoldDate(soldDate);
        try {
            client.create_JSON(soldBook);
        } catch (ClientErrorException e) {
            throw new InternalException(String.valueOf(e.getResponse().getStatus()));
        } finally {
            client.close();
        }
    }

}
