package com.teradata.exception;


public class AlbumNotFoundException extends RuntimeException {

    public AlbumNotFoundException(Long albumId) {
        super("could not find album '" + albumId.toString() + "'.");
    }
}
