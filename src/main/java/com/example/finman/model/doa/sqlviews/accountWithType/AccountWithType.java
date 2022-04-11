package com.example.finman.model.doa.sqlviews.accountWithType;

import java.util.Objects;

public final class AccountWithType {
    private final long accountNumber;
    private final double balance;
    private final String institution;
    private final String accountName;
    private final String accountType;

    public AccountWithType(long accountNumber, double balance, String institution, String accountName,
                           String accountType) {
        this.accountNumber = accountNumber;
        this.balance = balance;
        this.institution = institution;
        this.accountName = accountName;
        this.accountType = accountType;
    }

    public long getAccountNumber() {
        return accountNumber;
    }

    public double getBalance() {
        return balance;
    }

    public String getInstitution() {
        return institution;
    }

    public String getAccountName() {
        return accountName;
    }

    public String getAccountType() {
        return accountType;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        var that = (AccountWithType) obj;
        return this.accountNumber == that.accountNumber &&
                Double.doubleToLongBits(this.balance) == Double.doubleToLongBits(that.balance) &&
                Objects.equals(this.institution, that.institution) &&
                Objects.equals(this.accountName, that.accountName) &&
                Objects.equals(this.accountType, that.accountType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(accountNumber, balance, institution, accountName, accountType);
    }

    @Override
    public String toString() {
        return "AccountWithType[" +
                "accountNumber=" + accountNumber + ", " +
                "balance=" + balance + ", " +
                "institution=" + institution + ", " +
                "accountName=" + accountName + ", " +
                "accountType=" + accountType + ']';
    }

}
