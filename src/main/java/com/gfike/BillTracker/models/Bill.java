package com.gfike.BillTracker.models;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Bill {
    @Id
    long id;
    public Bill () {}
}
