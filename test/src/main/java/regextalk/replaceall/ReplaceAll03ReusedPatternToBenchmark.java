package regextalk.replaceall;

import java.util.Arrays;
import java.util.regex.Pattern;

public class ReplaceAll03ReusedPatternToBenchmark implements ReplaceAllToBenchmark {

   public static void main(String[] cmd_lineParams) {
      new ReplaceAll03ReusedPatternToBenchmark().setupRunBreakdown();
   }

   private Pattern pattern;

   @Override
   public void setup() {
      pattern = Pattern.compile(getRegex());
      System.out.println(getClass().getSimpleName() + ": " + getRegex());
   }

   @Override
   public void runCodeToBeTimed() {
      Arrays.stream(getInputs()).forEach(input -> {
         System.out.print("\"" + input + "\"    ->    \"");
         String replaced = pattern.matcher(input).replaceAll(REPLACE_WITH);
         System.out.println(replaced + "\"");
      });
   }
}
