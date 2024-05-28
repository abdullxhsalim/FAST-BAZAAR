package com.example.exceptions;

public class Exceptions {
    
    public static class InvalidEmailException extends Exception {
        String email;
        public InvalidEmailException(String email) {
            this.email = email;
        }
        @Override
        public String toString() {
            return "InvalidEmailException: The email '" + email + "' is not a valid email address.";
        }
    }
    
    public static void emailValidator(String email) throws InvalidEmailException {
        String emailRegex = "^[A-Za-z0-9+_.-]+@(.+)$";
        if(!email.matches(emailRegex)) throw new InvalidEmailException(email);
    }

    public static class InvalidNameException extends Exception {
        String name;
        InvalidNameException(String name) {
            this.name = name;
        }
        @Override
        public String toString() {
            return "InvalidNameException: The name '" + name + "' is not a valid name.";
        }
    }

    public static void nameValidator(String name) throws InvalidNameException {
        String nameRegex = "^[A-Za-z\\s'-]+$";
        if(!name.matches(nameRegex)) throw new InvalidNameException(name);
    }

    public static class InvalidPhoneException extends Exception {
        String phone;
        InvalidPhoneException(String phone) {
            this.phone = phone;
        }
        @Override
        public String toString() {
            return "InvalidPhoneException: The phone number '" + phone + "' is not a valid phone number.";
        }
    }

    public static void phoneValidator(String phone) throws InvalidPhoneException {
        String phoneRegex = "^[0-9]{10,15}$";
        if(!phone.matches(phoneRegex)) throw new InvalidPhoneException(phone);
    }

    public static class InvalidZipException extends Exception {
        private String zip;

        public InvalidZipException(String zip) {
            this.zip = zip;
        }

        @Override
        public String toString() {
            return "Invalid zip code: " + zip;
        }
    }

    public static void zipValidator(String zip) throws InvalidZipException {
        if (!zip.matches("\\d+")) {
            throw new InvalidZipException(zip);
        }
    }
}