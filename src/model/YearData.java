package model;

public class YearData {
    private final int month;
    private final Double amount;
    private final Boolean expense;

    public YearData(int month, Double amount, Boolean expense) {
        this.month = month;
        this.amount = amount;
        this.expense = expense;
    }

    public int getMonth() {
        return month;
    }

    public Double getAmount() {
        return amount;
    }

    public Boolean getExpense() {
        return expense;
    }

    @Override
    public String toString() {
        return "YearData{" +
                "month=" + month +
                ", amount=" + amount +
                ", expense=" + expense +
                '}';
    }
}
