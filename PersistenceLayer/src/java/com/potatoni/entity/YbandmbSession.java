/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.potatoni.entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author LinFan
 */
@Entity
@Table(name = "ybandmb_session")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "YbandmbSession.findAll", query = "SELECT y FROM YbandmbSession y"),
    @NamedQuery(name = "YbandmbSession.findBySessionId", query = "SELECT y FROM YbandmbSession y WHERE y.sessionId = :sessionId"),
    @NamedQuery(name = "YbandmbSession.findByUserid", query = "SELECT y FROM YbandmbSession y WHERE y.userid = :userid")})
public class YbandmbSession implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "sessionId")
    private String sessionId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "userid")
    private int userid;

    public YbandmbSession() {
    }

    public YbandmbSession(String sessionId) {
        this.sessionId = sessionId;
    }

    public YbandmbSession(String sessionId, int userid) {
        this.sessionId = sessionId;
        this.userid = userid;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (sessionId != null ? sessionId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof YbandmbSession)) {
            return false;
        }
        YbandmbSession other = (YbandmbSession) object;
        if ((this.sessionId == null && other.sessionId != null) || (this.sessionId != null && !this.sessionId.equals(other.sessionId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.potatoni.entity.YbandmbSession[ sessionId=" + sessionId + " ]";
    }
    
}
