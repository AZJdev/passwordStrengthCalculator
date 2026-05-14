package com.passwordchkr.service;

import java.util.ArrayList;
import java.util.List;

public class NistValidator {

    public static List<String> validate(String password) {

        List<String> issues = new ArrayList<>();

        if (password.length() < 8) {

            issues.add(
                    "Hasło krótsze niż 8 znaków.");
        }

        if (password.length() < 12) {

            issues.add(
                    "NIST zaleca minimum 12 znaków.");
        }

        if (DictionaryChecker.isWeakPassword(password)) {

            issues.add(
                    "Hasło znajduje się na liście popularnych haseł.");
        }

        if (containsSequence(password)) {

            issues.add(
                    "Hasło zawiera prostą sekwencję.");
        }

        if (containsRepeatedCharacters(password)) {

            issues.add(
                    "Hasło zawiera wiele powtórzeń znaków.");
        }

        return issues;
    }

    private static boolean containsSequence(String password) {

        String lower = password.toLowerCase();

        return lower.contains("1234") ||
                lower.contains("abcd") ||
                lower.contains("qwerty");
    }

    private static boolean containsRepeatedCharacters(String password) {

        return password.matches("(.)\\1\\1+.*");
    }
}
