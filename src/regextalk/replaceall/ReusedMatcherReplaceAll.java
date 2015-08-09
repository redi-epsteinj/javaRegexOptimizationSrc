package regextalk.replaceall;

import java.util.Arrays;

import regextalk.AbstractReusedMatcherToBenchmark;

public class ReusedMatcherReplaceAll extends AbstractReusedMatcherToBenchmark implements ReplaceAllToBenchmark {

   public static void main(String[] cmd_lineParams) {
      new ReusedMatcherReplaceAll().setupRunBreakdown();
   }

   @Override
   public void runCodeToBeTimed() {
      Arrays.stream(getInputs()).forEach(input -> {
         System.out.print("\"" + input + "\"    ->    \"");
         String replaced = getMatcher().reset(input).replaceAll(REPLACE_WITH);
         System.out.println(replaced + "\"");
      });
   }
}
