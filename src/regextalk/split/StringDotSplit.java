package regextalk.split;

import java.util.Arrays;

public class StringDotSplit implements SplitToBenchmark {

   public static void main(String[] cmd_lineParams) {
      new StringDotSplit().runCodeToBeTimed();
   }

   @Override
   public void runCodeToBeTimed() {
      Arrays.stream(getInputs()).forEach(input -> {
         System.out.println(input);
         System.out.println("\t" + Arrays.toString(
               input.split(REGEX, -1)
         ));
      });
   }
}
