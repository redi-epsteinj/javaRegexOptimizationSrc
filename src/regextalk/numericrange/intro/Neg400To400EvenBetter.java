package regextalk.numericrange.intro;

public class Neg400To400EvenBetter extends AbstractNeg400To400 {

   public static void main(String[] ignored) {
      new Neg400To400EvenBetter().setupRunBreakdown();
   }

   @Override
   public String getRegex() {
      return "-?\\b(?:400|[1-3]?[0-9]{1,2})\\b";
   }

   @Override
   public String[] getInputs() { return Neg400To400BadToGood00EveryNumberOrd.INPUTS; }
}
