import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        // Эта работа ужасная, есть очень много костылей, заранее извеняюсь за вытекшие глаза.
        // К сожалению не было времени написать нормально(
        // Прошу понять и простить
        Scanner scanner = new Scanner(System.in);
        ReportEngine reportData = new ReportEngine();


        while (true) {
            printMenu();
            String userCommand = scanner.next().toLowerCase();
            switch (userCommand) {
                case "1": {
                    reportData.readMonthsData();
                    break;
                }
                case "2": {
                    reportData.readYearsData();
                    break;
                }
                case "3": {
                    reportData.checkConsistency();
                    break;
                }
                case "4": {
                    reportData.printMonthData();
                    break;
                }
                case "5": {
                    reportData.printYearsData();
                    break;
                }
                case "6": {
                    System.out.println("Пока!");
                    scanner.close();
                    return;
                }
                default: {
                    System.out.println("Такой команды нет");
                }
            }
        }
    }

    static void printMenu() {
        System.out.println("Пожалуйста выберите один из пунктов меню:");
        System.out.println("1 - Считать все месячные отчёты!");
        System.out.println("2 - Считать годовой отчёт!");
        System.out.println("3 - Сверить отчёты!");
        System.out.println("4 - Вывести информацию обо всех месячных отчётах!");
        System.out.println("5 - Вывести информацию о годовом отчёте!");
        System.out.println("6 - Выход из приложения");
    }

}

