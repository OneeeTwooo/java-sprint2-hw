package model;

public class Transaction {
    String itemName;
    boolean isExpense;
    int quantity;
    double unitPrice;

    public Transaction(String itemName, boolean isExpense, int quantity, double unitPrice) {
        this.itemName = itemName;
        this.isExpense = isExpense;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
    }

    public boolean isExpense() {
        return isExpense;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public String getItemName() {
        return itemName;
    }

    @Override
    public String toString() {
        return "Model.Utils.Transaction{" +
                "itemName='" + itemName + '\'' +
                ", isExpense=" + isExpense +
                ", quantity=" + quantity +
                ", unitPrice=" + unitPrice +
                '}';
    }
}
