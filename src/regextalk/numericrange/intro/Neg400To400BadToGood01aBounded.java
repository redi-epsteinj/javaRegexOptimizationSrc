package regextalk.numericrange.intro;

public class Neg400To400BadToGood01aBounded extends AbstractNeg400To400 {

   public static void main(String[] ignored) {
      new Neg400To400BadToGood01aBounded().setupRunBreakdown();
   }

   @Override
   public String[] getInputs() { return Neg400To400BadToGood00EveryNumberOrd.INPUTS; }

   @Override
   public String getRegex() {
      return "\\b(" + Neg400To400BadToGood00EveryNumberOrd.NEG_400_TO_400 + ")\\b";
   }
}
