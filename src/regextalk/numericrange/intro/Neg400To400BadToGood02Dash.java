package regextalk.numericrange.intro;

import static java.util.stream.Collectors.joining;

import java.util.Arrays;

public class Neg400To400BadToGood02Dash extends AbstractNeg400To400 {

   public static final String ONE_OR_MORE_ZEROS_NOT_PRECEDED_BY_DASH = "(?<!-)0+";

   public static void main(String[] ignored) {
      new Neg400To400BadToGood02Dash().setupRunBreakdown();
   }

   @Override
   public String[] getInputs() { return Neg400To400BadToGood00EveryNumberOrd.INPUTS; }

   public static final String[] ONE_TO_400_PIECES =
         new String[]{Neg400To400BadToGood00EveryNumberOrd.ONE_TO_50, Neg400To400BadToGood00EveryNumberOrd.FIFTY1_TO_100,
                      Neg400To400BadToGood00EveryNumberOrd.ONE01_TO_150,
                      Neg400To400BadToGood00EveryNumberOrd.ONE51_TO_200,
                      Neg400To400BadToGood00EveryNumberOrd.TWO01_TO_250,
                      Neg400To400BadToGood00EveryNumberOrd.TWO51_TO_300,
                      Neg400To400BadToGood00EveryNumberOrd.THREE01_TO_350,
                      Neg400To400BadToGood00EveryNumberOrd.THREE51_TO_400};
   public static final String   ONE_TO_400        = Arrays.stream(ONE_TO_400_PIECES).
         collect(joining("|"));

   @Override
   public String getRegex() {
      return "\\b(?:" + ONE_OR_MORE_ZEROS_NOT_PRECEDED_BY_DASH + "|-?(?:" + ONE_TO_400 + "))\\b";
   }
}
