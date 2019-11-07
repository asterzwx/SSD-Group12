package hello.controller;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexChecker {
	public static final String KEY_EMPTY = "";
	public static final String MSG_SIGNING_UP = "Signing Up.. Please wait...";
    public static final String MSG_USERNAME_EXIST = "Username already taken!";
    public static final String MSG_LOGGING_IN = "Logging In.. Please wait...";
    public static final String MSG_USERNAME_EMPTY = "Username cannot be empty!";
    public static final String MSG_EMAIL_EMPTY = "Email cannot be empty!";
    public static final String MSG_MOBILE_EMPTY = "Mobile cannot be empty!";
    public static final String MSG_PASSWORD_EMPTY = "Password cannot be empty!";

    public static final String MSG_USERNAME_REQUIREMENT = "Length must be 4 to 16 characters long, and alphanumeric!";
    public static final String MSG_EMAIL_REQUIREMENT = "Please enter a valid email address!";
    public static final String MSG_MOBILE_REQUIREMENT = "Only Singapore mobile numbers allowed!";
    public static final String MSG_PASSWORD_REQUIREMENT = "Length must be 8 to 128 characters long!";
    public static final String MSG_REPEAT_PASSWORD = "Please check that your password is the same as above!";
	/**
     * Validates inputs and shows error if any
     */
    public boolean validateInputs(String username, String email, String mobile, String password,
    		String repeatPassword) {
        if (KEY_EMPTY.equals(username)) {            
            return false;
        } else if (!isUsernameValid(username)) {            
            return false;
        } else if (KEY_EMPTY.equals(email)) {            
            return false;
        } else if (!isEmailValid(email)) {
            return false;
        } else if (KEY_EMPTY.equals(mobile)) {            
            return false;
        } else if (!isMobileValid(mobile)) {            
            return false;
        } else if (KEY_EMPTY.equals(password)) {            
            return false;
        } else if (!isPasswordValid(password)) {            
            return false;
        } else if (!repeatPassword.equals(password)) {            
            return false;
        }

        return true;
    }

    //Check username regex
    public boolean isUsernameValid(String username) {
        String regExpn = "^[a-zA-Z0-9]{4,16}$";
        Pattern pattern = Pattern.compile(regExpn, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(username);

        return matcher.matches();
    }

    //Check email regex
    public boolean isEmailValid(String email) {
        String regExpn =
                "^(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\" +
                        "x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\" +
                        "x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9]" +
                        "(?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}" +
                        "(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\" +
                        "x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])$";

        Pattern pattern = Pattern.compile(regExpn, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(email);

        return matcher.matches();
    }

    //Check mobile regex
    public boolean isMobileValid(String mobile) {
        String regExpn = "^(8|9)\\d{7}$";

        Pattern pattern = Pattern.compile(regExpn, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(mobile);

        return matcher.matches();
    }

    //Check password regex
    public boolean isPasswordValid(String password) {
        String regExpn = "^[^\\t\\n\\r]{8,128}$";

        Pattern pattern = Pattern.compile(regExpn, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(password);

        return matcher.matches();
    }
}
