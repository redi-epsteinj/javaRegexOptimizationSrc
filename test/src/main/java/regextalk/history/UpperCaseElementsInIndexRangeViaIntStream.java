package regextalk.history;

import static java.util.stream.Collectors.joining;

import java.util.Arrays;
import java.util.stream.IntStream;

public class UpperCaseElementsInIndexRangeViaIntStream {

   public static void main(String[] ignored) {
      final String input = "one two three four five six seven eight";
      final int MIN_IDX = 2;
      final int MAX_IDX = 5;
      final String[] splits = input.split(" ");

      //Filter only those in range

      String output = IntStream.range(0, splits.length).
            filter(idx -> MIN_IDX <= idx && idx <= MAX_IDX).
            mapToObj(i -> splits[i]).
            collect(joining(" "));

      System.out.println(output);

      //Change the array in place.

      IntStream.range(0, splits.length).forEach(idx -> {
         final String split = splits[idx];
         splits[idx] = (MIN_IDX <= idx && idx <= MAX_IDX)
                       ? split.toUpperCase() : split;
      });

      output = Arrays.stream(splits).collect(joining(" "));

      System.out.println(output);
   }
}
