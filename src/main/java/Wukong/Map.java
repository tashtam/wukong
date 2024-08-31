package Wukong;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Collectors;

public class Map {


    public static void map(String mapName) throws IOException {
        final Path fileName = Paths.get("src/main/maps/" + mapName + ".txt");
        String content = Files.lines(fileName).collect(Collectors.joining(System.lineSeparator()));
        System.out.println(content);
    }
}
