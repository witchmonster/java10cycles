package foo.presentation;

import org.junit.Test;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by juliakram on 15/06/16.
 */
public class StreamDemo {

  static List<String> stringList = Arrays.asList(
          "From fairest creatures we desire increase",
          "That thereby beauty's rose might never die",
          "But as the riper should by time decease",
          "His tender heir might bear his memory:",
          "But thou contracted to thine own bright eyes",
          "Feed'st thy light's flame with self-substantial fuel,",
          "Making a famine where abundance lies,",
          "Thy self thy foe, to thy sweet self too cruel:",
          "Thou that art now the world's fresh ornament,",
          "And only herald to the gaudy spring,",
          "Within thine own bud buriest thy content,",
          "And, tender churl, mak'st waste in niggarding:",
          "Pity the world, or else this glutton be,",
          "To eat the world's due, by the grave and thee."
  );

  @Test
  public void sortedLowrCase()
          throws IOException {
    List<String> output = stringList
            .stream()
            .flatMap(line -> Stream.of(line.split("\\W+")))
            .filter(word -> word.length() > 0)
//                .map(s -> s.toLowerCase())
            .map(String::toLowerCase)
            .sorted()
            .skip(2)
            .limit(10)
            .distinct()
//                .reduce("", (word1, word2) -> word1 + " " + word2)
//                .count()
//                .findFirst()
//                .findAny()
            .collect(Collectors.toList());

//        output.stream().reduce("", (word1, word2) -> word1 + " " + word2);

    output.stream().forEach(System.out::println);

  }
}
