package regextalk.numericrange.intro;

import regextalk.AbstractReusedMatcherToBenchmark;
import regextalk.MatchOrFind;

public class Neg400To400BadToGood01bNonCapturing extends AbstractReusedMatcherToBenchmark {

   public Neg400To400BadToGood01bNonCapturing() {
      super(MatchOrFind.FIND);
   }

   public static void main(String[] ignored) {
      new Neg400To400BadToGood01bNonCapturing().setupRunBreakdown();
   }

   @Override
   public String[] getInputs() { return Neg400To400EveryNumberOrd.INPUTS; }

   @Override
   public String getRegex() {
      return "(?:" + Neg400To400EveryNumberOrd.NEG_400_TO_400 + ")";
   }
}
