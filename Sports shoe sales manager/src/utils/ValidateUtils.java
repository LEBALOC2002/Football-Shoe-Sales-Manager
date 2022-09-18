package utils;

import java.util.regex.Pattern;

public class ValidateUtils {
    public static final String NAME_REGEX = "^[a-zA-Z\\s]+";
    public static final String PHONE_REGEX = "^0[1-9][0-9]{8}$";
    public static final String PASSWORD_REGEX = "^a-zA-Z[A-Za-z0-9]{7,}$";
    public static final String EMAIL_REGEX = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,3}$";
    public static final String ADDRESS_REGEX = "A-Z[a-zA-z0-9] {3,5} " ;
    public static final String USERNAME_REGEX = "[a-zA-Z0-9_]$";

    public static boolean PasswordValid(String password) {
        return Pattern.compile(PASSWORD_REGEX).matcher(password).matches();
    }

    public static boolean NameValid(String name) {
        return Pattern.compile(NAME_REGEX).matcher(name).matches();
    }

    public static boolean PhoneValid(String phone) {
        return Pattern.compile(PHONE_REGEX).matcher(phone).matches();
    }

    public static boolean EmailValid(String email) {
        return Pattern.compile(EMAIL_REGEX).matcher(email).matches();
    }
    public static boolean AddressValid (String address) {
        return  Pattern.compile(ADDRESS_REGEX).matcher(address).matches();
    }
    public static boolean UserValid  (String username) {
        return Pattern.compile(USERNAME_REGEX).matcher(username).matches();
    }
}
