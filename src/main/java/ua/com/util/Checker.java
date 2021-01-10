package ua.com.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.Date;

public class Checker {
    public static boolean checkLogin(String login) {
        Pattern pattern = Pattern.compile("[a-zA-Z0-9_-]+");
        Matcher matcher = pattern.matcher(login);
        return matcher.matches();
    }

    public static boolean checkName(String name) {
        Pattern pattern = Pattern.compile("^[A-Z][a-z]+");
        Matcher matcher = pattern.matcher(name);
        return matcher.matches();
    }

    public static boolean checkDepName(String name) {
        Pattern pattern = Pattern.compile("^[A-Z][a-z]+.*");
        Matcher matcher = pattern.matcher(name);
        return matcher.matches();
    }

    public static boolean checkDate(Date d) throws ParseException {
        DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
        String date = dateFormat.format(d);
        Pattern pattern = Pattern.compile("^[0-9]{2}\\.[0-9]{2}\\.[0-9]{4}$");
        Matcher matcher = pattern.matcher(date);
        if (matcher.matches()) {
            return checkAge(date, dateFormat);
        } else {
            return false;
        }
    }

    public static boolean checkEmail(String email) {
        Pattern pattern = Pattern.compile("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$");
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    private static boolean checkAge(String date, DateFormat dateFormat) throws ParseException {
        Date rDate = dateFormat.parse(date);
        if (new Date().getYear() - rDate.getYear() >= 18) {
            return true;
        } else {
            return false;
        }
    }
}
