package regextalk.numericrange;

public class FourHundred11BestToBenchmark extends AbstractNeg400To400 {

   public static void main(String[] ignored) {
      new FourHundred11BestToBenchmark().setupRunBreakdown();
   }

   //@formatter:off
      public static final String REGEX = "" +
         "\\b" +
         "(?:" +
            "(?<!-)0+|" +        //Zero or more "0"-s, as long as not preceded by dash
            "-?" +               //Optional dash, followed by 1..400
            "(?:" +
               "400|" +
               "[1-9]?[0-9]|" +
               "[1-3][0-9][0-9]" +
            ")" +
         ")\\b";
//@formatter:on


   @Override
   public String getRegex() { return REGEX; }

   @Override
   public String[] getInputs() { return FourHundred01EveryNumberInOrToBenchmark.INPUTS; }
}
