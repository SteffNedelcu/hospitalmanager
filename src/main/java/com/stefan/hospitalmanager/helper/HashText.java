package com.stefan.hospitalmanager.helper;


import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class HashText {

    private String string;

    public HashText(String string) {
        this.string=string;
    }

    public String sha256() throws NoSuchAlgorithmException {
        final MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] result = digest.digest(this.string.getBytes());
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < result.length; i++) {
            sb.append(Integer.toString((result[i] & 0xff) + 0x100, 16).substring(1));
        }

        return sb.toString();
    }

    public String sha1() throws NoSuchAlgorithmException {
        final MessageDigest digest = MessageDigest.getInstance("SHA1");
        byte[] result = digest.digest(this.string.getBytes());
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < result.length; i++) {
            sb.append(Integer.toString((result[i] & 0xff) + 0x100, 16).substring(1));
        }

        return sb.toString();
    }
}
