package com.shteydle.top.homeWorkFiles2;

public class ContactServiceException extends Exception {

    public ContactServiceException() {
    }

    public ContactServiceException(String message) {
        super(message);
    }

    public ContactServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    public ContactServiceException(Throwable cause) {
        super(cause);
    }
}
