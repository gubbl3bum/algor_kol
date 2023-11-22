import java.util.Scanner;
public class asd {
    public static void main(String[] args) {
        //trojki();
        //podzbior_4();
        //podzbior_n();
        podzbior_plecak();
    }
    public static void trojki(){
        int n = 20;
        int a = 0, b = 0, c = 0;
        for(int x = 1; x <= n; x++){
            a++;
            for(int y = 1; y <=n; y++){
                b++;
                for(int z = 0; z <=n; z++){
                    c++;
                    if(x * x + y * y == z * z){
                        System.out.println("zlozonosc: " + a + " + " + b + " + " + c + " = " + (a+b+c));
                        System.out.println("wartosci: a = " + x + " b = " + y + " c = " + z);
                    }
                }
            }
        }
    }
    //umozliwi wypisanie wszystkich podzbiorow zbioru n elementowego
    public static void podzbior_4() {
        int b1, b2, b3, b4;
        for (b1 = 0; b1 <= 1; b1++) {
            for (b2 = 0; b2 <= 1; b2++) {
                for (b3 = 0; b3 <= 1; b3++) {
                    for (b4 = 0; b4 <= 1; b4++) {
                        System.out.print("{ ");
                        if(b1 == 1) System.out.print("1 ");
                        if(b2 == 1) System.out.print("2 ");
                        if(b3 == 1) System.out.print("3 ");
                        if(b4 == 1) System.out.print("4 ");
                        System.out.println("}");
                    }
                }
            }
        }
    }
    public static void podzbior_n(){
        System.out.print("podaj moc zbioru: ");
        Scanner scanner = new Scanner(System.in);
        final int N = scanner.nextInt(); //moc zbioru
        int[] tab = new int[N+1];
        int s = (int)Math.pow(2,N);
        for (int i = 0; i < s; i++) {
            System.out.print("{ ");
            for (int j = 0; j < N; j++) {
                if (tab[j] == 1) {
                    System.out.print(j + 1 + " ");
                }
            }
            System.out.println("}");
            int x = 0;
            do{
                if (tab[x] == 1) {
                    tab[x] = 0;
                    x++;
                } else {
                    tab[x] = 1;
                    break;
                }
            }while (true);
        }
    }
    public static void podzbior_plecak(){
        final int plecak_obj = 10;
        final int N = 5;
        int[] item_obj = {6, 2, 3, 2, 3, 1};
        int[] item_wart = {6, 4, 5, 7, 10, 2};
    }
}