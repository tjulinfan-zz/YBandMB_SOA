/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.potatoni.helper;

import com.potatoni.entity.User;
import com.potatoni.restclient.UserRESTClient;

/**
 *
 * @author LinFan
 */
public class UserHelper {
    
    public static User getUser(Integer userId) {
        UserRESTClient client = new UserRESTClient();
        return client.find_JSON(User.class, String.valueOf(userId));
    }
}
