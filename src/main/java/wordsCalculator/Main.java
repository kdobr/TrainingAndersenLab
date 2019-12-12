package wordsCalculator;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {

        ;

        try (BufferedReader reader =
                     new BufferedReader(new InputStreamReader(Main.class.getClassLoader().getResourceAsStream("test.txt")))) {
            List<String> result = reader.lines()
                    .map(String::trim)
                    .map(l -> l.replaceAll("[^\\w\\s]", ""))
                    .flatMap(l -> Arrays.stream(l.split(" ")))
                    .filter(s -> !s.equals(""))
                    .collect(ArrayList::new, ArrayList::add, ArrayList::addAll);

            System.out.println(result + "\n" + result.size());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

