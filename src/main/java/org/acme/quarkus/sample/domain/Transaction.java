package org.acme.quarkus.sample.domain;

import io.quarkus.runtime.annotations.RegisterForReflection;

import java.math.BigDecimal;
import java.util.Calendar;

@RegisterForReflection
public class Transaction {

    private String uuid;
    private Calendar date;
    private String brand;
    private String acquirer;
    private String operation;
    private BigDecimal amount;

    public String getUuid() {
        return uuid;
    }

    public Transaction setUuid(String uuid) {
        this.uuid = uuid;
        return this;
    }

    public Calendar getDate() {
        return date;
    }

    public Transaction setDate(Calendar date) {
        this.date = date;
        return this;
    }

    public String getBrand() {
        return brand;
    }

    public Transaction setBrand(String brand) {
        this.brand = brand;
        return this;
    }

    public String getAcquirer() {
        return acquirer;
    }

    public Transaction setAcquirer(String acquirer) {
        this.acquirer = acquirer;
        return this;
    }

    public String getOperation() {
        return operation;
    }

    public Transaction setOperation(String operation) {
        this.operation = operation;
        return this;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public Transaction setAmount(BigDecimal amount) {
        this.amount = amount;
        return this;
    }

}
