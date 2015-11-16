package regextalk.replaceall;

import java.util.Arrays;
import java.util.regex.Pattern;

/**
 *
 */
public class ReplaceAll02PatternDotToBenchmark implements ReplaceAllToBenchmark {

   public static void main(String[] cmd_lineParams) {
      new ReplaceAll02PatternDotToBenchmark().setupRunBreakdown();
   }

   @Override
   public void runCodeToBeTimed() {
      Arrays.stream(getInputs()).forEach(input -> {
         System.out.print("\"" + input + "\"    ->    \"");
         String replaced = Pattern.compile(getRegex()).matcher(input).replaceAll(REPLACE_WITH);
         System.out.println(replaced + "\"");
      });
   }
}
