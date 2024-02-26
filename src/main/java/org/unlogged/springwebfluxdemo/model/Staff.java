package org.unlogged.springwebfluxdemo.model;

import com.github.davidmoten.rx.jdbc.annotations.Column;

public interface Staff {
    @Column("id")
    Integer id();

    @Column("name")
    String name();

}
