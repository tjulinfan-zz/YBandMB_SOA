/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.potatoni.helper;

import com.potatoni.entity.Session;
import com.potatoni.exception.InvalidSessionException;
import com.potatoni.restclient.SessionRESTClient;
import javax.ws.rs.ClientErrorException;

/**
 *
 * @author LinFan
 */
public class SessionHelper {
    
    public static int getUserId(String sessionid) throws InvalidSessionException {
        SessionRESTClient client = new SessionRESTClient();
        try {
            Session session = client.find_JSON(Session.class, sessionid);
            return session.getUserid();
        } catch (ClientErrorException e) {
            throw new InvalidSessionException();
        } finally {
            client.close();
        }
    }
    
}
