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
public class Session implements Serializable {
    private static final long serialVersionUID = 1L;
    private String sessionId;
    private int userid;

    public Session() {
    }

    public Session(String sessionId) {
        this.sessionId = sessionId;
    }

    public Session(String sessionId, int userid) {
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
}
