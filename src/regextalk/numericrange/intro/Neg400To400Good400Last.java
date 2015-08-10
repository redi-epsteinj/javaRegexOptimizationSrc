package regextalk.numericrange.intro;

import regextalk.AbstractReusedMatcherToBenchmark;

public class Neg400To400Good400Last extends AbstractNeg400To400 {
   public static final String[] INPUTS = new String[]{
         "-401", "-400", "-11", "-10", "0", "400", "401", "Jimmy", "u390x", "-0"};

   public static void main(String[] ignored) {
      new Neg400To400Good400Last().setupRunBreakdown();
   }

   @Override
   public String getRegex() {
      return "-?\\b(?:[1-3]?\\d{1,2}|400)\\b";
   }

   @Override
   public String[] getInputs() { return Neg400To400BadToGood00EveryNumberOrd.INPUTS; }
}
