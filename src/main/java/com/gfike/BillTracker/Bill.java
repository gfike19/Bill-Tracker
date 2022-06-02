package com.gfike.BillTracker;

import com.sun.istack.NotNull;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name="Bill")
public class Bill {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(name = "ID", updatable = false, nullable = false)
    private UUID id;

    @Column(name="BillName")
    @NotNull
    private String name;

    @Column(name="Amount")
    @NotNull
    private int amount;

    @Column(name="DueDate")
    @NotNull
    private int dueDate;

    @Column(name="Autopay")
    private boolean autopay;

    @Column(name="AutopayMethod")
    private String autopayMethod;

    public Bill(String name, int amount, int dueDate, boolean autopay, String autopayMethod) {
        this.name = name;
        this.amount = amount;
        this.dueDate = dueDate;
        this.autopay = autopay;
        this.autopayMethod = autopayMethod;
    }

    public Bill() {
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getDueDate() {
        return dueDate;
    }

    public void setDueDate(int dueDate) {
        this.dueDate = dueDate;
    }

    public boolean isAutopay() {
        return autopay;
    }

    public void setAutopay(boolean autopay) {
        this.autopay = autopay;
    }

    public String getAutopayMethod() {
        return autopayMethod;
    }

    public void setAutopayMethod(String autopayMethod) {
        this.autopayMethod = autopayMethod;
    }
}
