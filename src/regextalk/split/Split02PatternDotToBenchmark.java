package regextalk.split;

import java.util.Arrays;
import java.util.regex.Pattern;

public class Split02PatternDotToBenchmark extends AbstractSplit {

   public Split02PatternDotToBenchmark(String regex) {
       super(regex);
   }

   public static void main(String[] cmd_lineParams) {
      new Split02PatternDotToBenchmark(REGEX_SPACE).setupRunBreakdown();
      new Split02PatternDotToBenchmark(REGEX_2_PLUS).setupRunBreakdown();
   }

   @Override
   public void runCodeToBeTimed() {
      Arrays.stream(newInputs()).forEach(input -> {
         System.out.println(input);
         System.out.println(Arrays.toString(
               Pattern.compile(getRegex()).split(input)
         ));
      });
   }
}
