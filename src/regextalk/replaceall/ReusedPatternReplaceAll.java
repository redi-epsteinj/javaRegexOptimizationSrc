package regextalk.replaceall;

import java.util.Arrays;
import java.util.regex.Pattern;

/**
 *
 */
public class ReusedPatternReplaceAll implements ReplaceAllToBenchmark {

   public static void main(String[] cmd_lineParams) {
      new ReusedPatternReplaceAll().runCodeToBeTimed();
   }

   public static final Pattern pattern = Pattern.compile(FIND_WHAT_REGEX);

   @Override
   public void runCodeToBeTimed() {
      Arrays.stream(getInputs()).forEach(input -> {
         System.out.print("\"" + input + "\"    ->    \"");
         String replaced = pattern.matcher(input).replaceAll(REPLACE_WITH);
         System.out.println(replaced + "\"");
      });
   }
}
