package regextalk.numericrange.toshow;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import regextalk.numericrange.FourHundred01EveryNumberInOrToBenchmark;

public class FourHundred12Logic {

   public static int MAX_INT = 400;
   public static int MIN_INT = MAX_INT * -1;

   public static void main(String[] ignored) {

      List<Integer> intList = FourHundred01EveryNumberInOrToBenchmark.newIntInputs();
      int[] inputs = intList.stream().mapToInt(i -> i).toArray();

      Arrays.stream(inputs).forEach(input -> {

         if(MIN_INT <= input && input <= MAX_INT) {
            System.out.printf("\"%d\" is in range.%n", input);
         } else {
            System.out.printf("\"%d\" is *NOT* in range.%n", input);
         }
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
