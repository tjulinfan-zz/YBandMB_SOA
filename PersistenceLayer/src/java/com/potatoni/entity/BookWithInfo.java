/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.potatoni.entity;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author LinFan
 */
public class BookWithInfo implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer id;
    private int ownerId;
    private float price;
    private String description;
    private Date soldDate;
    private Bookinfo info;

    public Bookinfo getInfo() {
        return info;
    }

    public void setInfo(Bookinfo info) {
        this.info = info;
    }

    public BookWithInfo() {
    }

    public BookWithInfo(Integer id) {
        this.id = id;
    }
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(int ownerId) {
        this.ownerId = ownerId;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getSoldDate() {
        return soldDate;
    }

    public void setSoldDate(Date soldDate) {
        this.soldDate = soldDate;
    }
    
}
