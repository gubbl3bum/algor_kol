public class fibbo_dyn {
    public static void main(String[] args) {
        int months = 12; // Możesz zmienić liczbę miesięcy na dowolną inną
        int result;

        System.out.println("Wyniki dla " + months + " miesięcy:");
        for (int i = 1; i <= months; i++) {
            result = fibonacci(i);
            System.out.println("Po " + i + " miesiącach: " + result + " par królików");
        }

        // Wyniki dla konkretnych przypadków
        int[] targetPopulations = {100, 1000, 10000, 100000, 1000000};
        System.out.println("\nWyniki dla konkretnych populacji:");
        for (int target : targetPopulations) {
            int monthsToReach = findMonthsToReachPopulation(target);
            System.out.println("Aby osiągnąć " + target + " par królików, potrzeba " + monthsToReach + " miesięcy.");
        }
    }

    // Metoda programowania dynamicznego do obliczania liczby Fibonacciego
    private static int fibonacci(int n) {
        int[] fibArray = new int[n + 1];
        fibArray[0] = 0;
        fibArray[1] = 1;

        for (int i = 2; i <= n; i++) {
            fibArray[i] = fibArray[i - 1] + fibArray[i - 2];
        }

        return fibArray[n];
    }

    // Metoda do znalezienia liczby miesięcy potrzebnych do osiągnięcia danej populacji
    private static int findMonthsToReachPopulation(int targetPopulation) {
        int months = 0;
        int currentPopulation = 0;

        while (currentPopulation < targetPopulation) {
            months++;
            currentPopulation = fibonacci(months);
        }

        return months;
    }
}
