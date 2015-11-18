package regextalk.replaceall;

import java.util.Arrays;

/**
 * Benchmarks using my own custom benchmark library, meaning unreliable. Use the JMH benchmarks instead.
 */
public class ReplaceAll01StringDotToBenchmark implements ReplaceAllToBenchmark {

   public static void main(String[] cmd_lineParams) {
      new ReplaceAll01StringDotToBenchmark().setupRunBreakdown();
   }

   @Override
   public void runCodeToBeTimed() {
      Arrays.stream(getInputs()).forEach(input -> {
         System.out.print("\"" + input + "\"    ->    \"");
         String replaced = input.replaceAll(getRegex(), REPLACE_WITH);
         System.out.println(replaced + "\"");
      });
   }
}
