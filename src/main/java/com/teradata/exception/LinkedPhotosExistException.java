package com.teradata.exception;

/**
 * Created by danielsong on 3/20/17.
 */
public class LinkedPhotosExistException extends RuntimeException  {
    public LinkedPhotosExistException(Long albumId) {
        super("Cannot delete " + albumId.toString() + "due to associated photos existing");
    }
}
