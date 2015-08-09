package regextalk.numericrange.intro.temp;

import regextalk.AbstractReusedMatcherToBenchmark;

public class Neg400To400Good400Last extends AbstractReusedMatcherToBenchmark {

   public static final String[] INPUTS = new String[]{
         "-401", "-400", "-11", "-10", "0", "400", "401", "Jimmy", "u390x"};

   public static void main(String[] ignored) {
      new Neg400To400Good400Last().setupRunBreakdown();
   }

   @Override
   public String getRegex() {
      return "-?\\b(?:[1-3]?\\d{1,2}|400)\\b";
   }
   
   @Override
   public String[] getInputs() {
      return INPUTS;
   }
}
