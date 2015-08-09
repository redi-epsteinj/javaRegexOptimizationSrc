package benchmark;

import regextalk.numericrange.NumericRangeLogic;
import regextalk.numericrange.RangeRegexAnchoredFind;
import regextalk.numericrange.RangeRegexUnanchoredFind;
import regextalk.numericrange.RangeRegexUnanchoredMatches;
import regextalk.numericrange.intro.Neg12To12Bad;
import regextalk.numericrange.intro.Neg12To12Good0t9First;
import regextalk.numericrange.intro.Neg12To12Good10to12First;
import regextalk.numericrange.intro.temp.Neg400To400Bad;
import regextalk.numericrange.intro.temp.Neg400To400Good400First;
import regextalk.numericrange.intro.temp.Neg400To400Good400Last;
import regextalk.password.PasswordLogicThreeRules;
import regextalk.password.PasswordLogicTwoRules;
import regextalk.password.PasswordRegexThreeRules;
import regextalk.password.PasswordRegexTwoRules;
import regextalk.replaceall.PatternDotReplaceAll;
import regextalk.replaceall.ReusedMatcherReplaceAll;
import regextalk.replaceall.ReusedPatternReplaceAll;
import regextalk.replaceall.StringDotReplaceAll;
import regextalk.split.PatternDotSplit;
import regextalk.split.PatternSplitAsStream;
import regextalk.split.ReusedPatternSplit;
import regextalk.split.StringDotSplit;

public class Test {

   public static void main(String[] cmd_lineParams) {
      StringBuilder builder = new StringBuilder();
      Benchmarker.runTestSequenceAppendResults(builder, 100_000, new StringDotSplit(),
                                               new PatternDotSplit(),
                                               new ReusedPatternSplit(),
                                               new PatternSplitAsStream());
      System.out.println(builder.toString());
      builder.setLength(0);
      System.out.println();

      Benchmarker.runTestSequenceAppendResults(builder, 100_000, new StringDotReplaceAll(),
                                               new PatternDotReplaceAll(),
                                               new ReusedPatternReplaceAll(),
                                               new ReusedMatcherReplaceAll());
      System.out.println(builder.toString());
      builder.setLength(0);
      System.out.println();

      Benchmarker.runTestSequenceAppendResults(builder, 100_000, new Neg12To12Bad(),
                                               new Neg12To12Good0t9First(),
                                               new Neg12To12Good10to12First());
      System.out.println(builder.toString());
      builder.setLength(0);
      System.out.println();

      Benchmarker.runTestSequenceAppendResults(builder, 100_000, new Neg400To400Bad(),
                                               new Neg400To400Good400Last(),
                                               new Neg400To400Good400First());
      System.out.println(builder.toString());
      builder.setLength(0);
      System.out.println();


      Benchmarker.runTestSequenceAppendResults(builder, 100_000, new RangeRegexUnanchoredFind(),
                                               new RangeRegexAnchoredFind(),
                                               new RangeRegexUnanchoredMatches(),
                                               new NumericRangeLogic());
      System.out.println(builder.toString());
      builder.setLength(0);
      System.out.println();


      Benchmarker.runTestSequenceAppendResults(builder, 100_000, new PasswordRegexTwoRules(),
                                               new PasswordLogicTwoRules());
      System.out.println(builder.toString());
      builder.setLength(0);
      System.out.println();

      Benchmarker.runTestSequenceAppendResults(builder, 100_000, new PasswordRegexThreeRules(),
                                               new PasswordLogicThreeRules());
      System.out.println(builder.toString());
      builder.setLength(0);
      System.out.println();
   }
}
