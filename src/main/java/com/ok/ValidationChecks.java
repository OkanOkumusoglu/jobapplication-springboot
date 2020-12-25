package com.ok;

public class ValidationChecks {

    public static boolean isValidEmailAddress(String email) {
        final String EMAIL_REGEX =
                "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
        return email.matches(EMAIL_REGEX);
    }

    public static boolean isValidURL(String URL) {
        final String URL_REGEX = "\\b(https?|ftp|file)://[-a-zA-Z0-9+&@#/%?=~_|!:,.;]*[-a-zA-Z0-9+&@#/%=~_|]";

        return URL.matches(URL_REGEX);

    }
}
