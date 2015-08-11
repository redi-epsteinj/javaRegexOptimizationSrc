package regextalk.numericrange;

public class FourHundred09HundredsDigitTo0to9ToBenchmark extends AbstractNeg400To400 {
   public static void main(String[] ignored) {
      new FourHundred09HundredsDigitTo0to9ToBenchmark().setupRunBreakdown();
   }

   @Override
   public String[] getInputs() { return FourHundred01EveryNumberInOrToBenchmark.INPUTS; }

   public static final String ONE_TO_400 =
         "[0-9]|" +
         "[1-9][0-9]|" +
         "[1-3][0-9][0-9]|" +
         "400";

   @Override
   public String getRegex() {
      return "\\b(?:" + FourHundred05OptionalDashToBenchmark.ONE_OR_MORE_ZEROS_NOT_PRECEDED_BY_DASH + "|-?(?:" +
             ONE_TO_400 + "))\\b";
   }
}
