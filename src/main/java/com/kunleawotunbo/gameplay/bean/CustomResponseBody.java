/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kunleawotunbo.gameplay.bean;

import java.util.List;

/**
 *
 * @author Olakunle Awotunbo
 */
public class CustomResponseBody {
    
    private String code;
    private String msg;
    private List<?> result;

    /**
     * @return the code
     */
    public String getCode() {
        return code;
    }

    /**
     * @param code the code to set
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * @return the msg
     */
    public String getMsg() {
        return msg;
    }

    /**
     * @param msg the msg to set
     */
    public void setMsg(String msg) {
        this.msg = msg;
    }

    /**
     * @return the result
     */
    public List<?> getResult() {
        return result;
    }

    /**
     * @param result the result to set
     */
    public void setResult(List<?> result) {
        this.result = result;
    }
}
