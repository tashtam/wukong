package Wukong;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Collectors;

/**
 * @author Tianfa Zhu
 *
 * This class represents a map loader that reads and displays map content from a text file.
 */
public class Map {

    /**
     * Loads and prints the content of a map file.
     * The map file is expected to be located in the "src/main/maps/" directory with a ".txt" extension.
     *
     * @param mapName The name of the map file (without the extension) to be loaded.
     * @throws IOException If there is an issue reading the map file.
     */
    public static void map(String mapName) throws IOException {
        final Path fileName = Paths.get("src/main/maps/" + mapName + ".txt");
        String content = Files.lines(fileName).collect(Collectors.joining(System.lineSeparator()));
        System.out.println(content);
    }
}
