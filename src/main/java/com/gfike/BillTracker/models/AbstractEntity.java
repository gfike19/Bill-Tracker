package com.gfike.BillTracker.models;

import javax.persistence.*;

@MappedSuperclass
public abstract class AbstractEntity {

    private long id;

    @Id
    @GeneratedValue()
    @Column(name = "ID", updatable = false, nullable = false)
    public Long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
