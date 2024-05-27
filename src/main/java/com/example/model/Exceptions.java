package com.example.model;
public class Exceptions {
    
    public static class InvalidEmailException extends Exception {
        String email;
        InvalidEmailException(String email) {
            this.email = email;
        }
        @Override
        public String toString() {
            return "InvalidEmailException: " + email;
        }
    }
    
    static void emailValidator(String email) throws InvalidEmailException {
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
            return "InvalidNameException: " + name;
        }
    }
    static void nameValidator(String name) throws InvalidNameException {
        String nameRegex = "^[A-Za-z]+";
        if(!name.matches(nameRegex)) throw new InvalidNameException(name);
        
    }

    public static class InvalidPhoneException extends Exception {
        String phone;
        InvalidPhoneException(String phone) {
            this.phone = phone;
        }
        @Override
        public String toString() {
            return "InvalidPhoneException: " + phone;
        }
    }

    static void phoneValidator(String phone) throws InvalidPhoneException {
        String phoneRegex = "^[0-9]{10}";
        if(!phone.matches(phoneRegex)) throw new InvalidPhoneException(phone);
    }
}
