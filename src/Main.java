import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {
        Cache cache = new LRUCache(16, 8, 1);
        try (Stream<String> lines = Files.lines(Paths.get("resources", "addresses.txt"))) {
            lines.map(line -> Integer.parseInt(line, 16))
                .forEach(address -> {
                    System.out.println(cache.access(address));
                });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
