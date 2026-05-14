package com.passwordchkr.service;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

public class DictionaryChecker {

    private static final Set<String> weakPasswords =
            new HashSet<>();

    static {

        try {

            InputStream is =
                    DictionaryChecker.class.getResourceAsStream(
                            "/com/passwordchkr/weak_passwords.txt");

            BufferedReader reader =
                    new BufferedReader(
                            new InputStreamReader(is));

            String line;

            while ((line = reader.readLine()) != null) {

                weakPasswords.add(
                        line.trim().toLowerCase());
            }

        } catch (Exception e) {

            e.printStackTrace();
        }
    }

    public static boolean isWeakPassword(String password) {

        return weakPasswords.contains(
                password.toLowerCase());
    }
}
