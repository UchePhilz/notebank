/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.notebank.utils;

import org.apache.commons.codec.digest.DigestUtils;

/**
 *
 * @author Uche Powers
 */
public class PasswordEncoder {

    public static String encoder(String password) {
        String sha256hex = DigestUtils.sha256Hex(password);
        return sha256hex;
    }

    public static boolean checkPassword(String password, String storedPassword) {
        String sha256hex = DigestUtils.sha256Hex(password);

        return sha256hex.equals(storedPassword);
    }

}
