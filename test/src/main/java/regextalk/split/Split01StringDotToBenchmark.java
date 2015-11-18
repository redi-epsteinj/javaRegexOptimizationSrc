package regextalk.split;

import java.util.Arrays;

/**
 * Benchmarks using my own custom benchmark library, meaning unreliable. Use the JMH benchmarks instead.
 */
public class Split01StringDotToBenchmark extends AbstractSplit {

   public Split01StringDotToBenchmark(String regex) {
      super(regex);
   }

   public static void main(String[] cmd_lineParams) {
      new Split01StringDotToBenchmark(REGEX_SPACE).setupRunBreakdown();
      new Split01StringDotToBenchmark(REGEX_2_PLUS).setupRunBreakdown();
   }

   @Override
   public void runCodeToBeTimed() {
      Arrays.stream(newInputs()).forEach(input -> {
         System.out.println(input);
         System.out.println("\t" + Arrays.toString(
               input.split(getRegex(), -1)
         ));
      });
   }
}
