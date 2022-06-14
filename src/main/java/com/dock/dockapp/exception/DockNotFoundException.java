package com.dock.dockapp.exception;

public class DockNotFoundException extends RuntimeException {

    public DockNotFoundException(Long id) {
        super("Could not find boat with id :" + id);
    }
}
