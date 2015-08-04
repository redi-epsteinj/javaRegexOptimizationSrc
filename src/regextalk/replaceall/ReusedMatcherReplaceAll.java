package regextalk.replaceall;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import regextalk.RegexToBenchmark;

public class ReusedMatcherReplaceAll implements ReplaceAllToBenchmark {

   public static void main(String[] cmd_lineParams) {
      new ReusedMatcherReplaceAll().runCodeToBeTimed();
   }

   public static final Matcher
         matcher =
         Pattern.compile(FIND_WHAT_REGEX).matcher(
               RegexToBenchmark.IGNORED_INPUT);

   @Override
   public void runCodeToBeTimed() {
      Arrays.stream(getInputs()).forEach(input -> {
         System.out.print("\"" + input + "\"    ->    \"");
         String replaced = matcher.reset(input).replaceAll(REPLACE_WITH);
         System.out.println(replaced + "\"");
      });
   }
}
