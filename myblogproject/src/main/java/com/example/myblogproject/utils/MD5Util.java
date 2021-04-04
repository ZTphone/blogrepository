package com.example.myblogproject.utils;

import org.apache.commons.codec.digest.DigestUtils;


public class MD5Util {
        private static final String salt1 = "6cb755e000cf62e";
        private static final String salt2 = "gj4roi4e5d3ef";

        public static String getFirstMD5(String key){
                String md5 = DigestUtils.md5Hex(salt1+key);
                return md5;
        }

        public static String getSecondMD5(String key){
                String md5 = DigestUtils.md5Hex(salt2+key);
                return md5;
        }

        public static String getFinMD5(String key){
                String md5 = getSecondMD5(getFirstMD5(key));
                return md5;
        }
}
