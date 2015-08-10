package regextalk.numericrange.intro;

import regextalk.AbstractReusedMatcherToBenchmark;

public class Neg400To400BadToGood01NonCapturingWithBounds extends AbstractReusedMatcherToBenchmark {

   public static void main(String[] ignored) {
      new Neg400To400BadToGood01NonCapturingWithBounds().setupRunBreakdown();
   }

   @Override
   public String[] getInputs() { return Neg400To400EveryNumberOrd.INPUTS; }

   @Override
   public String getRegex() {
      return "\\b(?:" + Neg400To400EveryNumberOrd.NEG_400_TO_400 + ")\\b";
   }
}
