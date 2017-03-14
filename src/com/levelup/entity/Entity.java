package com.levelup.entity;

/**
 * Created by java on 13.01.2017.
 */
public abstract class Entity {

    private Long id;

    public Entity(){

    }

    public Entity(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
