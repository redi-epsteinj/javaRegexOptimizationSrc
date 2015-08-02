package regextalk.numericrange;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import regextalk.RegexToBenchmark;

public class RangeRegexAnchored implements NumericRangeToBenchmark {

   public static final String ANCHORED_REGEX =
      "^" + RangeRegexUnanchoredFind.UNANCHORED_REGEX + "$";

   public static void main(String[] cmd_lineParams) {
      new RangeRegexAnchored().runCodeToBeTimed();
   }


   @Override
   public void runCodeToBeTimed() {
      Matcher matcher = Pattern.compile(ANCHORED_REGEX).matcher(RegexToBenchmark.IGNORED_INPUT);

      Arrays.stream(getInputs()).forEach(input -> {
         System.out.print(input + ": ");

         if (matcher.reset(input).find()) {
            System.out.println("in range");
         } else {
            System.out.println("NOT in range");
         }
      });
   }
}
