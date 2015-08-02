package regextalk.replaceall;

import java.util.Arrays;

public class StringDotReplaceAll implements ReplaceAllToBenchmark {

   public static void main(String[] cmd_lineParams) {
      new StringDotReplaceAll().runCodeToBeTimed();
   }

   @Override
   public void runCodeToBeTimed() {
      Arrays.stream(getInputs()).forEach(input -> {
         System.out.print("\"" + input + "\"    ->    \"");
         String replaced = input.replaceAll(FIND_WHAT_REGEX, REPLACE_WITH);
         System.out.println(replaced + "\"");
      });
   }
}
