package com.gfike.BillTracker.models;

import com.sun.istack.NotNull;
import org.hibernate.envers.Audited;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="Expenditure")
@Audited
public class Expenditure extends AbstractEntity {

    @Column(name="Year")
    @NotNull
    private int yr;

    @Column(name="Month")
    @NotNull
    private int month;

    @Column(name="Bills")
    @NotNull
    private String bills;

    public Expenditure() {
    }

    public Expenditure(int yr, int month, String bills) {
        this.yr = yr;
        this.month = month;
        this.bills = bills;
    }

    public int getYr() {
        return yr;
    }

    public void setYr(int yr) {
        this.yr = yr;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public String getBills() {
        return bills;
    }

    public void setBills(String bills) {
        this.bills = bills;
    }
}
