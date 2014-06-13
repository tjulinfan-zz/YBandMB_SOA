/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.potatoni.entity;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author LinFan
 */
@Entity
@Table(name = "bookinfo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Bookinfo.findAll", query = "SELECT b FROM Bookinfo b"),
    @NamedQuery(name = "Bookinfo.findByIsbn", query = "SELECT b FROM Bookinfo b WHERE b.isbn = :isbn"),
    @NamedQuery(name = "Bookinfo.findByTitle", query = "SELECT b FROM Bookinfo b WHERE b.title = :title"),
    @NamedQuery(name = "Bookinfo.findByAuthor", query = "SELECT b FROM Bookinfo b WHERE b.author = :author"),
    @NamedQuery(name = "Bookinfo.findByImgurl", query = "SELECT b FROM Bookinfo b WHERE b.imgurl = :imgurl"),
    @NamedQuery(name = "Bookinfo.findByPublisher", query = "SELECT b FROM Bookinfo b WHERE b.publisher = :publisher"),
    @NamedQuery(name = "Bookinfo.findByPubdate", query = "SELECT b FROM Bookinfo b WHERE b.pubdate = :pubdate")})
public class Bookinfo implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "isbn")
    private String isbn;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "title")
    private String title;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "author")
    private String author;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "imgurl")
    private String imgurl;
    @Size(max = 50)
    @Column(name = "publisher")
    private String publisher;
    @Column(name = "pubdate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date pubdate;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "isbn")
    private Collection<Book> bookCollection;

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

    public Date getPubdate() {
        return pubdate;
    }

    public void setPubdate(Date pubdate) {
        this.pubdate = pubdate;
    }

    @XmlTransient
    public Collection<Book> getBookCollection() {
        return bookCollection;
    }

    public void setBookCollection(Collection<Book> bookCollection) {
        this.bookCollection = bookCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (isbn != null ? isbn.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Bookinfo)) {
            return false;
        }
        Bookinfo other = (Bookinfo) object;
        if ((this.isbn == null && other.isbn != null) || (this.isbn != null && !this.isbn.equals(other.isbn))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.potatoni.entity.Bookinfo[ isbn=" + isbn + " ]";
    }
    
}
