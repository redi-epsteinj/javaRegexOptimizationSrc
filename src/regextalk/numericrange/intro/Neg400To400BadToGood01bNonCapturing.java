package regextalk.numericrange.intro;

public class Neg400To400BadToGood01bNonCapturing extends AbstractNeg400To400 {

   public static void main(String[] ignored) {
      new Neg400To400BadToGood01bNonCapturing().setupRunBreakdown();
   }

   @Override
   public String[] getInputs() { return Neg400To400BadToGood00EveryNumberOrd.INPUTS; }

   @Override
   public String getRegex() {
      return "(?:" + Neg400To400BadToGood00EveryNumberOrd.NEG_400_TO_400 + ")";
   }
}
