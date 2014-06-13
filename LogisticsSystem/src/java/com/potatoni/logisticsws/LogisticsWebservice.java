/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.potatoni.logisticsws;

import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.ejb.Stateless;

/**
 *
 * @author LinFan
 */
@WebService(serviceName = "LogisticsWebservice")
@Stateless()
public class LogisticsWebservice {

    /**
     * This is a sample web service operation
     */
    @WebMethod(operationName = "query")
    public String hello(@WebParam(name = "id") String id) {
        return "TODO logistical information";
    }
}
