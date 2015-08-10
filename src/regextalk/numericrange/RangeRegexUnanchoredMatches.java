package regextalk.numericrange;

import regextalk.AbstractReusedMatcherToBenchmark;
import regextalk.MatchOrFind;

public class RangeRegexUnanchoredMatches extends AbstractReusedMatcherToBenchmark {

   public RangeRegexUnanchoredMatches() {
       super(MatchOrFind.MATCH);
   }

   public static void main(String[] cmd_lineParams) {
      new RangeRegexUnanchoredMatches().setupRunBreakdown();
   }

   @Override
   public String getRegex() {
      return RangeRegexUnanchoredFind.REGEX;
   }

   @Override
   public String[] getInputs() {
      return RangeRegexAnchoredFind.INPUTS;
   }
}
