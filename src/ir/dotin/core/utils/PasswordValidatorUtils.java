package ir.dotin.core.utils;


public class PasswordValidatorUtils {

    private static final String PATTERN = "((?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$%^&*_]).{8,20})";

    public static boolean validate(String password) {
        String value = password.trim();
        return value.matches(PATTERN);
    }
}