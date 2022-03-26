/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.notebank.utils;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

/**
 *
 * @author Uche Powers
 */
public class ResponseObject {

    private Object obj;
    private HttpStatus httpStatus;

    public ResponseObject(HttpStatus httpStatus, Object obj) {
        this.httpStatus = httpStatus;
        this.obj = obj;
    }

    public Object getObj() {
        return obj;
    }

    public void setObj(Object obj) {
        this.obj = obj;
    }

    public ResponseEntity<?> HttpResponse() {
        return new ResponseEntity<>(this, httpStatus);
    }

}
