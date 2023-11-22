import java.math.BigInteger;
import java.util.*;
import static java.lang.Math.max;
public class Main {
    public static void main(String[] args) {
        System.out.println("Decyzyjny problem plecakowy - metoda dziel i zwyciezaj\n");
        plecaczek_dz();
        System.out.println("\n--------------------------------------------------------");
        System.out.println("\nDecyzyjny problem plecakowy - metoda programowania dynamicznego\n");
        plecaczek_PD();

        System.out.println("\n--------------------------------------------------------");
        System.out.println("\nOdgadywanie liczby - metoda dziel i zwyciężaj\n");
        odgadywanie_DZ();
        System.out.println("\n--------------------------------------------------------");
        System.out.println("\nOdgadywanie liczby - metoda programowania dynamicznego\n");
        odgadywanie_PD();

        System.out.println("\n--------------------------------------------------------");
        System.out.println("\nproblem przewidywania liczbności populacji królików - metoda dziel i zwyciężaj\n");
        kroliczki_dz();
        System.out.println("\n--------------------------------------------------------");
        System.out.println("\nproblem przewidywania liczbności populacji królików - metoda programowania dynamicznego\n");
        kroliki_PD();

        System.out.println("\n--------------------------------------------------------");
        System.out.println("\nprzewidywanie przebiegu epidemii - metoda dziel i zwyciężaj\n");
        epidemia_DZ();
        System.out.println("\n--------------------------------------------------------");
        System.out.println("\nprzewidywanie przebiegu epidemii - metoda programowania dynamicznego\n");
        epidemia_PD();

        System.out.println("\n--------------------------------------------------------");
        System.out.println("\nProblem rozmnażania bakterii - metoda dziel i zwyciężaj\n");
        bakteryjki_DZ();
        System.out.println("\n--------------------------------------------------------");
        System.out.println("\nProblem rozmnażania bakterii - metoda programowania dynamicznego\n");
        baktryjki_PD();

        System.out.println("\n--------------------------------------------------------");
        System.out.println("\nProblem wyprodukowania lodów wszystkich smaków - metoda algorytmu zachłannego\n");
        lodziki_AZ();
        System.out.println("\n--------------------------------------------------------");
        System.out.println("\nProblem wyprodukowania lodów wszystkich smaków - metoda Monte Carlo");
        lodziki_MC();

        System.out.println("\n--------------------------------------------------------");
        System.out.println("\nProblem najkrótszej drogi - metoda algorytmu zachłannego\n");
        drozka_AZ();
        System.out.println("\n--------------------------------------------------------");
        System.out.println("\nProblem najkrótszej drogi - metoda Monte Carlo\n");
        drozka_MC();

        System.out.println("\n--------------------------------------------------------");
        System.out.println("\nProblem wydawania reszty - metoda algorytmu zachłannego\n");
        monetki_AZ();
        System.out.println("\n--------------------------------------------------------");
        System.out.println("\nProblem wydawania reszty - metoda Monte Carlo\n");
        monetki_MC();
    }

