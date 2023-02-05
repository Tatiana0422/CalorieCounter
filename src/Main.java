import java.util.Scanner;
import java.util.HashMap;


public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        StepTracker stepTracker = new StepTracker();
try {
    while (true) {
        printMenu();
        int userInput = scanner.nextInt();
        if (userInput == 1) {
            System.out.println("Введите количество шагов в день, которые Вы совершили");
            int stepsPerDay = scanner.nextInt();
            System.out.println("Введите месяц");
            int month = scanner.nextInt();
            System.out.println("Введите день");
            int day = scanner.nextInt();
            stepTracker.saveSteps(stepsPerDay, month, day);

        } else if (userInput == 2) {
            System.out.println("Введите месяц, за который нужна статистика");
            int userMonth2 = scanner.nextInt();
            stepTracker.getStatics(userMonth2);

        } else if (userInput == 3) {
            System.out.println("Введите вашу новую дневную цель по шагам ");
            int newDailyStepsGoal = scanner.nextInt();
            stepTracker.setSteps(newDailyStepsGoal);
            System.out.println("Ваша новая цель по шагам составляет- " + newDailyStepsGoal);

        } else if (userInput == 4) {
            System.out.println("Вы совершили выход из программы");
            break;
        } else {
            System.out.println("Извините, такой команды пока нет.");
            break;
        }

    }
} catch (Exception e) {
    System.out.println("Вы некорректно ввели данные");
}
        System.out.println("Программа завершена");
}
    private static void printMenu() {
        System.out.println("Выберите действие, которое хотите совершить");
        System.out.println("1 - ввести количество шагов за день");
        System.out.println("2 - напечатать статистику за месяц");
        System.out.println("3- изменить цель по количеству шагов в день");
        System.out.println("4 - выйти из приложения");
    }

    static class StepTracker {
        int dailyStepsGoal = 10000;
        HashMap<Integer, MonthData> monthToData = new HashMap<Integer, MonthData>();
        Converter converter = new Converter();
        public StepTracker() {
            for (int i = 1; i < 13; i++) {
                monthToData.put(i, new MonthData());
            }
        }

        public void setSteps(int newDailyStepsGoal) {

            int newGoal = newDailyStepsGoal;
        }

        public void saveSteps(int stepsPerDay, int month, int day) {
        if (stepsPerDay > 0  && day > 0 && day < 31 && month > 0 && month < 13) {
            monthToData.get(month).steps[day-1] = stepsPerDay;
            System.out.println("Ваши шаги сохранены");
        } else {
            System.out.println("Ввод некорректен");
        }
        }
        public void getStatics(int month) {

        if (monthToData.containsKey(month)) {
        int sum = 0;
        int maxSteps = 0;
        int currentSeries = 0;
        int theBestSeries = 0;

        for (int i = 0; i < monthToData.get(month).steps.length; i++) {
        System.out.println((i + 1) + "день: " + monthToData.get(month).steps[i] + ", ");
        sum += monthToData.get(month).steps[i];
            if (monthToData.get(month).steps[i] > maxSteps) {
                maxSteps = monthToData.get(month).steps[i];
            }
    }
    for (int stepsDay : monthToData.get(month).steps) {
        if (stepsDay > dailyStepsGoal) {
            currentSeries++;
        } else {
            if (currentSeries > theBestSeries) {
                theBestSeries = currentSeries;
            }
               currentSeries = 0;
        }
    }
    if (currentSeries > theBestSeries) {
        theBestSeries = currentSeries;
    }

    System.out.println();
    System.out.println("Общее количество шагов за месяц: " + sum);
    System.out.println("Среднее количество шагов за месяц: " + sum / monthToData.get(month).steps.length);
    System.out.println("Максимальное количество шагов за месяц: " + maxSteps);
    converter.convert(sum);
    System.out.println("Лучшая серия за месяц: " + theBestSeries);
}
        else{
            System.out.println("Такой месяц еще не придумали, введите существующий месяц");
        }

        }

        class MonthData {
            int[] steps;
            MonthData() {

                steps = new int[30];
            }
        }

    }
    public static class Converter {

        public void convert(int sum) {

        }

    }
}