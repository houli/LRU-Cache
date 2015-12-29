import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) {
        Cache cache = new LRUCache(16, 8, 1);
        try (InputStream stream = Main.class.getResourceAsStream("addresses.txt")) {
            BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
            reader.lines().map(line -> Integer.parseInt(line, 16))
                .forEach(address -> {
                    System.out.println(cache.access(address));
                });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
