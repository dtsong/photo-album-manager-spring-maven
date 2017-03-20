package com.teradata.exception;


public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(Long userId) {
        super("could not find album with userId of '" + userId.toString() + "'.");
    }
}
