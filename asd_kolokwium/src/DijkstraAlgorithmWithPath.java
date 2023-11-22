import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;

public class DijkstraAlgorithmWithPath {
    private static final int INFINITY = Integer.MAX_VALUE;

    public static void main(String[] args) {
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
        Result result = dijkstra(distances, 0, 8);

        // Wypisanie wyniku
        System.out.println("Najkrótsza droga z Warszawy do Sofii ma długość " + result.distance + " km i prowadzi przez: ");
        for (Integer cityIndex : result.path) {
            System.out.print(getCityName(cityIndex) + " ");
        }
    }

    private static Result dijkstra(int[][] graph, int start, int destination) {
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

        Result result = new Result();
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
}
