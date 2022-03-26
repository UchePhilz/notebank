/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.notebank.utils;

import org.apache.commons.lang3.RandomStringUtils;

/**
 *
 * @author Uche Powers
 */
public class TokenGenerator {

    public static final int DEFAULT_TOKEN_LENGTH = 100;

    public static String generalToken() {
        return RandomStringUtils.randomAlphanumeric(DEFAULT_TOKEN_LENGTH);
    }

    public static String currentTimeMilli() {
        return System.currentTimeMillis() + "";
    }

}
