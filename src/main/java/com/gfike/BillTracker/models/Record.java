package com.gfike.BillTracker.models;

import com.gfike.BillTracker.data.BillDao;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name="Record")
public class Record {
    @Autowired
    BillDao billDao;

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(name = "ID", updatable = false, nullable = false)
    private UUID id;

    @Column(name="RecordMonth")
    private String month;

    @Column(name="RecordYear")
    private int year;

    @ManyToMany
    @Column
    private Set<Bill> bills = new HashSet<>();

    public Record(String month, int year) {
        this.month = month;
        this.year = year;
        this.setBills();
    }

    public Set<Bill> getBills() {
        return bills;
    }

    public void setBills() {
        this.bills = (Set)billDao.findAll();
    }

    public void addBill (Bill b) {
        this.bills.add(b);
    }

    public void removeBill (Bill b) {
        this.bills.remove(b);
    }

    public Record() {
    }

    public UUID getId() {
        return id;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }
}
