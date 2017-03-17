package com.teradata.exception;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class AlbumNotFoundException extends RuntimeException {

    public AlbumNotFoundException(String albumId) {
        super("could not find album '" + albumId + "'.");
    }
}
