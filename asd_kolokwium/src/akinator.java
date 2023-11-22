import java.util.Random;
import java.util.Scanner;

public class akinator {
    public static void main(String[] args) {
        Random rand = new Random();
        int ran_num = rand.nextInt(1001);
        System.out.println("wygenerowany numer: " + ran_num);
        //guessing(0,1000,0); //<- guessuje człowiek
        guessing_comp(0,1000,0, ran_num); // <- guessuje kopmputer
    }
    static void guessing_comp(int a, int b, int attempts, int liczba){
        int middle_value = a + (b-a)/2;
        System.out.println("czy to liczba: " + middle_value);
        if(middle_value < liczba){
            System.out.println("za mała :(\n");
            guessing_comp(middle_value + 1, b, attempts + 1, liczba);
        } else if (middle_value > liczba){
            System.out.println("za duża :<\n");
            guessing_comp(a, middle_value - 1, attempts + 1, liczba);
        } else{
            System.out.println("\nudało się odgadnąć liczbę: " + middle_value + "! :D");
            System.out.println("liczba prób: " + attempts);
        }
    }
    static void guessing(int a, int b, int attempts){
        int middle_value = a + (b-a)/2;
        System.out.println("czy to liczba:  " + middle_value);
        System.out.println("TAK / ZA MAŁA / ZA DUŻA:");
        String input = input_str();
        switch (input.toLowerCase()) {
            case "za mała" -> {
                guessing(middle_value + 1, b, attempts+1);
            }
            case "za duża" -> {
                guessing(a,middle_value - 1, attempts+1);
            }
            case "tak" -> {
                System.out.println("\nudało się odgadnąć liczbę! \nliczba: " + middle_value);
            System.out.println("liczba prób: " + attempts);
            }
            default -> {
                System.out.println("nieprawidłowa odpowiedź.");
                guessing(a,b, attempts+1);
            }
        }
    }
    static String input_str(){
        Scanner scan = new Scanner(System.in);
        String input = scan.nextLine();
        return input;
    }
}