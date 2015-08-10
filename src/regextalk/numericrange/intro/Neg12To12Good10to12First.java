package regextalk.numericrange.intro;

public class Neg12To12Good10to12First extends AbstractNeg400To400 {

   public static final String[] INPUTS = new String[]{
         "-13", "-12", "-11", "-10", "-9", "-8", "-7", "-6", "-5", "-4", "-3", "-2", "-1",
         "0",
         "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13"};

   public static void main(String[] ignored) {
      new Neg12To12Good10to12First().setupRunBreakdown();
   }

   @Override
   public String getRegex() {
      return "-?\\b(?:1[0-2]|[0-9])\\b";
   }
   
   @Override
   public String[] getInputs() {
      return INPUTS;
   }
}
