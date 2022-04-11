package com.example.finman.model.doa.sqltables.transaction;

public record Transaction(long transactionNumber, long accountNumber, String category, double amount,
                          String createdAt) {
}
