package com.teradata.controller;

import com.teradata.exception.AlbumNotFoundException;
import org.springframework.hateoas.VndErrors;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class PhotoControllerAdvice {

    @ResponseBody
    @ExceptionHandler(AlbumNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    VndErrors albumNotFoundExceptionHandler(AlbumNotFoundException ex) {
        return new VndErrors("error", ex.getMessage());
    }
}
