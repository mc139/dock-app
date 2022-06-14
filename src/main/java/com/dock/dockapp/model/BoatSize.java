package com.dock.dockapp.model;

public enum BoatSize {

    YACHT(200.0),
    STANDARDBOAT(100.0),
    SMALLBOAT(50.0),
    KAYAK(25.0);

    private double size;

    BoatSize(double size) {
        this.size = size;
    }
}
