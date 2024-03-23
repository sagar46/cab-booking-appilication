package com.cabbooking.exception;

public class CabBookingException extends RuntimeException{
    public CabBookingException(String message){
        super(message);
    }
    public CabBookingException(String message, Exception exception){
        super(message, exception);
    }
}
