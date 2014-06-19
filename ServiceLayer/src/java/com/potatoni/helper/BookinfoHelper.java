/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.potatoni.helper;

import com.potatoni.entity.Bookinfo;
import com.potatoni.restclient.BookinfoRESTClient;

/**
 *
 * @author LinFan
 */
public class BookinfoHelper {
    
    public static Bookinfo getBookinfoByIsbn(String isbn) {
        BookinfoRESTClient client = new BookinfoRESTClient();
        Bookinfo bookinfo = client.find_JSON(Bookinfo.class, isbn);
        return bookinfo;
    }
    
}
