/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.potatoni.userws;

import com.potatoni.entity.Session;
import com.potatoni.entity.User;
import com.potatoni.exception.InternalException;
import com.potatoni.exception.PasswordIncorrectException;
import com.potatoni.exception.UserNotExistingException;
import com.potatoni.helper.CreateID;
import com.potatoni.restclient.SessionRESTClient;
import com.potatoni.restclient.UserRESTClient;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.ws.rs.ClientErrorException;

/**
 *
 * @author LinFan
 */
@WebService(serviceName = "UserWebService")
public class UserWebService {
    private User User;

    private static class STATUS {

        public static final int NOT_FOUND = 404;
    };

    /**
     * 判断用户是否登录
     */
    @WebMethod(operationName = "isLoggedIn")
    public Boolean isLoggedIn(@WebParam(name = "sessionid") String sessionid, @WebParam(name = "userid") int userid) throws InternalException {
        SessionRESTClient client = new SessionRESTClient();
        try {
            Session session = client.find_JSON(Session.class, sessionid);
            return session.getUserid() == userid;
        } catch (ClientErrorException e) {
            if (e.getResponse().getStatus() == STATUS.NOT_FOUND) {
                return false;
            }
            throw new InternalException();
        } finally {
            client.close();
        }
    }

    /**
     * 判断用户是否存在
     */
    @WebMethod(operationName = "isUserExisting")
    public Boolean isUserExisting(@WebParam(name = "username") String username) throws InternalException {
        UserRESTClient client = new UserRESTClient();
        try {
            User user = client.findByUsername_JSON(User.class, username);
            return user != null;
        } catch (ClientErrorException e) {
            if (e.getResponse().getStatus() == STATUS.NOT_FOUND) {
                return false;
            }
            throw new InternalException();
        } finally {
            client.close();
        }
    }

    /**
     * 注册新用户
     */
    @WebMethod(operationName = "registerUser")
    public void registerUser(@WebParam(name = "username") String username, @WebParam(name = "password") String password, @WebParam(name = "email") String email, @WebParam(name = "phonenumber") String phonenumber, @WebParam(name = "address") String address) throws InternalException {
        UserRESTClient client = new UserRESTClient();
        User newUser = new User(username, password, email);
        newUser.setPhonenum(phonenumber);
        newUser.setAddress(address);
        try {
            client.create_JSON(newUser);
        } catch (ClientErrorException e) {
            throw new InternalException(String.valueOf(e.getResponse().getStatus()));
        } finally {
            client.close();
        }
    }

    /**
     * 登录
     */
    @WebMethod(operationName = "logIn")
    public Session logIn(@WebParam(name = "username") String username, @WebParam(name = "password") String password) throws InternalException, PasswordIncorrectException, UserNotExistingException {
        UserRESTClient client = new UserRESTClient();
        SessionRESTClient sessionClient = new SessionRESTClient();
        try {
            User user = client.findByUsername_JSON(User.class, username);
            if (user.getPassword().equals(password)) {
                Session session = new Session(CreateID.generate(), user.getId());
                sessionClient.create_JSON(session);
                return session;
            } else {
                throw new PasswordIncorrectException();
            }
        } catch (ClientErrorException ex) {
            if (ex.getResponse().getStatus() == STATUS.NOT_FOUND)
                throw new UserNotExistingException();
            throw new InternalException();
        } finally {
            client.close();
        }
    }
}
