package regextalk.history;

import static java.util.stream.Collectors.joining;

import java.util.Arrays;
import java.util.function.Predicate;
import java.util.regex.Pattern;

public class PredicateStreamInvalidType {
   public static void main(String[] ignored) {

      String input = "one two three four five six seven eight";

      Predicate<String> containsAnE = Pattern.compile("e").asPredicate();

      String[] splits = input.split(" ");

      String output = Arrays.stream(splits).
            filter(containsAnE).
            collect(joining(" "));

      System.out.println(output);        //"one three five seven eight"
   }
}
