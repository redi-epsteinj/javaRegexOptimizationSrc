package regextalk.numericrange;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 */
public class RangeRegexUnanchoredFind implements NumericRangeToBenchmark {

   public static final String UNANCHORED_REGEX =
      "(" +            //Capture group for the entire number
         "-?\\b" +            //Optional dash, followed by a word (number) boundary
         "(?:20" +            //Followed by "20", which is followed by one of
               "(?:5[0-5]" +        //50 through 55
            "|" +                      //or
               "[0-4][0-9])" +      //00 through 49
         "|" +                                         //or
            "1[0-9]{3}" +        //a one followed by any three digits
         "|" +                                         //or
            "[1-9][0-9]{0,2}" +  //1-9 followed by 0 through 2 of any digit
         "|" +                                         //or
            "(?<!-)0+" +         //one-or-more zeros *not* preceded by a dash
         ")" +            //end "or" non-capture group
      ")\\b";      //End number capture group, followed by a word-bound

   public static void main(String[] cmd_lineParams) {
      new RangeRegexUnanchoredFind().runCodeToBeTimed();
   }

   @Override
   public void runCodeToBeTimed() {
      Matcher matcher = Pattern.compile(RangeRegexUnanchoredFind.UNANCHORED_REGEX).matcher(IGNORED_INPUT);

      Arrays.stream(getInputs()).forEach(input -> {
         System.out.print(input + ": ");
         boolean inRange = matcher.reset(input).find();
         System.out.println(inRange);
      });
   }
}
