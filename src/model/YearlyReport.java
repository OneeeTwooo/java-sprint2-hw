package model;

import java.util.LinkedList;

public class YearlyReport {
    private final Integer year;
    private final LinkedList<YearData> yearData;


    public YearlyReport(Integer year, LinkedList<YearData> yearData) {
        this.year = year;
        this.yearData = yearData;
    }

    public Integer getYear() {
        return year;
    }

    public LinkedList<YearData> getYearData() {
        return yearData;
    }

}
