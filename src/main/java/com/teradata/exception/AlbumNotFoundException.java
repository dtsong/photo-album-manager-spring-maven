package com.teradata.exception;


public class AlbumNotFoundException extends RuntimeException {

    public AlbumNotFoundException(String albumId) {
        super("could not find album '" + albumId + "'.");
    }
}
