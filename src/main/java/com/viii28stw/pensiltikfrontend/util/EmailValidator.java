package com.viii28stw.pensiltikfrontend.util;

import org.springframework.web.client.RestTemplate;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Plamedi L. Lusembo
 */

public class EmailValidator {

    private static EmailValidator uniqueInstance;

    public EmailValidator() {
    }

    public static synchronized EmailValidator getInstance() {
        if (uniqueInstance == null) {
            uniqueInstance = new EmailValidator();
        }
        return uniqueInstance;
    }

    public boolean validaEmail(String email) {
        Pattern p = Pattern.compile("^[\\w-]+(\\.[\\w-]+)*@([\\w-]+\\.)+[a-zA-Z]{2,7}$");
        Matcher m = p.matcher(email);
        return m.find();
    }

}
