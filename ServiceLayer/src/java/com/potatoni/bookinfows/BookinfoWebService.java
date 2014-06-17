/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.potatoni.bookinfows;

import com.google.gson.Gson;
import com.potatoni.entity.Bookinfo;
import com.potatoni.entity.DoubanBook;
import com.potatoni.exception.InternalException;
import com.potatoni.exception.ServerNetworkException;
import com.potatoni.restclient.BookinfoRESTClient;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.ws.rs.ClientErrorException;

/**
 *
 * @author LinFan
 */
@WebService(serviceName = "BookinfoWebService")
public class BookinfoWebService {

    private static class STATUS {
        public static final int NOT_FOUND = 404;
    };
    
    /**
     * 添加新书
     */
    @WebMethod(operationName = "addNewBook")
    public void addNewBookinfo(@WebParam(name = "isbn") String isbn) throws ServerNetworkException, InternalException {
        final String url = "https://api.douban.com/v2/book/isbn/";
        final int TIME_OUT_MS = 5000;
        try {
            URL getURL = new URL(url + isbn);
            URLConnection conn = getURL.openConnection();
            conn.setConnectTimeout(TIME_OUT_MS);
            Gson gson = new Gson();
            DoubanBook dBook = gson.fromJson(new BufferedReader(new InputStreamReader(conn.getInputStream())), DoubanBook.class);
            
            if (dBook != null) {
                BookinfoRESTClient client = new BookinfoRESTClient();
                Bookinfo bookinfo = new Bookinfo();
                bookinfo.setIsbn(dBook.getIsbn13());
                bookinfo.setTitle(dBook.getTitle());
                bookinfo.setAuthor(dBook.getAuthor());
                bookinfo.setSummary(dBook.getSummary());
                bookinfo.setPubdate(dBook.getPubdate());
                bookinfo.setPublisher(dBook.getPublisher());
                bookinfo.setImgurl(dBook.getImage());
                bookinfo.setDoubanurl(dBook.getUrl());
                
                client.create_JSON(client);
                client.close();
            }
        } catch (MalformedURLException ex) {
            throw new ServerNetworkException();
        } catch (IOException ex) {
            throw new ServerNetworkException();
        } catch(Exception ex) {
            throw new InternalException();
        }
    }

    /**
     * Web 服务操作
     */
    @WebMethod(operationName = "isBookinfoExisting")
    public Boolean isBookinfoExisting(@WebParam(name = "isbn") String isbn) throws InternalException {
        BookinfoRESTClient client = new BookinfoRESTClient();
        try {
            Bookinfo bookinfo = client.find_JSON(Bookinfo.class, isbn);
            return bookinfo != null;
        } catch (ClientErrorException e) {
            if (e.getResponse().getStatus() == STATUS.NOT_FOUND)
                return false;
            throw new InternalException();
        } finally {
            client.close();
        }
    }
}
