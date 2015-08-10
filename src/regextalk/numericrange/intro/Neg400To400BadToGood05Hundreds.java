package regextalk.numericrange.intro;

public class Neg400To400BadToGood05Hundreds extends AbstractNeg400To400 {
   public static void main(String[] ignored) {
      new Neg400To400BadToGood05Hundreds().setupRunBreakdown();
   }

   @Override
   public String[] getInputs() { return Neg400To400BadToGood00EveryNumberOrd.INPUTS; }

   public static final String ONE_TO_400 =
         "[0-9]|" +
         "[1-9][0-9]|" +
         "[1-3][0-9][0-9]|" +
         "400";

   @Override
   public String getRegex() {
      return "\\b(?:" + Neg400To400BadToGood02Dash.ONE_OR_MORE_ZEROS_NOT_PRECEDED_BY_DASH + "|-?(?:" +
             ONE_TO_400 + "))\\b";
   }
}
