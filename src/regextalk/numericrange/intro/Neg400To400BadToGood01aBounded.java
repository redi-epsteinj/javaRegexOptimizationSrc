package regextalk.numericrange.intro;

import regextalk.AbstractReusedMatcherToBenchmark;
import regextalk.MatchOrFind;

public class Neg400To400BadToGood01aBounded extends AbstractReusedMatcherToBenchmark {

   public Neg400To400BadToGood01aBounded() {
      super(MatchOrFind.FIND);
   }

   public static void main(String[] ignored) {
      new Neg400To400BadToGood01aBounded().setupRunBreakdown();
   }

   @Override
   public String[] getInputs() { return Neg400To400EveryNumberOrd.INPUTS; }

   @Override
   public String getRegex() {
      return "\\b(" + Neg400To400EveryNumberOrd.NEG_400_TO_400 + ")\\b";
   }
}
