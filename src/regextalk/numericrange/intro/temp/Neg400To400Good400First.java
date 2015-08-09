package regextalk.numericrange.intro.temp;

import regextalk.AbstractReusedMatcherToBenchmark;

public class Neg400To400Good400First extends AbstractReusedMatcherToBenchmark {
   public static void main(String[] ignored) {
      new Neg400To400Good400First().setupRunBreakdown();
   }

   @Override
   public String getRegex() {
      return "-?\\b(?:400|[1-3]?\\d{1,2})\\b";
   }
   
   @Override
   public String[] getInputs() {
      return Neg400To400Good400Last.INPUTS;
   }
}
