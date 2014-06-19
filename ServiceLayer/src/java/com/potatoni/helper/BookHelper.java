/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.potatoni.helper;

import com.potatoni.entity.Book;
import com.potatoni.restclient.BookRESTClient;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;

/**
 *
 * @author LinFan
 */
public class BookHelper {
    
    private static WebTarget webTarget;
    private static Client client;
    private static final String BASE_URI = "http://localhost:9999/PersistenceLayer/webresources";
    
    private static void init() {
         client = javax.ws.rs.client.ClientBuilder.newClient();
         webTarget = client.target(BASE_URI).path("com.potatoni.entity.book");
    }
    
    private static void close() {
        client.close();
    }
    
    public static Book getBook(Integer bookId) {
        BookRESTClient client = new BookRESTClient();
        Book book = client.find_JSON(Book.class, String.valueOf(bookId));
        return book;
    }
    
    //getAllBooks
    public static List<Book> getAllBooks() {
        init();
        WebTarget resource = webTarget;
        GenericType<List<Book>> booksType = new GenericType<List<Book>>() {};
        List<Book> books = resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(booksType);
        
        HashMap<String, Book> hashMap = new HashMap<String, Book>();
        for (Book book : books) {
            if (!hashMap.containsKey(book.getIsbn())) {
                hashMap.put(book.getIsbn(), book);
            }
        }
        
        List<Book> ret = new ArrayList<Book>();
        Iterator it = hashMap.keySet().iterator();
        while (it.hasNext()) {
            String key = (String) it.next();
            ret.add(hashMap.get(key));
        }
        close();
        return ret;
    }
    
    public static List<Book> getBooksByName(String name) {
        init();
        WebTarget resource = webTarget;
        resource = resource.path(java.text.MessageFormat.format("name/{0}", new Object[]{name}));
        GenericType<List<Book>> booksType = new GenericType<List<Book>>() {};
        List<Book> ret = resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(booksType);
        close();
        return ret;
    }
    
    public static List<Book> getBooksByIsbn(String isbn) {
        init();
        WebTarget resource = webTarget;
        resource = resource.path(java.text.MessageFormat.format("isbn/{0}", new Object[]{isbn}));
        GenericType<List<Book>> booksType = new GenericType<List<Book>>() {};
        List<Book> ret = resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(booksType);
        close();
        return ret;
    }
    
    public static void main(String[] args) {
        /*testGetAllBooks*/
//        List<Book> books = getAllBooks();
//        for (Book book : books)
//            System.out.println(book.getIsbn());
        
        /*testGetBooksByIsbn*/
        List<Book> books = getBooksByIsbn("9780375842207");
        System.out.println(books.size());
    }
    
    public static List<Book> getOwnedBooks(Integer ownerId) {
        init();
        WebTarget resource = webTarget;
        resource = resource.path(java.text.MessageFormat.format("ownerid/{0}", new Object[]{ownerId}));
        GenericType<List<Book>> booksType = new GenericType<List<Book>>() {};
        List<Book> ret = resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(booksType);
        close();
        return ret;
    }
}
