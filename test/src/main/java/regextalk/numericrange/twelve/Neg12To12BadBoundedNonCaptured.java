package regextalk.numericrange.twelve;

import regextalk.numericrange.AbstractNeg400To400;

public class Neg12To12BadBoundedNonCaptured extends AbstractNeg400To400 {

   public static void main(String[] ignored) {
      new Neg12To12BadBoundedNonCaptured().setupRunBreakdown();
   }

   @Override
   public String[] getInputs() {
      return Neg12To12Good0t9First.INPUTS;
   }

   @Override
   public String getRegex() {
      return "\\b(?:-12|-11|-10|-9|-8|-7|-6|-5|-4|-3|-2|-1|0|1|2|3|4|5|6|7|8|9|10|11|12)\\b";
   }
}
