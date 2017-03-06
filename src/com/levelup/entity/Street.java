package com.levelup.entity;

/**
 * Created by java on 13.01.2017.
 */
public class Street extends Entity {

    private String streetName;

    public Street(String streetName) {
        this.streetName = streetName;
    }

    public Street(Long id, String streetName) {
        super(id);
        this.streetName = streetName;
    }

    public String getStreetName() {
        return streetName;
    }

    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }
}
