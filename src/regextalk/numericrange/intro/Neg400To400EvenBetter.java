package regextalk.numericrange.intro;

import regextalk.AbstractReusedMatcherToBenchmark;

public class Neg400To400EvenBetter extends AbstractReusedMatcherToBenchmark {
   public static void main(String[] ignored) {
      new Neg400To400EvenBetter().setupRunBreakdown();
   }

   @Override
   public String getRegex() {
      return "-?\\b(?:400|[1-3]?[0-9]{1,2})\\b";
   }

   @Override
   public String[] getInputs() { return Neg400To400EveryNumberOrd.INPUTS; }
}
