package org.unlogged.springwebfluxdemo.model;

import java.io.Serializable;

public class BaseEntity implements Serializable {

    private int Id;

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }
}
