package regextalk.numericrange.intro;

import regextalk.AbstractReusedMatcherToBenchmark;
import regextalk.MatchOrFind;

public class Neg400To400BadToGood04Tens extends AbstractReusedMatcherToBenchmark {
   public Neg400To400BadToGood04Tens() {
       super(MatchOrFind.MATCH);
   }

   public static void main(String[] ignored) {
      new Neg400To400BadToGood04Tens().setupRunBreakdown();
   }

   @Override
   public String[] getInputs() {
      return Neg400To400Good400Last.INPUTS;
   }

   public static final String ONE_TO_400 =
         "[0-9]|" +
         "[1-9][0-9]|" +
         "1[0-9][0-9]|" +
         "2[0-9][0-9]|" +
         "3[0-9][0-9]|" +
         "400";

   @Override
   public String getRegex() {
      return "\\b(?:" + Neg400To400BadToGood02Dash.ONE_OR_MORE_ZEROS_NOT_PRECEDED_BY_DASH +
             "|-?(?:" + ONE_TO_400 + "))\\b";
   }
}
