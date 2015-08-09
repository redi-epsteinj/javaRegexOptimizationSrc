package regextalk.numericrange;

import java.util.Arrays;
import regextalk.AbstractReusedMatcherToBenchmark;

public class RangeRegexAnchoredFind extends AbstractReusedMatcherToBenchmark {

   public static final String[] INPUTS = new String[]{"-2056", "-2055", "-10", "0", "10", "2055",
                                                      "2056"};

   public static void main(String[] cmd_lineParams) {
      new RangeRegexAnchoredFind().setupRunBreakdown();
   }

   public String getRegex() {
      return "^" + RangeRegexUnanchoredFind.REGEX + "$";
   }

   @Override
   public String[] getInputs() {
      return INPUTS;
   }

   @Override
   public void runCodeToBeTimed() {
      Arrays.stream(getInputs()).forEach(input -> {
         boolean inRange = getMatcher().reset(input).find();
         System.out.println(input + ": " + (inRange ? "I" : "NOT i") + "n range");
      });
   }
}
