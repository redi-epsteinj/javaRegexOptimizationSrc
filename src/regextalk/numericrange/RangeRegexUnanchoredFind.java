package regextalk.numericrange;

import java.util.Arrays;

import regextalk.AbstractReusedMatcherToBenchmark;

/**
 *
 */
public class RangeRegexUnanchoredFind extends AbstractReusedMatcherToBenchmark {

   public static final  String OPTIONAL_DASH_FOLLOWED_BY_A_NUMBER_BOUND = "-?\\b";
   private static final String FIFTY_TO_55__OR__0_TO_49                 = "5[0-5]|[0-4][0-9]";
   public static final  String A_ONE_FOLLOWED_BY_ANY_THREE_DIGITS       = "1[0-9]{3}";
   public static final  String ONE_TO_9_FOLLOWED_BY__ANY_DIGIT_ZERO_TO_TWO_TIMES = "[1-9][0-9]{0,2}";
   public static final  String ONE_OR_MORE_0S_NOT_PRECEDED_BY_DASH    = "(?<!-)0+";

   public static final String REGEX =
         "(" +            //Capture group for the entire number
            OPTIONAL_DASH_FOLLOWED_BY_A_NUMBER_BOUND +
            "(?:" +
               "20" +                                       //Followed by "20", which is followed by
               "(?:" + FIFTY_TO_55__OR__0_TO_49 + ")" +
               "|" +                                           //or
               A_ONE_FOLLOWED_BY_ANY_THREE_DIGITS +
               "|" +                                           //or
               ONE_TO_9_FOLLOWED_BY__ANY_DIGIT_ZERO_TO_TWO_TIMES +
               "|" +                                           //or
               ONE_OR_MORE_0S_NOT_PRECEDED_BY_DASH +
            ")" +                                          //end "or" non-capture group
         ")\\b";                                           //End number capture group, followed
                                                           //by a word-bound

   public static void main(String[] cmd_lineParams) {
      new RangeRegexUnanchoredFind().setupRunBreakdown();
   }

   @Override
   public String getRegex() {
      return REGEX;
   }

   @Override
   public String[] getInputs() {
      return RangeRegexAnchoredFind.INPUTS;
   }

   @Override
   public void runCodeToBeTimed() {
      Arrays.stream(getInputs()).forEach(input -> {
         boolean inRange = getMatcher().reset(input).find();
         System.out.printf("* %s: %sn range%n", input, (inRange ? "I" : "NOT i"));
      });
   }
}
