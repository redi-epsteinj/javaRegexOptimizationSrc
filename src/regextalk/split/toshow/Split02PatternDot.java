package regextalk.split.toshow;

import static java.util.stream.Collectors.joining;

import java.util.Arrays;
import java.util.regex.Pattern;

public class Split02PatternDot {

   public static void main(String[] ignored) {
      String regex = "[,./ ]+";
      String[] inputs = Split01StringDot.newInputs();

      Arrays.stream(inputs).forEach(input -> {

         String[] splits = Pattern.compile(regex).split(input);

         String joined = Arrays.stream(splits).collect(joining(" "));

         System.out.println('"' + input + "\" -> \"" + joined + '"');
      });
   }
}
