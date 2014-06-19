/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.potatoni.helper;

import com.potatoni.entity.Book;
import com.potatoni.entity.OrderForm;
import com.potatoni.restclient.BookRESTClient;
import com.potatoni.restclient.OrderRESTClient;
import java.util.Date;

/**
 *
 * @author LinFan
 */
public class OrderHelper {
    
    public static void genOrder(OrderForm order) {
        OrderRESTClient client = new OrderRESTClient();
        client.create_JSON(order);
        client.close();
        
        BookRESTClient bookClient = new BookRESTClient();
        Book book = bookClient.find_JSON(Book.class, String.valueOf(order.getBookId()));
        book.setSoldDate(order.getSoldDate());
        bookClient.edit_JSON(book, String.valueOf(book.getId()));
        bookClient.close();
    }
    
    public static void testGenOrder() {
        OrderForm order = new OrderForm();
        order.setBookId(4);
        order.setCustomerId(23);
        order.setSellerId(24);
        order.setPrice(50);
        order.setSoldDate(new Date());
        
        genOrder(order);
    }
    
    public static void main(String[] args) {
        testGenOrder();
    }
    
}
