package com.passwordchkr.service;

import java.security.SecureRandom;

public class PasswordGenerator {

    private static final String LOWER =
            "abcdefghijklmnopqrstuvwxyz";

    private static final String UPPER =
            "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

    private static final String DIGITS =
            "0123456789";

    private static final String SPECIAL =
            "!@#$%^&*()_-+=<>?";

    public static String generate(int length) {

        String chars =
                LOWER + UPPER + DIGITS + SPECIAL;

        SecureRandom random = new SecureRandom();

        StringBuilder password = new StringBuilder();

        for (int i = 0; i < length; i++) {

            int index =
                    random.nextInt(chars.length());

            password.append(chars.charAt(index));
        }

        return password.toString();
    }
}
