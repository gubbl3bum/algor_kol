public class DynamicProgrammingExample {

    public static int calculateF(int n) {
        if (n == 1) {
            return 1;
        }
        if (n == 2) {
            return 2;
        }
        if (n == 3) {
            return 3;
        }

        int[] dp = new int[n + 1];
        dp[1] = 1;
        dp[2] = 2;
        dp[3] = 3;

        for (int i = 4; i <= n; i++) {
            if (i % 2 == 0) {
                dp[i] = dp[i - 1] * dp[i - 3];
            } else {
                dp[i] = dp[i - 2] * dp[i - 2];
            }
        }

        return dp[n];
    }

    public static void main(String[] args) {
        int n = 10; // Możesz zmienić wartość n na dowolną, aby uzyskać wynik dla innego elementu ciągu
        int result = calculateF(n);
        System.out.println("f(" + n + ") = " + result);
    }
}
