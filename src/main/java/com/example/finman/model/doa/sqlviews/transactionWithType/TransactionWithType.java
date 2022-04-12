package com.example.finman.model.doa.sqlviews.transactionWithType;

public class TransactionWithType{
    private final long transactionNumber;
    private final long accountNumber;
    private final String category;
    private final double amount;
    private final String createdAt;
    private final String transactionType;

    public TransactionWithType(long transactionNumber, long accountNumber, String category, double amount,
                               String createdAt, String transactionType) {
        this.transactionNumber = transactionNumber;
        this.accountNumber = accountNumber;
        this.category = category;
        this.amount = amount;
        this.createdAt = createdAt;
        this.transactionType = transactionType;
    }

    public long getTransactionNumber() {
        return transactionNumber;
    }

    public long getAccountNumber() {
        return accountNumber;
    }

    public String getCategory() {
        return category;
    }

    public double getAmount() {
        return amount;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public String getTransactionType() {
        return transactionType;
    }
}
