package com.dock.dockapp.exception;

public class BoatNotFoundException extends RuntimeException {

    public BoatNotFoundException(Long id) {
        super("Could not find boat with id :" + id);
    }
}