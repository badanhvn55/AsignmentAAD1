/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package validate;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author ueda
 */
public class Validator {

    public static boolean isEmailValid(String email) {
        boolean isValid = false;
        String strEmail = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
        CharSequence inputStr = email;
        Pattern email_pattern = Pattern.compile(strEmail, Pattern.CASE_INSENSITIVE);
        Matcher mEmail = email_pattern.matcher(inputStr);
        if (mEmail.matches()) {
            isValid = true;
        }
        return isValid;
    }

    public static boolean isPhoneValid(String phone) {
        boolean isValid = false;
        String strPhone = "(\\+84|0)\\d{9,10}";
        CharSequence inputStr = phone;
        Pattern phone_pattern = Pattern.compile(strPhone, Pattern.CASE_INSENSITIVE);
        Matcher mPhone = phone_pattern.matcher(inputStr);
        if (mPhone.matches()) {
            isValid = true;
        }
        return isValid;
    }
    
    public static boolean isBirthdayValid(String birthday) {
        boolean isValid = false;
        String strBirthday = "^\\d{4}-\\d{2}-\\d{2}$";
        CharSequence inputStr = birthday;
        Pattern birthday_pattern = Pattern.compile(strBirthday, Pattern.CASE_INSENSITIVE);
        Matcher mBirthday = birthday_pattern.matcher(inputStr);
        if (mBirthday.matches()) {
            isValid = true;
        }
        return isValid;
    }

    
}
