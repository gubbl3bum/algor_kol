public class epidemia_DZ {
    public static void main(String[] args) {
        int populacja = 100000;
        int liczbaChorych = 10;
        int dzien = 0;

        while (liczbaChorych > 0) {
            dzien++;
            System.out.println("Dzień " + dzien + ": Liczba chorych: " + liczbaChorych);

            int nowiChorzy = liczbaChorych * 2; // Każda zarażona osoba zaraża dwie kolejne osoby
            liczbaChorych += nowiChorzy;

            // Zakładamy, że choroba trwa 7 dni
            if (dzien % 7 == 0) {
                // Każda osoba, która jest chora od 7 dni, wyzdrowiała
                liczbaChorych -= (liczbaChorych / 7);
            }

            // Zakładamy dodatkowe zakażenia po 2 tygodniach od wyzdrowienia
            if (dzien % 14 == 0) {
                // Każda osoba, która wyzdrowiała 2 tygodnie temu, może ponownie zachorować
                liczbaChorych += (liczbaChorych / 7);
            }

            // Ograniczenie, aby uniknąć nieskończonej pętli
            if (dzien > 100) {
                System.out.println("Przerwano symulację po 100 dniach.");
                break;
            }
        }

        System.out.println("Epidemia zakończona po " + dzien + " dniach.");
    }
}
