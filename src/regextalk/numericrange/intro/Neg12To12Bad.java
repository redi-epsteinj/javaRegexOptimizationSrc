package regextalk.numericrange.intro;

import regextalk.AbstractReusedMatcherToBenchmark;

public class Neg12To12Bad extends AbstractReusedMatcherToBenchmark {

   public static void main(String[] ignored) {
      new Neg12To12Bad().setupRunBreakdown();
   }

   @Override
   public String[] getInputs() {
      return Neg12To12Good.INPUTS;
   }

   @Override
   public String getRegex() {
      return "(-12|-11|-10|-9|-8|-7|-6|-5|-4|-3|-2|-1|0|1|2|3|4|5|6|7|8|9|10|11|12)";
      //No bounds, captured, ridiculous.
   }
}
