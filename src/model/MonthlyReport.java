package model;

import java.util.LinkedList;

public class MonthlyReport {
    int month;
    LinkedList<Transaction> transactions;

    public MonthlyReport(Integer month, LinkedList<Transaction> transactions) {
        this.month = month;
        this.transactions = transactions;
    }

    public int getMonth() {
        return month;
    }

    public LinkedList<Transaction> getTransactions() {
        return transactions;
    }

    public Transaction getTheMostProfitable() {
        Transaction profit = null;
        double maxValue = 0;
        for (Transaction tr : transactions) {
            if (!tr.isExpense) {
                if ((tr.quantity * tr.unitPrice) >= maxValue) {
                    maxValue = tr.quantity * tr.unitPrice;
                    profit = tr;
                }
            }
        }
        return profit;
    }

    public Transaction getTheMostExpensive() {
        Transaction expens = null;
        double maxValue = 0;
        for (Transaction tr : transactions) {
            if (tr.isExpense) {
                if ((tr.quantity * tr.unitPrice) >= maxValue) {
                    maxValue = tr.quantity * tr.unitPrice;
                    expens = tr;
                }
            }
        }
        return expens;
    }

}