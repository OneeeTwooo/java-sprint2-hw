import model.MonthlyReport;
import model.Transaction;
import model.YearData;
import model.YearlyReport;

import java.util.*;

public class ReportEngine {
    private final HashMap<Integer, MonthlyReport> monthlyReportHashMap;
    private final HashMap<Integer, YearlyReport> yearlyReportHashMap;
    private static final String READ_YEAR_DATA = "Пожалуйста выполните считывание итоговых данных по годам.";
    private static final String READ_MONTH_DATA = "Пожалуйста выполните считывание итоговых данных по месяцам.";

    private final FileReader fileReader;

    public ReportEngine() {
        this.monthlyReportHashMap = new HashMap<>();
        this.yearlyReportHashMap = new HashMap<>();
        this.fileReader = new FileReader();
    }

    public void readMonthsData() {
        for (int i = 1; i <= 3; i++) {
            String monthNumber = String.format("%02d", i);
            ArrayList<String> strings = fileReader.readFileContents("m.2021" + monthNumber + ".csv");
            LinkedList<Transaction> transactions = new LinkedList<>();
            for (int j = 1; j < strings.size(); j++) {
                transactions.add(new Transaction(strings.get(j).split(",")[0],
                        Boolean.parseBoolean(strings.get(j).split(",")[1]),
                        Integer.parseInt(strings.get(j).split(",")[2]),
                        Double.parseDouble(strings.get(j).split(",")[3])));
            }
            MonthlyReport parsedMonth = new MonthlyReport(Integer.parseInt(monthNumber), transactions);
            monthlyReportHashMap.put(Integer.parseInt(monthNumber), parsedMonth);
        }
    }

    public void readYearsData() {
        ArrayList<String> yearStrings = fileReader.readFileContents("y.2021.csv");
        LinkedList<YearData> yearData = new LinkedList<>();
        for (int i = 1; i < yearStrings.size(); i++) {
            yearData.add(new YearData(Integer.parseInt(yearStrings.get(i).split(",")[0]),
                    Double.parseDouble(yearStrings.get(i).split(",")[1]),
                    Boolean.parseBoolean(yearStrings.get(i).split(",")[2])));
        }
        YearlyReport parsedYear = new YearlyReport(2021, yearData);
        yearlyReportHashMap.put(2021, parsedYear);
    }

    public void printMonthData() {
        if (this.monthlyReportHashMap.isEmpty()) {
            System.out.println(READ_MONTH_DATA);
        } else {
            for (MonthlyReport mr : monthlyReportHashMap.values()) {
                Transaction mostProfitable = mr.getTheMostProfitable();
                System.out.println("Месяц :" + mr.getMonth());
                System.out.println("Cамый прибыльный товар: " + mostProfitable.getItemName());
                System.out.println("Общая сумма: " + mostProfitable.getQuantity() * mostProfitable.getUnitPrice());
                Transaction mostExpensive = mr.getTheMostExpensive();
                System.out.println("Самая большая трата: " + mostExpensive.getItemName());
                System.out.println("Общая сумма: " + mostExpensive.getQuantity() * mostExpensive.getUnitPrice());
            }
        }
    }

    public void printYearsData() {
        if (this.yearlyReportHashMap.isEmpty()) {
            System.out.println(READ_YEAR_DATA);
        } else {
            Set monthCount = new HashSet();
            Map<Integer, Double> income = new HashMap<>();
            Map<Integer, Double> consumption = new HashMap<>();
            for (YearlyReport yearlyReport : yearlyReportHashMap.values()) {
                System.out.println("Рассматриваемый год: " + yearlyReport.getYear());
                for (YearData yearData : yearlyReport.getYearData()) {
                    monthCount.add(yearData.getMonth());
                    if (!yearData.getExpense()) {
                        income.put(yearData.getMonth(), yearData.getAmount());
                    } else {
                        if (yearData.getExpense()) {
                            consumption.put(yearData.getMonth(), yearData.getAmount());
                        }
                    }
                }
                for (int i = 1; i <= income.size(); i++) {
                    System.out.println("Месяц: " + i);
                    System.out.println("Прибыль: " + (income.get(i) - consumption.get(i)));
                }
                System.out.println("Cредний расход за все имеющиеся операции в году: " + (consumption.values().stream().mapToDouble(Double::doubleValue).sum()) / monthCount.size());
                System.out.println("Cредний доход за все имеющиеся операции в году: " + (income.values().stream().mapToDouble(Double::doubleValue).sum()) / monthCount.size());
            }
        }
    }

    public void checkConsistency() {
        if (this.yearlyReportHashMap.isEmpty()) {
            System.out.println(READ_YEAR_DATA);
            return;
        }
        if (this.monthlyReportHashMap.isEmpty()) {
            System.out.println(READ_MONTH_DATA);
            return;
        }
        HashMap<Integer, Double> consumptionMap = new HashMap<>();
        HashMap<Integer, Double> incomeMap = new HashMap<>();
        for (MonthlyReport mr : monthlyReportHashMap.values()) {
            double income = 0;
            double consumption = 0;
            for (Transaction tr : mr.getTransactions()) {
                if (tr.isExpense()) {
                    income += tr.getUnitPrice() * tr.getQuantity();
                }
                if (!tr.isExpense()) {
                    consumption += tr.getUnitPrice() * tr.getQuantity();
                }
                consumptionMap.put(mr.getMonth(), income);
                incomeMap.put(mr.getMonth(), consumption);
            }
        }

        for (YearlyReport yearlyReport : yearlyReportHashMap.values()) {
            for (YearData yearData : yearlyReport.getYearData()) {
                if (!yearData.getExpense()) {
                    if (!Objects.equals(incomeMap.get(yearData.getMonth()), yearData.getAmount())) {
                        System.out.println("Доходы не совпадают в месяце №: " + yearData.getMonth());
                    }
                } else {
                    if (!Objects.equals(consumptionMap.get(yearData.getMonth()), yearData.getAmount())) {
                        System.out.println("Расходы не совпадают в месяце №: " + yearData.getMonth());
                    }
                }
            }
        }
        System.out.println("Сверка выполнена успешно.");
    }

}
