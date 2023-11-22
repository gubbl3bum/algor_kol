public class kroliczki_DZ {
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

        // Metoda dziel i zwyciężaj do obliczania liczby Fibonacciego
        private static int fibonacci(int n) {
            if (n <= 1) {
                return n;
            }
            return fibonacci(n - 1) + fibonacci(n - 2);
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

