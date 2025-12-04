package com.b2b.listingservice.domain;

import java.math.BigDecimal;
import java.util.Currency;

public class Money {
    private final BigDecimal amount;
    private final Currency currency;

    public Money(BigDecimal amount, Currency currency) {
        if (amount == null || currency == null) {
            throw new IllegalArgumentException("Amount and currency cannot be null");
        }
        this.amount = amount;
        this.currency = currency;
    }

    public Money(double amount, Currency currency) {
        this(BigDecimal.valueOf(amount), currency);
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public Currency getCurrency() {
        return currency;
    }

    @Override
    public String toString() {
        return currency.getSymbol() + amount;
    }
}

