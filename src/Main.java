import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) {
        int[][] config = {
            {16, 1, 8, 0},
            {16, 2, 4, 0},
            {16, 4, 2, 0},
            {16, 8, 1, 0},
            {16, 1, 8, 1},
            {16, 2, 4, 1},
            {16, 4, 2, 1},
            {16, 8, 1, 1}
        };

        for (int i = 0; i < config.length; i++) {
            int l = config[i][0];
            int k = config[i][1];
            int n = config[i][2];
            // 0 is LRU, 1 is PSEUDO_LRU
            Cache.ReplacementPolicy policy = Cache.ReplacementPolicy.values()[config[i][3]];

            Cache cache = new Cache(l, k, n, policy);
            try (InputStream stream = Main.class.getResourceAsStream("addresses.txt")) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
                reader.lines().map(line -> Integer.parseInt(line, 16))
                    .forEach(address -> {
                        cache.access(address);
                    });
            } catch (IOException e) {
                e.printStackTrace();
            }

            int accesses = cache.getAccesses();
            int hits = cache.getHits();

            System.out.println("L: " + l + ", K: " + k + ", N: " + n + ", Policy: " + policy.toString().replace("_", " "));
            System.out.println("Accesses: " + accesses + ", Hits: " + hits + ", Hit rate: " +
                               (double) hits / accesses  + "\n");
        }
    }
}
