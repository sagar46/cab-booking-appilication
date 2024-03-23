package com.cabbooking.exception;

public class UserNotFoundException extends CabBookingException {
    public UserNotFoundException(String message) {
        super(message);
    }
}
