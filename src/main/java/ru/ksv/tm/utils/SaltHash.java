package ru.ksv.tm.utils;

import org.apache.commons.codec.digest.DigestUtils;

public class SaltHash {

    public static String getHash(String strInput) {
        final String salt = "CjKmGtHtW";
        String strOutput = strInput;
        for(int i = 0; i < strInput.length(); i++) {
            strOutput = strOutput + salt;
            strOutput = DigestUtils.md5Hex(strOutput);
        }
        return strOutput;
    }

}
