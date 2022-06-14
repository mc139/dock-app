package com.dock.dockapp.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class DockNotFoundAdvice {

    @ResponseBody
    @ExceptionHandler(DockNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String BoatNotFoundHandler(DockNotFoundException exception) {
        return exception.getMessage();
    }
}
