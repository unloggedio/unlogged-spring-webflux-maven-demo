package org.unlogged.springwebfluxdemo.model.circle;

public class CircleParent {
    private CircleChild child;
    private int id;


    public CircleChild getChild() {
        return child;
    }

    public void setChild(CircleChild childm) {
        this.child = child;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public CircleParent(int id) {
        this.child = new CircleChild(this,"e");
        this.id = id;
    }
}
