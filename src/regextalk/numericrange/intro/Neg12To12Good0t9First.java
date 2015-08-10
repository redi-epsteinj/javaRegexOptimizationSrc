package regextalk.numericrange.intro;

import regextalk.AbstractReusedMatcherToBenchmark;
import regextalk.MatchOrFind;

public class Neg12To12Good0t9First extends AbstractReusedMatcherToBenchmark {

   public Neg12To12Good0t9First() {
      super(MatchOrFind.FIND);
   }

   public static final String[] INPUTS = new String[]{
         "-13", "-12", "-11", "-10", "-9", "-8", "-7", "-6", "-5", "-4", "-3", "-2", "-1",
         "0",
         "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13"};

   public static void main(String[] ignored) {
      new Neg12To12Good0t9First().setupRunBreakdown();
   }

   @Override
   public String getRegex() {
      return "-?\\b(?:[0-9]|1[0-2])\\b";
   }
   
   @Override
   public String[] getInputs() {
      return INPUTS;
   }
}
