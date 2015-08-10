package regextalk.numericrange.intro;

import static java.util.stream.Collectors.joining;

import java.util.Arrays;

import regextalk.AbstractReusedMatcherToBenchmark;
import regextalk.MatchOrFind;

public class Neg400To400BadToGood02Dash extends AbstractReusedMatcherToBenchmark {

   public Neg400To400BadToGood02Dash() {
      super(MatchOrFind.FIND);
   }


   public static final String ONE_OR_MORE_ZEROS_NOT_PRECEDED_BY_DASH = "(?<!-)0+";

   public static void main(String[] ignored) {
      new Neg400To400BadToGood02Dash().setupRunBreakdown();
   }

   @Override
   public String[] getInputs() { return Neg400To400EveryNumberOrd.INPUTS; }

   public static final String[] ONE_TO_400_PIECES =
         new String[]{Neg400To400EveryNumberOrd.ONE_TO_50, Neg400To400EveryNumberOrd.FIFTY1_TO_100,
                      Neg400To400EveryNumberOrd.ONE01_TO_150,
                      Neg400To400EveryNumberOrd.ONE51_TO_200,
                      Neg400To400EveryNumberOrd.TWO01_TO_250,
                      Neg400To400EveryNumberOrd.TWO51_TO_300,
                      Neg400To400EveryNumberOrd.THREE01_TO_350,
                      Neg400To400EveryNumberOrd.THREE51_TO_400};
   public static final String   ONE_TO_400        = Arrays.stream(ONE_TO_400_PIECES).
         collect(joining("|"));

   @Override
   public String getRegex() {
      return "\\b(?:" + ONE_OR_MORE_ZEROS_NOT_PRECEDED_BY_DASH + "|-?(?:" + ONE_TO_400 + "))\\b";
   }
}
