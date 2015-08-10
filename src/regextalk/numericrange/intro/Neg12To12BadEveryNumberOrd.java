package regextalk.numericrange.intro;

import regextalk.AbstractReusedMatcherToBenchmark;
import regextalk.MatchOrFind;

public class Neg12To12BadEveryNumberOrd extends AbstractReusedMatcherToBenchmark {

   public Neg12To12BadEveryNumberOrd() {
      super(MatchOrFind.FIND);
   }

   public static void main(String[] ignored) {
      new Neg12To12BadEveryNumberOrd().setupRunBreakdown();
   }

   @Override
   public String[] getInputs() {
      return Neg12To12Good0t9First.INPUTS;
   }

   @Override
   public String getRegex() {
      return "(-12|-11|-10|-9|-8|-7|-6|-5|-4|-3|-2|-1|0|1|2|3|4|5|6|7|8|9|10|11|12)";
      //No bounds, captured, ridiculous.
   }
}
