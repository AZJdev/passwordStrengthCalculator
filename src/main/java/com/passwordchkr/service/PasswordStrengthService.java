package com.passwordchkr.service;

public class PasswordStrengthService {

    public static String evaluate(double entropy) {

        if (entropy < 28)
            return "Bardzo słabe";

        if (entropy < 36)
            return "Słabe";

        if (entropy < 60)
            return "Średnie";

        if (entropy < 128)
            return "Silne";

        return "Bardzo silne";
    }
}
