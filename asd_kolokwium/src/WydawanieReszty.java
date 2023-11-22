import java.util.HashMap;
import java.util.Map;
import java.util.Random;

class WydawanieResztyMonteCarlo {

    public static void main(String[] args) {
        // Wczytaj resztę od użytkownika w złotówkach
        double resztaZlotowki = 123.5;

        // Przelicz resztę na grosze
        int resztaGrosze = (int) (resztaZlotowki * 100);

        // Dostępne nominały monet w groszach
        int[] nominaly = {500, 200, 100, 50, 20, 10, 5, 2, 1};

        // Uruchom algorytm Monte Carlo
        Map<Integer, Integer> najlepszaKombinacja = wydajReszteMonteCarlo(resztaGrosze, nominaly, 10000);

        // Wypisz wynik
        System.out.println("Reszta " + resztaZlotowki + " zł została wydana za pomocą następujących monet (Monte Carlo):");
        for (Map.Entry<Integer, Integer> entry : najlepszaKombinacja.entrySet()) {
            int nominal = entry.getKey();
            int ilosc = entry.getValue();
            String jednostka = (nominal >= 100) ? "zł" : "gr";
            if(nominal >= 100){
                System.out.println(ilosc + " monet po " + nominal / 100.0 + " " + jednostka);
            }else{
                System.out.println(ilosc + " monet po " + nominal + " " + jednostka);
            }
        }
    }

    public static Map<Integer, Integer> wydajReszteMonteCarlo(int reszta, int[] nominaly, int liczbaProbek) {
        Map<Integer, Integer> najlepszaKombinacja = null;
        int najlepszaLiczbaMonet = Integer.MAX_VALUE;

        Random random = new Random();

        for (int i = 0; i < liczbaProbek; i++) {
            Map<Integer, Integer> kombinacja = new HashMap<>();
            int resztaTemp = reszta;

            for (int nominal : nominaly) {
                int ilosc = random.nextInt(resztaTemp / nominal + 1);
                kombinacja.put(nominal, ilosc);
                resztaTemp -= ilosc * nominal;
            }

            if (resztaTemp == 0 && kombinacja.size() < najlepszaLiczbaMonet) {
                najlepszaKombinacja = new HashMap<>(kombinacja);
                najlepszaLiczbaMonet = kombinacja.size();
            }
        }

        return najlepszaKombinacja;
    }
}
