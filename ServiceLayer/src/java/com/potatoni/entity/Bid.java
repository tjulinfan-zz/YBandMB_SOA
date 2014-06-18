/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.potatoni.entity;

import java.util.Date;

/**
 *
 * @author LinFan
 */
public class Bid {

    private Integer id;
    private int bookId;
    private int userId;
    private float price;
    private Date bidDate;

    public Bid() {
    }

    public Bid(Integer id) {
        this.id = id;
    }

    public Bid(int bookId, int userId, float price, Date bidDate) {
        this.bookId = bookId;
        this.userId = userId;
        this.price = price;
        this.bidDate = bidDate;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public Date getBidDate() {
        return bidDate;
    }

    public void setBidDate(Date bidDate) {
        this.bidDate = bidDate;
    }
}
