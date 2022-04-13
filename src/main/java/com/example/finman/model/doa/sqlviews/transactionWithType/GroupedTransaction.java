package com.example.finman.model.doa.sqlviews.transactionWithType;

public class GroupedTransaction {
    private final double groupAmount;
    private final String groupCategory;

    public GroupedTransaction(double groupAmount, String groupCategory) {
        this.groupAmount = groupAmount;
        this.groupCategory = groupCategory;
    }

    public double getGroupAmount() {
        return groupAmount;
    }

    public String getGroupCategory() {
        return groupCategory;
    }
}
