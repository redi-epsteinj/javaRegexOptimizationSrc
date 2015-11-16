package regextalk.numericrange;

public class FourHundred03NonCapturingOnlyToBenchmark extends AbstractNeg400To400 {

   public static void main(String[] ignored) {
      new FourHundred03NonCapturingOnlyToBenchmark().setupRunBreakdown();
   }

   @Override
   public String[] getInputs() { return FourHundred01EveryNumberInOrToBenchmark.INPUTS; }

   @Override
   public String getRegex() {
      return "(?:" + FourHundred01EveryNumberInOrToBenchmark.NEG_400_TO_400 + ")";
   }
}
