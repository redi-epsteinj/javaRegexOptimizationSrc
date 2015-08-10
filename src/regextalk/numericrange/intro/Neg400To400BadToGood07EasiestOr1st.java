package regextalk.numericrange.intro;

public class Neg400To400BadToGood07EasiestOr1st extends AbstractNeg400To400 {
   public static void main(String[] ignored) {
      new Neg400To400BadToGood07EasiestOr1st().setupRunBreakdown();
   }


   @Override
   public String[] getInputs() { return Neg400To400BadToGood00EveryNumberOrd.INPUTS; }

   public static final String ONE_TO_400 =
         "400|" +
         "[1-9]?[0-9]|" +
         "[1-3][0-9]{2}";

   @Override
   public String getRegex() {
      return "(?:" + Neg400To400BadToGood02Dash.ONE_OR_MORE_ZEROS_NOT_PRECEDED_BY_DASH + "|-?(?:" +
             ONE_TO_400 + "))";
   }
}
