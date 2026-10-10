package com.vaibhav.linksnap.util;

import java.security.SecureRandom;

public class CodeGenerator {

    private static final String CHAR_SET = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
    private static final int STRING_LENGTH = 6;
    private static final SecureRandom RANDOM = new SecureRandom();

    private CodeGenerator() {}

    public static String generate() {
        StringBuilder sb = new StringBuilder(STRING_LENGTH);
        for(int i = 0; i < STRING_LENGTH; i++){
            int randomIndex = RANDOM.nextInt(CHAR_SET.length());
            sb.append(CHAR_SET.charAt(randomIndex));
        }

        return sb.toString();
    }
}
