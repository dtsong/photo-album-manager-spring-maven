package com.teradata.exception;


public class LinkedPhotosExistException extends RuntimeException  {
    public LinkedPhotosExistException(Long albumId) {
        super("Cannot delete " + albumId.toString() + "due to associated photos existing");
    }
}
