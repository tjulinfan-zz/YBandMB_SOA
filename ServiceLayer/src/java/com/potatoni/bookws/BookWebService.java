/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.potatoni.bookws;

import com.potatoni.entity.Book;
import com.potatoni.entity.BookWithInfo;
import com.potatoni.entity.Bookinfo;
import com.potatoni.entity.Session;
import com.potatoni.exception.BookNotExistingException;
import com.potatoni.exception.InternalException;
import com.potatoni.exception.InvalidSessionException;
import com.potatoni.exception.PermissionDeniedException;
import com.potatoni.exception.UserNotLoggedInException;
import com.potatoni.helper.BookHelper;
import com.potatoni.helper.BookinfoHelper;
import com.potatoni.helper.SessionHelper;
import com.potatoni.restclient.BookRESTClient;
import com.potatoni.restclient.BookinfoRESTClient;
import com.potatoni.restclient.SessionRESTClient;
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
@WebService(serviceName = "BookWebService")
public class BookWebService {

    private static class STATUS {
        public static final int NOT_FOUND = 404;
    };
    
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

    /**
     * 通过书名搜索书籍
     */
    @WebMethod(operationName = "searchBookByName")
    public List<BookWithInfo> searchBooksByName(
            @WebParam(name = "name") String name) throws InternalException {
        ArrayList<BookWithInfo> rets = new ArrayList<BookWithInfo>();
        try {
            List<Book> books = BookHelper.getBooksByName(name);
            for (Book book : books) {
                Bookinfo info = BookinfoHelper.getBookinfoByIsbn(book.getIsbn());
                rets.add(new BookWithInfo(book, info));
            }
        } catch (ClientErrorException e) {
            throw new InternalException();
        }
        return rets;
    }

    /**
     * 获取Book信息
     */
    @WebMethod(operationName = "getBook")
    public Book getBook(@WebParam(name = "bookid") int bookid) throws InternalException, BookNotExistingException {
        BookRESTClient client = new BookRESTClient();
        try {
            return client.find_JSON(Book.class, String.valueOf(bookid));
        } catch (ClientErrorException e) {
            if (e.getResponse().getStatus() == STATUS.NOT_FOUND) {
                throw new BookNotExistingException();
            }
            throw new InternalException();
        } finally {
            client.close();
        }
    }

    /**
     * 获取所有Book
     */
    @WebMethod(operationName = "getAllBooks")
    public List<BookWithInfo> getAllBooks() throws InternalException {
        List<BookWithInfo> rets = new ArrayList<BookWithInfo>();
        try {
            List<Book> books = BookHelper.getAllBooks();
            for (Book book : books) {
                Bookinfo bookinfo = BookinfoHelper.getBookinfoByIsbn(book.getIsbn());
                rets.add(new BookWithInfo(book, bookinfo));
            }
        } catch (ClientErrorException e) {
            throw new InternalException();
        }
        return rets;
    }

    /**
     * 获取自己的Book
     */
    @WebMethod(operationName = "getOwnedBooks")
    public List<BookWithInfo> getOwnedBooks(@WebParam(name = "sessionId") String sessionId) throws InternalException, UserNotLoggedInException {
        List<BookWithInfo> rets = new ArrayList<BookWithInfo>();
        try {
            int userId = SessionHelper.getUserId(sessionId);
            List<Book> books = BookHelper.getOwnedBooks(userId);
            for (Book book : books) {
                Bookinfo bookinfo = BookinfoHelper.getBookinfoByIsbn(book.getIsbn());
                rets.add(new BookWithInfo(book, bookinfo));
            }
        } catch (InvalidSessionException ex) {
            throw new UserNotLoggedInException();
        } catch (ClientErrorException e) {
            throw new InternalException();
        }
        return rets;
    }

    /**
     * 根据ISBN搜索书籍
     */
    @WebMethod(operationName = "searchBookByIsbn")
    public List<BookWithInfo> searchBookByIsbn(@WebParam(name = "isbn") String isbn) throws InternalException {
        List<BookWithInfo> rets = new ArrayList<BookWithInfo>();
        try {
            List<Book> books = BookHelper.getBooksByIsbn(isbn);
            for (Book book : books) {
                Bookinfo bookinfo = BookinfoHelper.getBookinfoByIsbn(book.getIsbn());
                rets.add(new BookWithInfo(book, bookinfo));
            }
        } catch (ClientErrorException e) {
            throw new InternalException();
        }
        return rets;
    }

    

}
