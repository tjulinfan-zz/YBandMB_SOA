/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.potatoni.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author LinFan
 */
@Entity
@Table(name = "order")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "OrderForm.findAll", query = "SELECT o FROM OrderForm o"),
    @NamedQuery(name = "OrderForm.findById", query = "SELECT o FROM OrderForm o WHERE o.id = :id"),
    @NamedQuery(name = "OrderForm.findByCustomerId", query = "SELECT o FROM OrderForm o WHERE o.customerId = :customerId"),
    @NamedQuery(name = "OrderForm.findByPrice", query = "SELECT o FROM OrderForm o WHERE o.price = :price"),
    @NamedQuery(name = "OrderForm.findBySoldDate", query = "SELECT o FROM OrderForm o WHERE o.soldDate = :soldDate"),
    @NamedQuery(name = "OrderForm.findByLogisticsId", query = "SELECT o FROM OrderForm o WHERE o.logisticsId = :logisticsId"),
    @NamedQuery(name = "OrderForm.findByIsFinished", query = "SELECT o FROM OrderForm o WHERE o.isFinished = :isFinished")})
public class OrderForm implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "customer_id")
    private int customerId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "price")
    private float price;
    @Basic(optional = false)
    @NotNull
    @Column(name = "sold_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date soldDate;
    @Column(name = "logistics_id")
    private Integer logisticsId;
    @Column(name = "is_finished")
    private Boolean isFinished;
    @JoinColumn(name = "seller_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private User sellerId;
    @JoinColumn(name = "book_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Book bookId;

    public OrderForm() {
    }

    public OrderForm(Integer id) {
        this.id = id;
    }

    public OrderForm(Integer id, int customerId, float price, Date soldDate) {
        this.id = id;
        this.customerId = customerId;
        this.price = price;
        this.soldDate = soldDate;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public Date getSoldDate() {
        return soldDate;
    }

    public void setSoldDate(Date soldDate) {
        this.soldDate = soldDate;
    }

    public Integer getLogisticsId() {
        return logisticsId;
    }

    public void setLogisticsId(Integer logisticsId) {
        this.logisticsId = logisticsId;
    }

    public Boolean getIsFinished() {
        return isFinished;
    }

    public void setIsFinished(Boolean isFinished) {
        this.isFinished = isFinished;
    }

    public User getSellerId() {
        return sellerId;
    }

    public void setSellerId(User sellerId) {
        this.sellerId = sellerId;
    }

    public Book getBookId() {
        return bookId;
    }

    public void setBookId(Book bookId) {
        this.bookId = bookId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof OrderForm)) {
            return false;
        }
        OrderForm other = (OrderForm) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.potatoni.entity.OrderForm[ id=" + id + " ]";
    }
    
}
