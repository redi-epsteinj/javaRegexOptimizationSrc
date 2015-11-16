package regextalk.numericrange;

public class FourHundred10FurtherConsolidationToBenchmark extends AbstractNeg400To400 {
   public static void main(String[] ignored) {
      new FourHundred10FurtherConsolidationToBenchmark().setupRunBreakdown();
   }

   @Override
   public String[] getInputs() { return FourHundred01EveryNumberInOrToBenchmark.INPUTS; }

   public static final String ONE_TO_400 =
         "[1-9]?[0-9]|" +
         "[1-3][0-9]{2}|" +
         "400";

   @Override
   public String getRegex() {
      return "(?:" + FourHundred05OptionalDashToBenchmark.ONE_OR_MORE_ZEROS_NOT_PRECEDED_BY_DASH + "|-?(?:" +
             ONE_TO_400 + "))";
   }
}
