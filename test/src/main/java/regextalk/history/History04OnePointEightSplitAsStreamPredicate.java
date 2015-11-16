package regextalk.history;

import static java.util.stream.Collectors.joining;

import java.util.Arrays;
import java.util.function.Predicate;
import java.util.regex.Pattern;

public class History04OnePointEightSplitAsStreamPredicate {
   public static void main(String[] ignored) {

      String regex = " ";
      String input = "one two three four five six seven eight";

      //splitAsStream example

      String output = Pattern.compile(regex).splitAsStream(input).
            map(split -> split.contains("e") ? split.toUpperCase() : split).
            collect(joining(" "));

      System.out.println(output);  //"ONE two THREE four FIVE six SEVEN EIGHT"


      //Predicate example

      String regexContainsOneOrMoreEs = "e+";

      Predicate<String> predicateContainsAnE = Pattern.compile(regexContainsOneOrMoreEs).asPredicate();

      String[] splits = input.split(" ");
      output = Arrays.stream(splits).
            filter(predicateContainsAnE).
            collect(joining(" "));

      System.out.println(output);        //"one three five seven eight"
   }


}
