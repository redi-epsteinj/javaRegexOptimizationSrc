package regextalk.numericrange;

public class FourHundred04BoundedAndNonCapturingToBenchmark extends AbstractNeg400To400 {
   public static void main(String[] ignored) {
      new FourHundred04BoundedAndNonCapturingToBenchmark().setupRunBreakdown();
   }

   @Override
   public String[] getInputs() { return FourHundred01EveryNumberInOrToBenchmark.INPUTS; }

   @Override
   public String getRegex() {
      return "\\b(?:" + FourHundred01EveryNumberInOrToBenchmark.NEG_400_TO_400 + ")\\b";
   }
}
