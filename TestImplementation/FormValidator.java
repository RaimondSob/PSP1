package com.raimondsobolevskij.service;

public class FormValidator {

    private int _phoneLength = 12;
    private char _phoneShortPrefix = '8';
    private String _phoneFullPrefix = "+370";
    private String _passwordSpecialChars = "!@#$%^&*()_+=-?{}[]~";
    private String _emailForbiddenChars = "(),:;<>[]\\";

    public boolean validateEmail(String email)
    {
        if(email.contains(" "))
        {
            return false;
        }
        if(!(email.chars().filter(ch -> ch == '@').count() == 1))
        {
            return false;
        }
        if(email.contains(".."))
        {
            return false;
        }
        if(checkEmailSpecialChars(email))
        {
            return false;
        }
        if(checkEmailDomain(email))
        {
            return false;
        }
        return true;
    }

    private boolean checkEmailDomain(String email) {
        String[] parts = email.split("@");
        if(parts.length < 2)
        {
            return true;
        }

        String local = parts[0];
        String fullDomain = parts[1];

        if(local == "" || fullDomain == "" || !(fullDomain.contains(".")))
        {
            return true;
        }

        String[] domainParts = fullDomain.split("\\.");
        String domain = domainParts[0];
        String TLD = domainParts[1];

        if(Character.isDigit(TLD.charAt(0)) || TLD.length() <= 2 || TLD.length() >= 63 || domain == "")
        {
            return true;
        }

        return false;
    }

    private boolean checkEmailSpecialChars(String email) {
        for (int i = 0; i < email.length(); i++)
        {
            if(_emailForbiddenChars.contains(String.valueOf(email.charAt(i))))
            {
                return true;
            }
        }
        return false;
    }

    public boolean validatePhoneNumber(String phone)
    {
        if(phone.isBlank() || phone.isEmpty())
        {
            return false;
        }

        if(phone.charAt(0) == _phoneShortPrefix)
        {
             phone = phone.substring(1,phone.length());
             phone = _phoneFullPrefix.concat(phone);
        }

        if(phone.charAt(0) != '+' || phone.length() != _phoneLength)
        {
            return false;
        }

        for(int i = 1; i < phone.length(); i++)
        {
            if(!Character.isDigit(phone.charAt(i)))
            {
                return false;
            }
        }

        return true;
    }

    public boolean validatePassword(String password)
    {
        if(password.isEmpty() || password.isBlank()|| password.contains(" "))
        {
            return false;
        }

        if(password.length() < 6)
        {
            return false;
        }

        return checkPasswordSpecialChar(password);
    }

    private boolean checkPasswordSpecialChar(String password) {
        var countUpperCase = 0;
        var countSpecialChars = 0;

        for (int i = 0; i < password.length(); i++)
        {
            if(!Character.isLetterOrDigit(password.charAt(i)))
            {
                if(!_passwordSpecialChars.contains(String.valueOf(password.charAt(i))))
                {
                    return false;
                }
                countSpecialChars++;
            }

            if(Character.isUpperCase(password.charAt(i)))
            {
                countUpperCase++;
            }
        }

        if(countUpperCase == 0 || countSpecialChars == 0)
        {
            return false;
        }

        return true;
    }

    public void newValidationRule(int length, char shortPrefix, String prefix)
    {
        _phoneFullPrefix = prefix;
        _phoneLength = length;
        _phoneShortPrefix = shortPrefix;
    }
}
