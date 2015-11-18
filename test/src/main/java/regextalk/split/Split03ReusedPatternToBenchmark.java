package regextalk.split;

import java.util.Arrays;
import java.util.regex.Pattern;

/**
 * Benchmarks using my own custom benchmark library, meaning unreliable. Use the JMH benchmarks instead.
 */
public class Split03ReusedPatternToBenchmark extends AbstractSplit {

   public Split03ReusedPatternToBenchmark(String regex) {
      super(regex);
   }

   public static void main(String[] cmd_lineParams) {
      new Split03ReusedPatternToBenchmark(REGEX_SPACE).setupRunBreakdown();
      new Split03ReusedPatternToBenchmark(REGEX_2_PLUS).setupRunBreakdown();
   }


   @Override
   public void runCodeToBeTimed() {
      Pattern pattern = Pattern.compile(getRegex());
      Arrays.stream(newInputs()).forEach(input -> {
         System.out.println(input);
         System.out.println(Arrays.toString(
               pattern.split(input)
         ));
      });
   }
}
