package org.unlogged.springwebfluxdemo.model;

import com.github.davidmoten.rx.jdbc.annotations.Column;

public interface University {
    @Column("id")
    String id();

    @Column("name")
    String name();

    @Column("address")
    String address();
}
