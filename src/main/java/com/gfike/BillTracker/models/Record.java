package com.gfike.BillTracker.models;


import org.hibernate.envers.Audited;
import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Audited
@Table(name="Record")
public class Record extends AbstractEntity {

    @Column(name="RecordMonth")
    private int month;

    @Column(name="RecordYear")
    private int year;

    @ManyToMany
    @Column
    private Set<Bill> bills = new HashSet<>();

    public Record(int month, int year, Set<Bill> bills) {
        this.month = month;
        this.year = year;
        this.bills = bills;
    }

    public List<Bill> sortBillsByName () {
        List<Bill> temp = (List<Bill>)this.bills;
        List<Bill> billsSorted = new ArrayList<>();
        billsSorted.add(temp.remove(0));

        return billsSorted;
    }

    public Set<Bill> getBills() {
        return bills;
    }

    public void setBills(Set bills) {
        this.bills = bills;
    }

    public void addBill (Bill b) {
        this.bills.add(b);
    }

    public void removeBill (Bill b) {
        this.bills.remove(b);
    }

    public Record() {
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }
}
