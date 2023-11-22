import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MonteCarloAlgorith {
    public static void main(String[] args) {
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

    private static String getCityName(int index) {
        String[] cities = {"Warszawa", "Katowice", "Zakopane", "Lwów", "Wiedeń", "Budapeszt", "Bukareszt", "Zagrzeb", "Sofia"};
        return cities[index];
    }

    static class Result {
        int distance;
        List<Integer> path;
    }
}
