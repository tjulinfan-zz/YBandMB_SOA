/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.potatoni.entity;

import java.io.Serializable;

/**
 *
 * @author LinFan
 */
public class Bookinfo implements Serializable {
    private String doubanurl;
    private String summary;
    private String pubdate;
    private static final long serialVersionUID = 1L;
    private String isbn;
    private String title;
    private String author;
    private String imgurl;
    private String publisher;

    public Bookinfo() {
    }

    public Bookinfo(String isbn) {
        this.isbn = isbn;
    }

    public Bookinfo(String isbn, String title, String author, String imgurl) {
        this.isbn = isbn;
        this.title = title;
        this.author = author;
        this.imgurl = imgurl;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getImgurl() {
        return imgurl;
    }

    public void setImgurl(String imgurl) {
        this.imgurl = imgurl;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getPubdate() {
        return pubdate;
    }

    public void setPubdate(String pubdate) {
        this.pubdate = pubdate;
    }

    public String getDoubanurl() {
        return doubanurl;
    }

    public void setDoubanurl(String doubanurl) {
        this.doubanurl = doubanurl;
    }
    
}
