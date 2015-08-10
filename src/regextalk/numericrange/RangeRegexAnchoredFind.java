package regextalk.numericrange;

import regextalk.AbstractReusedMatcherToBenchmark;
import regextalk.MatchOrFind;

public class RangeRegexAnchoredFind extends AbstractReusedMatcherToBenchmark {

   public static final String[] INPUTS = new String[]{"-2056", "-2055", "-10", "0", "10", "2055",
                                                      "2056"};

   public RangeRegexAnchoredFind() {
       super(MatchOrFind.FIND);
   }
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
}
