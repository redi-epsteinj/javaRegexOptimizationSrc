package regextalk.numericrange.toshow;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import regextalk.numericrange.FourHundred01EveryNumberInOrToBenchmark;

public class FourHundred12Logic {

   public static void main(String[] ignored) {

      List<Integer> intList = FourHundred01EveryNumberInOrToBenchmark.newIntInputs();
      int[] inputs = intList.stream().mapToInt(i -> i).toArray();

      Arrays.stream(inputs).forEach(input -> {
         boolean inRange = (-400 <= input && input <= 400);
         System.out.printf("\"%d\" is %sin range.%n", input, (inRange ? "" : "*NOT* "));
      });
   }

   public static final int[] newIntInputs() {
      //All numbers, including some non-valid
      List<Integer> intList = IntStream.range(-450, 450).boxed().collect(Collectors.toList());

      Collections.shuffle(intList);

      final int size = intList.size();
      intList.subList(size / 10, size).clear();
      Collections.sort(intList);

      //Now a fraction the size of the original, with random elements from it.

      return  intList.stream().mapToInt(i -> i).toArray();
   }
}
