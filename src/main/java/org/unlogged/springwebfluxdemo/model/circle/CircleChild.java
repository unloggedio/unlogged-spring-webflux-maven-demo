package org.unlogged.springwebfluxdemo.model.circle;

public class CircleChild {

    CircleParent parent;
    String name;

    public CircleChild(CircleParent parent, String name) {
        this.parent = parent;
        this.name = name;
    }

    public CircleParent getParent() {
        return parent;
    }

    public void setParent(CircleParent parent) {
        this.parent = parent;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
