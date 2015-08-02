package regextalk.split;

import java.util.Arrays;
import java.util.regex.Pattern;

public class PatternDotSplit implements SplitToBenchmark {

   public static void main(String[] cmd_lineParams) {
      new PatternDotSplit().runCodeToBeTimed();
   }

   @Override
   public void runCodeToBeTimed() {
      Arrays.stream(getInputs()).forEach(input -> {
         System.out.println(input);
         System.out.println(Arrays.toString(
            Pattern.compile(REGEX).split(input)
         ));
      });
   }
}
