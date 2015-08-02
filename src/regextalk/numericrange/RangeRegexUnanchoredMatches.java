package regextalk.numericrange;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RangeRegexUnanchoredMatches implements NumericRangeToBenchmark {

   public static void main(String[] cmd_lineParams) {
      new RangeRegexUnanchoredMatches().runCodeToBeTimed();
   }

   @Override
   public void runCodeToBeTimed() {
      Matcher matcher = Pattern.compile(RangeRegexUnanchoredFind.UNANCHORED_REGEX).matcher(IGNORED_INPUT);

      Arrays.stream(getInputs()).forEach(input -> {
         System.out.print(input + ": ");
         boolean inRange = matcher.reset(input).matches();
         System.out.println(inRange);
      });
   }
}