    /*A.1 Decyzyjny problem plecakowy
    Dany jest plecak o objętości v = 10 oraz 6 przedmiotów ponumerowanych od 0 do 5. Każdy
    przedmiot ma określoną wartość Wi
    i objętość Vi. Należy zapakować plecak spośród przedmiotów
    ponumerowanych od 0 do 5 w taki sposób, aby wartość przedmiotów w nim zgromadzonych była
    największa. Wartości i objętości przedmiotów określone są w poniższej tabeli:
        i 0 1 2 3 4 5
        Vi 6 2 3 2 3 1
        Wi 6 4 5 7 10 2
    Odp: W plecaku o maksymalnej wartości znajdą się przedmioty 1,2,3,4 o wartości 26*/
    /* DZIEL I ZWYCIEZAJ / PROGRAMOWANIE DYNAMICZNE */
    static void plecaczek_dz() {
        int v = 10;                         // objętość plecaka
        int[] Vi = {6, 2, 3, 2, 3, 1};      // objętość przedmiotów
        int[] Wi = {6, 4, 5, 7, 10, 2};     // wartość przedmiotów

        int n = Vi.length;                  // liczba przedmiotów
        int wynik = plecak_DZ(v, Vi, Wi, n);
        System.out.println("Maksymalna wartość przedmiotów w plecaku: " + wynik);
    }
    static int plecak_DZ(int v, int[] Vi, int[] Wi, int n) {
        //warunek zakończenia rekurencji
        if (n == 0 || v == 0) {
            return 0;
        }
        //jeśli objętość przedmiotu jest większa niż dostępna przestrzen
        if (Vi[n - 1] > v) {
            return plecak_DZ(v, Vi, Wi, n - 1);
        } else {
            //wybrać maksimum spośród dwoch przypadków:
            //1. Przedmiot jest dodany do plecaka
            //2. Przedmiot jest pominięty
            return Math.max(
                    Wi[n - 1] + plecak_DZ(v - Vi[n - 1], Vi, Wi, n - 1),
                    plecak_DZ(v, Vi, Wi, n - 1));
        }
    }
    static void plecaczek_PD() {
        int v = 10;
        int[] Vi = {6, 2, 3, 2, 3, 1};
        int[] Wi = {6, 4, 5, 7, 10, 2};

        int n = Vi.length;
        int[][] tabela = new int[n + 1][v + 1];

        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= v; j++) {
                if (i == 0 || j == 0) {
                    tabela[i][j] = 0;
                } else if (Vi[i - 1] <= j) {
                    tabela[i][j] = Math.max(Wi[i - 1] + tabela[i - 1][j - Vi[i - 1]], tabela[i - 1][j]);
                } else {
                    tabela[i][j] = tabela[i - 1][j];
                }
            }
        }
        System.out.println("Maksymalna wartość przedmiotów w plecaku: " + tabela[n][v]);

    }
    /*A.6 Problem odgadywania liczby
    Jak odgadnąć liczbę pomyślaną przez rozmówcę z zakresu od 0 do 1000, zadając jak najmniejszą liczbę pytań rozmówcy?
    Przy czym, pytania zadawane rozmówcy muszą być jedynie
    typu: „Czy to jest liczba 546?”, na które rozmówca może odpowiadać na jeden z następujących
    sposobów: „TAK”, „ZA MAŁA” lub „ZA DUŻA”. */
    static void odgadywanie_DZ() {
        Random rand = new Random();
        int random_number = rand.nextInt(1001);
        System.out.println("wygenerowany numer: " + random_number);
        guessing_comp(0, 1000, 0, random_number);
    }
    static void guessing_comp(int a, int b, int attempts, int liczba) {
        int srodek_przedzialu = dziel_i_zwyciezaj(a, b);
        System.out.println("czy to liczba: " + srodek_przedzialu + "?");

        if (srodek_przedzialu < liczba) {
            System.out.println("za mała :(");
            guessing_comp(srodek_przedzialu + 1, b, attempts + 1, liczba);
        } else if (srodek_przedzialu > liczba) {
            System.out.println("za duza :(");
            guessing_comp(a, srodek_przedzialu - 1, attempts + 1, liczba);
        } else {
            System.out.println("\nudalo się odgadnac liczbę: " + srodek_przedzialu + "!!! :D");
            System.out.println("liczba prob: " + attempts);
        }
    }
    static int dziel_i_zwyciezaj(int a, int b) {
        //zwraca środek przedziału
        return a + (b - a) / 2;
    }
    static void odgadywanie_PD() {
        Random rand = new Random();
        int random_num = rand.nextInt(1001);
        System.out.println("wygenerowany numer: " + random_num);
        int[] zakres = {0, 1000};
        zgadywanie_PD(zakres, 0, random_num);
    }
    static void zgadywanie_PD(int[] zakres, int attempts, int liczba) {
        int srodek_prz = zakres[0] + (zakres[1] - zakres[0]) / 2;
        System.out.println("czy to liczba: " + srodek_prz);

        if (srodek_prz < liczba) {
            System.out.println("za mała :(");
            zakres[0] = srodek_prz + 1;
            zgadywanie_PD(zakres, attempts + 1, liczba);
        } else if (srodek_prz > liczba) {
            System.out.println("za duża :((");
            zakres[1] = srodek_prz - 1;
            zgadywanie_PD(zakres, attempts + 1, liczba);
        } else {
            System.out.println("\nudalo się zgadnąć liczbę: " + srodek_prz + "!! :D");
            System.out.println("liczba prob: " + attempts);
        }
    }
    /*A.20 Problem przewidywania liczebności populacji królików
    W roku 1202 Leonardo Fibonacci sformułował następujący, obecnie bardzo popularny w informatyce problem, dotyczący
    rozmnażania się królików. Na początku mamy parę nowonarodzonych
    królików i o każdej parze królików zakładamy, że:
    • nowa para staje się płodna po miesiącu życia,
    • każda płodna para rodzi jedną parę nowych królików w miesiącu,
    • króliki nigdy nie umierają.
    W oparciu o powyższe warunki, Fibonacci sformułował następujące pytanie: ile będzie par królików po upływie roku,
    które można uogólnić pytając o to: ile będzie par królików po upływie n
    miesięcy? Liczbę tę zwykle oznacza się przez Fn, jest ona nazywana liczbą Fibonacciego. PrzEprowadzić symulację
    mająca na celu stwierdzenie: po ilu miesiącach populacja królików osiągnie:
    100, 1000, 10000, 100000 oraz 1 milion par*/
    /* DZIEL I ZWYCIEZAJ / PROGRAMOWANIE DYNAMICZNE */
    static void kroliczki_dz() {
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
    private static int fibonacci(int n) {
        if (n <= 1) {
            return n;
        }
        return fibonacci(n - 1) + fibonacci(n - 2);
    }
    private static int findMonthsToReachPopulation(int targetPopulation) {
        int months = 0;
        int currentPopulation = 0;

        while (currentPopulation < targetPopulation) {
            months++;
            currentPopulation = fibonacci(months);
        }

        return months;
    }

    static void kroliki_PD() {
        int months = 12; // Możesz zmienić liczbę miesięcy na dowolną inną
        int result;

        System.out.println("Wyniki dla " + months + " miesięcy:");
        for (int i = 1; i <= months; i++) {
            result = fibbo_kroliczki(i);
            System.out.println("Po " + i + " miesiącach: " + result + " par królików");
        }
        // Wyniki dla konkretnych przypadków
        int[] targetPopulations = {100, 1000, 10000, 100000, 1000000};
        System.out.println("\nWyniki dla konkretnych populacji:");
        for (int target : targetPopulations) {
            int monthsToReach = findMonthsToReachPopulation_PD(target);
            System.out.println("Aby osiągnąć " + target + " par królików, potrzeba " + monthsToReach + " miesięcy.");
        }
    }

    static int fibbo_kroliczki(int n) {
        int[] fibArray = new int[n + 1];
        fibArray[0] = 0;
        fibArray[1] = 1;
        for (int i = 2; i <= n; i++) {
            fibArray[i] = fibArray[i - 1] + fibArray[i - 2];
        }
        return fibArray[n];
    }

    private static int findMonthsToReachPopulation_PD(int targetPopulation) {
        int months = 0;
        int currentPopulation = 0;

        while (currentPopulation < targetPopulation) {
            months++;
            currentPopulation = fibbo_kroliczki(months);
        }
        return months;
    }

    /*A.23 Problem przewidywania przebiegu epidemii
    W pewnej zamkniętej społeczności liczącej 100000 osób pojawiło się 10 osób chorych na
    katar, co spowodowało „epidemię kataru”. Wiedząc, że spośród 10 osób chorych na katar każda
    zaraża codziennie jeszcze dwie osoby, podać przewidywany przebieg epidemii. W szczególności
    odpowiedzieć na pytania: kiedy będzie najwięcej chorych i kiedy epidemia wygaśnie z powodu
    braku osób podatnych na zachorowanie? Założyć przy tym, że katar trwa 7 dni od dnia zarażenia
    i przez ten okres chorzy mogą zarażać inne osoby. Oprócz tego osoby, które wyzdrowiały nie
    mogą już zachorować. Jak zmieni się przebieg epidemii jeśli dopuścimy, że po 2 tygodniach po
    wyzdrowieniu, znowu można zachorować na katar*/
    static void epidemia_DZ() {
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
    static void epidemia_PD() {
        int populacja = 100000;
        int liczbaChorych = 10;
        int dzien = 0;
        int okresWyzdrowienia = 7;
        int okresPonownegoZachorowania = 14;
        int[] liczbaChorychNaDzien = new int[populacja];
        liczbaChorychNaDzien[0] = liczbaChorych;
        while (liczbaChorychNaDzien[dzien] > 0) {
            dzien++;
            int nowiChorzy = liczbaChorychNaDzien[dzien - 1] * 2;
            // Aktualizacja liczby chorych na kolejny dzień
            liczbaChorychNaDzien[dzien] = liczbaChorychNaDzien[dzien - 1] + nowiChorzy;
            // Wyzdrowienie po okresie okresWyzdrowienia dni
            if (dzien >= okresWyzdrowienia) {
                liczbaChorychNaDzien[dzien] -= liczbaChorychNaDzien[dzien - okresWyzdrowienia];
            }
            // Ponowne zachorowanie po okresie okresPonownegoZachorowania dni
            if (dzien >= okresPonownegoZachorowania) {
                liczbaChorychNaDzien[dzien] += liczbaChorychNaDzien[dzien - okresPonownegoZachorowania];
            }
        }
        System.out.println("Epidemia zakończona po " + dzien + " dniach.");
    }
    /*A.24 Problem rozmnażania bakterii
    Rozważmy proces rozmnażania bakterii. Zakładamy, że bakterie znajdują się w środowisku o
    stałych parametrach, w związku z czym szybkość i sposób ich rozmnażania jest stały. Wiadomo,
    że bakterie rozmnażają się przez podział, w którego wyniku z jednej bakterii powstaje 2 nowe.
    Podział ten następuje co 2 minuty. Przyjąć, że wszystkie bakterie dzielą się w tych samych
    chwilach. Po jakim czasie liczba bakterii zwiększy się 1000 razy, 10000 razy, 100000 razy oraz 1
    mln razy*/
    static void bakteryjki_DZ() {
        int poczatkowaLiczbaBakterii = 1;
        int zwiekszenie = 1000;
        for(int i = 1; zwiekszenie < 1000001; i++){
            int czas = znajdzCzas_DZ(poczatkowaLiczbaBakterii, zwiekszenie);
            System.out.println("Czas potrzebny na zwiększenie liczby bakterii o " + zwiekszenie + " razy: " + czas + " minut.");
            zwiekszenie = zwiekszenie * 10;
        }
    }
    static int znajdzCzas_DZ(int poczatkowaLiczbaBakterii, int zwiekszenie) {
        return znajdzCzasRekurencyjnie_DZ(poczatkowaLiczbaBakterii, zwiekszenie, 0, 2);
    }
    static int znajdzCzasRekurencyjnie_DZ(int aktualnaLiczbaBakterii, int zwiekszenie, int czas, int czasPodzialu) {
        if (aktualnaLiczbaBakterii >= zwiekszenie) {
            return czas;
        } else {
            int nowaLiczbaBakterii = aktualnaLiczbaBakterii * 2;
            int nowyCzas = czas + czasPodzialu;
            return znajdzCzasRekurencyjnie_DZ(nowaLiczbaBakterii, zwiekszenie, nowyCzas, czasPodzialu);
        }
    }
    static void baktryjki_PD(){
        int poczatkowaLiczbaBakterii = 1;
        int zwiekszenie = 1000;

        for(int i = 1; zwiekszenie < 1000001; i++){
            int czas = znajdzCzas_PD(poczatkowaLiczbaBakterii, zwiekszenie);
            System.out.println("Czas potrzebny na zwiększenie liczby bakterii o " + zwiekszenie + " razy: " + czas + " minut.");
            zwiekszenie = zwiekszenie * 10;
        }
    }
    public static int znajdzCzas_PD(int poczatkowaLiczbaBakterii, int zwiekszenie) {
        int[] czasy = new int[zwiekszenie + 1];
        czasy[1] = 0;

        for (int i = 2; i <= zwiekszenie; i++) {
            czasy[i] = czasy[i / 2] + 2;
        }
        return czasy[zwiekszenie];
    }
    /*A.25 Problem łososi i rekinów
    Pewna rodzina łososi rozwija się zgodnie z prawem Malthusa, co można wyrazić w ten sposób,
    że jeśli wielkość populacji w chwili t wynosi p(t), to w wyniku naturalnego rozrodu populacji
    w chwili t + 1 jej wielkość wynosi:
    p(t + 1) = p(t) · e^0.003
    gdzie t jest czasem mierzonym w minutach. Rekiny, które zagnieździły się na wodach zasiedlonych
    przez łososie, zjadają 2 promile populacji łososi na minutę. Poza tym, ze względu na niekorzystne
    warunki 2 promile łososi z całej populacji na minutę odpływa z tych wód. Wiedząc, że w chwili
    początkowej było milion łososi, odpowiedzieć na pytanie jak będzie zmieniać się liczebność populacji łososi tzn.
    jeśli liczebność będzie się zmniejszać, to kiedy osiągnie jakieś istotne progi
    (np. 1/2, 1/3, 1/10, 1/100 początkowej liczebności) oraz czy i kiedy populacja łososi na omawianym obszarze wymrze.
     Za moment wymarcia populacji łososi uważamy sytuację, kiedy liczebność
    łososi spadnie poniżej 100 sztuk*/

        /*SYMBOL NEWTONA*/
        /*--------------------------------------------------------------------------------------------------------------------*/

    /*A.1 Decyzyjny problem plecakowy
    Dany jest plecak o objętości v = 10 oraz 6 przedmiotów ponumerowanych od 0 do 5. Każdy
    przedmiot ma określoną wartość Wi
    i objętość Vi. Należy zapakować plecak spośród przedmiotów
    ponumerowanych od 0 do 5 w taki sposób, aby wartość przedmiotów w nim zgromadzonych była
    największa. Wartości i objętości przedmiotów określone są w poniższej tabeli:
        i 0 1 2 3 4 5
        Vi 6 2 3 2 3 1
        Wi 6 4 5 7 10 2
    Odp: W plecaku o maksymalnej wartości znajdą się przedmioty 1,2,3,4 o wartości 26*/
        /*ALGORYTM ZACHŁANNY / MONTE CARLO */

    /*A.4 Problem doboru załogi statku kosmicznego
    Organizowana jest załogowa ekspedycja kosmiczna na Marsa. W ramach kompletowania
    załogi statku kosmicznego główny organizator ekspedycji ma rozwiązać następujący problem.
    Do jego dyspozycji jest pięciu kosmonautów (k1, k2, k3, k4, k5), którzy przeszli pozytywnie
    kwalifikację wstępną do udziału w ekspedycji. Na pokładzie specjalnego statku kosmicznego
    spośród nich będzie potrzebny przynajmniej jeden specjalista w każdej z dziedzin: A, B, C
    i D. Wiadomym jest, że w dziedzinie A specjalizują się kosmonauci k1 i k4, w dziedzinie B
    specjalizują się kosmonauci k2, k3 i k4, w dziedzinie C specjalizują się kosmonauci k3 i k5
    oraz w dziedzinie D specjalizują się kosmonauci k1, k2 i k5. Ze względu na szczupłość miejsca
    na pokładzie statku kosmicznego, z powyższej piątki trzeba wybrać jak najmniejszą grupkę
    kosmonautów, tak aby znajdował się w niej przynajmniej jeden specjalista w każdej z dziedzin
    A, B, C, D.
            Odp: Na Marsa mogą polecieć np. kosmonauci k4 i k5.*/
        /*ALGORYTM ZACHŁANNY / MONTE CARLO */

    /*A.5 Problem wyprodukowania lodów wszystkich smaków
    Fabryka lodów każdego dnia produkuje na tej samej maszynie lody o sześciu różnych smakach.
    Zmiana produkcji ze smaku i na smak j wymaga przestrojenia maszyny, czyli przygotowania
            (w tym umycia) do nowej produkcji, które trwa określony czas. Podana niżej tablica zawiera
    informację o czasach przestrojenia maszyny.

      i\j  1  2  3  4  5  6
        1  0  7  20 21 12 23
        2 27  0  13 16 46 5
        3 53  15 0  25 27 6
        4 16  2  35 0  47 10
        5 31  29 5  18 0  4
        6 28  24 1  17 5  0
    Znaleźć kolejność produkcji, przy której całkowity czas przestrojenia maszyny jest minimalny. Przyjąć, że na koniec dnia maszyna ma być przygotowana do produkcji w następnym dniu.
    Odp: Minimalny całkowity czas przestrojenia maszyny wynosi 63. Oto przykładowa kolejność
    produkcji lodów, dla której całkowity czas przestrojenia maszyny jest minimalny: 1,2,6,5,3,4,1.*/
    static void lodziki_AZ(){
        int[][] czasyPrzestrojenia = {
                {0, 7, 20, 21, 12, 23},
                {27, 0, 13, 16, 46, 5},
                {53, 15, 0, 25, 27, 6},
                {16, 2, 35, 0, 47, 10},
                {31, 29, 5, 18, 0, 4},
                {28, 24, 1, 17, 5, 0}
        };

        List<Integer> kolejnoscProdukcji = znajdzKolejnoscProdukcji_AZ(czasyPrzestrojenia);

        int calkowityCzasPrzestrojenia = obliczCalkowityCzasPrzestrojenia_AZ(czasyPrzestrojenia, kolejnoscProdukcji);

        System.out.println("Minimalny całkowity czas przestrojenia maszyny wynosi: " + calkowityCzasPrzestrojenia);
        System.out.println("Kolejność produkcji lodów: " + kolejnoscProdukcji);
    }
    public static List<Integer> znajdzKolejnoscProdukcji_AZ(int[][] czasyPrzestrojenia) {
        int iloscSmakow = czasyPrzestrojenia.length;

        List<Integer> dostepneSmaki = new ArrayList<>();
        for (int i = 1; i <= iloscSmakow; i++) {
            dostepneSmaki.add(i);
        }

        List<Integer> kolejnoscProdukcji = new ArrayList<>();
        int obecnySmak = 1;

        while (!dostepneSmaki.isEmpty()) {
            int najkrotszyCzas = Integer.MAX_VALUE;
            int najlepszySmak = -1;

            for (int smak : dostepneSmaki) {
                if (czasyPrzestrojenia[obecnySmak - 1][smak - 1] < najkrotszyCzas) {
                    najkrotszyCzas = czasyPrzestrojenia[obecnySmak - 1][smak - 1];
                    najlepszySmak = smak;
                }
            }

            kolejnoscProdukcji.add(najlepszySmak);
            dostepneSmaki.remove(Integer.valueOf(najlepszySmak));
            obecnySmak = najlepszySmak;
        }

        // Dodaj czas przestrojenia na koniec dnia (powrót do pierwszego smaku)
        kolejnoscProdukcji.add(kolejnoscProdukcji.get(0));

        return kolejnoscProdukcji;
    }
    public static int obliczCalkowityCzasPrzestrojenia_AZ(int[][] czasyPrzestrojenia, List<Integer> kolejnoscProdukcji) {
        int calkowityCzas = 0;

        for (int i = 0; i < kolejnoscProdukcji.size() - 1; i++) {
            int obecnySmak = kolejnoscProdukcji.get(i) - 1;
            int nastepnySmak = kolejnoscProdukcji.get(i + 1) - 1;
            calkowityCzas += czasyPrzestrojenia[obecnySmak][nastepnySmak];
        }

        return calkowityCzas;
    }
    static void lodziki_MC(){
        int[][] czasyPrzestrojenia = {
                {0, 7, 20, 21, 12, 23},
                {27, 0, 13, 16, 46, 5},
                {53, 15, 0, 25, 27, 6},
                {16, 2, 35, 0, 47, 10},
                {31, 29, 5, 18, 0, 4},
                {28, 24, 1, 17, 5, 0}
        };
        int liczbaProbek = 100000;

        List<Integer> najlepszaKolejnosc = znajdzNajlepszaKolejnoscMonteCarlo(czasyPrzestrojenia, liczbaProbek);

        int calkowityCzasPrzestrojenia = obliczCalkowityCzasPrzestrojenia(czasyPrzestrojenia, najlepszaKolejnosc);

        System.out.println("Minimalny całkowity czas przestrojenia maszyny (Monte Carlo): " + calkowityCzasPrzestrojenia);
        System.out.println("Najlepsza kolejność produkcji lodów (Monte Carlo): " + najlepszaKolejnosc);
    }
    public static List<Integer> znajdzNajlepszaKolejnoscMonteCarlo(int[][] czasyPrzestrojenia, int liczbaProbek) {
        List<Integer> najlepszaKolejnosc = null;
        int najlepszyCzas = Integer.MAX_VALUE;

        for (int i = 0; i < liczbaProbek; i++) {
            List<Integer> kolejnoscProbki = losowaKolejnoscProdukcji(czasyPrzestrojenia.length);
            int czasProbki = obliczCalkowityCzasPrzestrojenia(czasyPrzestrojenia, kolejnoscProbki);

            if (czasProbki < najlepszyCzas) {
                najlepszyCzas = czasProbki;
                najlepszaKolejnosc = new ArrayList<>(kolejnoscProbki);
            }
        }
        return najlepszaKolejnosc;
    }
    public static List<Integer> losowaKolejnoscProdukcji(int iloscSmakow) {
        List<Integer> kolejnosc = new ArrayList<>();
        for (int i = 1; i <= iloscSmakow; i++) {
            kolejnosc.add(i);
        }

        Collections.shuffle(kolejnosc, new Random());
        return kolejnosc;
    }
    public static int obliczCalkowityCzasPrzestrojenia(int[][] czasyPrzestrojenia, List<Integer> kolejnoscProdukcji) {
        int calkowityCzas = 0;

        for (int i = 0; i < kolejnoscProdukcji.size() - 1; i++) {
            int obecnySmak = kolejnoscProdukcji.get(i) - 1;
            int nastepnySmak = kolejnoscProdukcji.get(i + 1) - 1;
            calkowityCzas += czasyPrzestrojenia[obecnySmak][nastepnySmak];
        }
        return calkowityCzas;
    }
    /*A.19 Problem najkrótszej drogi
    Wyznaczyć najkrótszą drogę z Warszawy do Sofii, korzystając z sieci połączeń przedstawionej
    w poniższej tabeli, gdzie w przypadku istniejącego połączenia pomiędzy miastami, podane są
    odległości (w kilometrach). Natomiast jeśli pomiędzy dwoma miastami nie ma bezpośredniego
    połączenia - zamiast odległości umieszczono słowo: brak.

    Miasta Warszawa Katowice Zakopane Lwów      Wiedeń  Budapeszt Bukareszt Zagrzeb Sofia
    Warszawa 0         300      402     356     brak    brak      brak      brak    brak
    Katowice 300        0       brak    brak    440     474       brak      brak    brak
    Zakopane 402        brak    0       brak    brak    330       brak      brak    brak
    Lwów     356        brak    brak    0       brak    brak      823       brak    brak
    Wiedeń   brak       440     brak    brak    0       brak      brak      430     brak
    Budapeszt brak      474     330     brak    brak    0         813       365     774
    Bukareszt brak      brak    brak    823     brak    813       0         brak    403
    Zagrzeb  brak       brak    brak    brak    430     365       brak      0       768
    Sofia   brak        brak    brak    brak    brak    774       403       768     0
    Odp: Najkrótsza droga z Warszawy do Sofii ma długość 1506 km i prowadzi przez Zakopane oraz
    Budapeszt.*/
        /*ALGORYTM ZACHŁANNY / MONTE CARLO */
    private static final int INFINITY = Integer.MAX_VALUE;
    static void drozka_AZ(){
            // Macierz odległości
            int[][] distances = {
                    {0, 300, 402, 356, INFINITY, INFINITY, INFINITY, INFINITY, INFINITY},
                    {300, 0, INFINITY, INFINITY, 440, 474, INFINITY, INFINITY, INFINITY},
                    {402, INFINITY, 0, INFINITY, INFINITY, 330, INFINITY, INFINITY, INFINITY},
                    {356, INFINITY, INFINITY, 0, INFINITY, INFINITY, 823, INFINITY, INFINITY},
                    {INFINITY, 440, INFINITY, INFINITY, 0, INFINITY, INFINITY, 430, INFINITY},
                    {INFINITY, 474, 330, INFINITY, INFINITY, 0, 813, 365, 774},
                    {INFINITY, INFINITY, INFINITY, 823, INFINITY, 813, 0, INFINITY, 403},
                    {INFINITY, INFINITY, INFINITY, INFINITY, 430, 365, INFINITY, 0, 768},
                    {INFINITY, INFINITY, INFINITY, INFINITY, INFINITY, 774, 403, 768, 0}
            };

            // Wyznaczanie najkrótszej drogi
            DijkstraAlgorithmWithPath.Result result = dijkstra(distances, 0, 8);

            // Wypisanie wyniku
            System.out.println("Najkrótsza droga z Warszawy do Sofii ma długość " + result.distance + " km i prowadzi przez: ");
            for (Integer cityIndex : result.path) {
                System.out.print(getCityName(cityIndex) + " ");
            }
    }
    private static DijkstraAlgorithmWithPath.Result dijkstra(int[][] graph, int start, int destination) {
        int n = graph.length;
        int[] distances = new int[n];
        List<Integer>[] paths = new ArrayList[n];
        boolean[] visited = new boolean[n];

        Arrays.fill(distances, INFINITY);
        distances[start] = 0;

        for (int i = 0; i < n; i++) {
            paths[i] = new ArrayList<>();
        }

        paths[start].add(start);

        for (int i = 0; i < n - 1; i++) {
            int minIndex = findMinDistance(distances, visited);
            visited[minIndex] = true;

            for (int j = 0; j < n; j++) {
                if (!visited[j] && graph[minIndex][j] != INFINITY && distances[minIndex] != INFINITY &&
                        distances[minIndex] + graph[minIndex][j] < distances[j]) {
                    distances[j] = distances[minIndex] + graph[minIndex][j];
                    paths[j] = new ArrayList<>(paths[minIndex]);
                    paths[j].add(j);
                }
            }
        }
        DijkstraAlgorithmWithPath.Result result = new DijkstraAlgorithmWithPath.Result();
        result.distance = distances[destination];
        result.path = paths[destination];
        return result;
    }
    private static int findMinDistance(int[] distances, boolean[] visited) {
        int min = INFINITY;
        int minIndex = -1;

        for (int i = 0; i < distances.length; i++) {
            if (!visited[i] && distances[i] <= min) {
                min = distances[i];
                minIndex = i;
            }
        }

        return minIndex;
    }
    private static String getCityName(int index) {
        String[] cities = {"Warszawa", "Katowice", "Zakopane", "Lwów", "Wiedeń", "Budapeszt", "Bukareszt", "Zagrzeb", "Sofia"};
        return cities[index];
    }
    static class Result {
        int distance;
        List<Integer> path;
    }
    static void drozka_MC(){
        // Macierz odległości
        int[][] distances = {
                {0, 300, 402, 356, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE},
                {300, 0, Integer.MAX_VALUE, Integer.MAX_VALUE, 440, 474, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE},
                {402, Integer.MAX_VALUE, 0, Integer.MAX_VALUE, Integer.MAX_VALUE, 330, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE},
                {356, Integer.MAX_VALUE, Integer.MAX_VALUE, 0, Integer.MAX_VALUE, Integer.MAX_VALUE, 823, Integer.MAX_VALUE, Integer.MAX_VALUE},
                {Integer.MAX_VALUE, 440, Integer.MAX_VALUE, Integer.MAX_VALUE, 0, Integer.MAX_VALUE, Integer.MAX_VALUE, 430, Integer.MAX_VALUE},
                {Integer.MAX_VALUE, 474, 330, Integer.MAX_VALUE, Integer.MAX_VALUE, 0, 813, 365, 774},
                {Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, 823, Integer.MAX_VALUE, 813, 0, Integer.MAX_VALUE, 403},
                {Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, 430, 365, Integer.MAX_VALUE, 0, 768},
                {Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, 774, 403, 768, 0}
        };
        Result result = monteCarlo(distances, 0, 8);
        // Wypisanie wyniku
        System.out.println("Losowa ścieżka z Warszawy do Sofii ma długość " + result.distance + " km i prowadzi przez: ");
        for (Integer cityIndex : result.path) {
            System.out.print(getCityName(cityIndex) + " ");
        }
    }
    private static Result monteCarlo(int[][] graph, int start, int destination) {
        Random random = new Random();
        List<Integer> path = new ArrayList<>();
        path.add(start);
        int totalDistance = 0;

        while (true) {
            int currentCity = path.get(path.size() - 1);

            if (currentCity == destination) {
                break;
            }

            List<Integer> neighbors = getNeighbors(graph, currentCity);

            // Jeśli nie ma sąsiadów, zakończ algorytm
            if (neighbors.isEmpty()) {
                break;
            }

            int nextCity = neighbors.get(random.nextInt(neighbors.size()));
            path.add(nextCity);
            totalDistance += graph[currentCity][nextCity];
        }

        Result result = new Result();
        result.path = path;
        result.distance = totalDistance;

        return result;
    }
    private static List<Integer> getNeighbors(int[][] graph, int city) {
        List<Integer> neighbors = new ArrayList<>();

        for (int i = 0; i < graph[city].length; i++) {
            if (graph[city][i] != Integer.MAX_VALUE) {
                neighbors.add(i);
            }
        }
        return neighbors;
    }
    /*A.28 Problem wydawania reszty
    Jak wydawać resztę za pomocą możliwie najmniejszej liczby monet o nominałach 1gr, 2gr,
     5gr, 10gr, 20gr, 50gr, 1zł, 2zł i 5zł. Skonstruuj algorytm, do którego na wejście podajemy pewną
    sumę pieniędzy, czyli resztę, jaką trzeba wydawać; natomiast na wyjściu wypisywane są monety
    za pomocą których należy tę sumę wydać*/
        /*ALGORYTM ZACHŁANNY / MONTE CARLO */
    static void monetki_AZ(){
        Scanner scanner = new Scanner(System.in);
        // Wczytaj resztę od użytkownika w złotówkach
        System.out.print("Podaj resztę do wydania (w złotówkach): ");
        double resztaZlotowki = scanner.nextDouble();
        // Przelicz resztę na grosze
        int resztaGrosze = (int) (resztaZlotowki * 100);
        // Dostępne nominały monet w groszach
        int[] nominaly = {500, 200, 100, 50, 20, 10, 5, 2, 1};
        // Mapa przechowująca ilość poszczególnych monet
        Map<Integer, Integer> iloscMonet = wydajReszte_AZ(resztaGrosze, nominaly);
        // Wypisz wynik
        System.out.println("Reszta " + resztaZlotowki + " zł została wydana za pomocą następujących monet:");
        for (Map.Entry<Integer, Integer> entry : iloscMonet.entrySet()) {
            int nominal = entry.getKey();
            int ilosc = entry.getValue();
            String jednostka = (nominal >= 100) ? "zł" : "gr";
            if(nominal >= 100){
                System.out.println(ilosc + " monet po " + nominal / 100.0 + " " + jednostka);
            }else{
                System.out.println(ilosc + " monet po " + nominal + " " + jednostka);
            }
        }
        // Zamknij scanner
        scanner.close();
    }
    public static Map<Integer, Integer> wydajReszte_AZ(int reszta, int[] nominaly) {
        Map<Integer, Integer> iloscMonet = new HashMap<>();
        for (int nominal : nominaly) {
            if (reszta >= nominal) {
                int ilosc = reszta / nominal;
                iloscMonet.put(nominal, ilosc);
                reszta -= ilosc * nominal;
            }
        }
        return iloscMonet;
    }

    static void monetki_MC(){
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
        /*FIGURA*/
    }