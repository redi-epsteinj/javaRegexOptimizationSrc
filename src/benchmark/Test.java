package benchmark;

import regextalk.numericrange.NumericRangeLogic;
import regextalk.numericrange.RangeRegexAnchoredFind;
import regextalk.numericrange.RangeRegexUnanchoredFind;
import regextalk.numericrange.RangeRegexUnanchoredMatches;
import regextalk.numericrange.intro.Neg12To12BadBoundedNonCaptured;
import regextalk.numericrange.intro.Neg12To12BadEveryNumberOrd;
import regextalk.numericrange.intro.Neg12To12Good0t9First;
import regextalk.numericrange.intro.Neg12To12Good10to12First;
import regextalk.numericrange.intro.Neg400To400BadToGood01NonCapturingWithBounds;
import regextalk.numericrange.intro.Neg400To400BadToGood02Dash;
import regextalk.numericrange.intro.Neg400To400BadToGood03ZeroToNine;
import regextalk.numericrange.intro.Neg400To400BadToGood04Tens;
import regextalk.numericrange.intro.Neg400To400BadToGood05Hundreds;
import regextalk.numericrange.intro.Neg400To400BadToGood06Consolidated;
import regextalk.numericrange.intro.Neg400To400BadToGood07EasiestOr1st;
import regextalk.numericrange.intro.Neg400To400EvenBetter;
import regextalk.numericrange.intro.Neg400To400EveryNumberOrd;
import regextalk.password.PasswordLogicThreeRules;
import regextalk.password.PasswordLogicTwoRules;
import regextalk.password.PasswordRegexThreeRules;
import regextalk.password.PasswordRegexTwoRules;
import regextalk.replaceall.PatternDotReplaceAll;
import regextalk.replaceall.ReusedMatcherReplaceAll;
import regextalk.replaceall.ReusedPatternReplaceAll;
import regextalk.replaceall.StringDotReplaceAll;
import regextalk.split.AbstractSplitToBenchmark;
import regextalk.split.PatternDotSplit;
import regextalk.split.PatternSplitAsStream;
import regextalk.split.ReusedPatternSplit;
import regextalk.split.StringDotSplit;

public class Test {

   public static void main(String[] cmd_lineParams) {

      runSuitePasswordTwoRules();
      runSuitePasswordThreeRules();
      //if (true) { return; }
      runSuiteSplitSpace();
      runSuiteSplit2PlusChars();
      runSuiteReplaceAll();
      runSuiteNeg12To12();
      runSuiteRange();
      runSuiteNeg400To400();
   }

   private static void runSuitePasswordThreeRules() {
      StringBuilder builder = new StringBuilder();
      Benchmarker.runTestSequenceAppendResults(builder, null, 100_000, 2000,
                                               new PasswordRegexThreeRules(),
                                               new PasswordLogicThreeRules());
      System.out.println(builder.toString());
      System.out.println();
   }

   private static void runSuitePasswordTwoRules() {
      StringBuilder builder = new StringBuilder();
      Benchmarker.runTestSequenceAppendResults(builder, null, 100_000, 2000,
                                               new PasswordRegexTwoRules(),
                                               new PasswordLogicTwoRules());
      System.out.println(builder.toString());
      System.out.println();
   }

   private static void runSuiteRange() {
      StringBuilder builder = new StringBuilder();
      Benchmarker.runTestSequenceAppendResults(builder, null, 100_000, 2000,
                                               new RangeRegexUnanchoredFind(),
                                               new RangeRegexAnchoredFind(),
                                               new RangeRegexUnanchoredMatches(),
                                               new NumericRangeLogic());
      System.out.println(builder.toString());
      System.out.println();
   }

   private static void runSuiteSplit2PlusChars() {
      String description = "Regex: \"" + AbstractSplitToBenchmark.REGEX_2_PLUS + "\"";
      StringBuilder builder = new StringBuilder();
      Benchmarker.runTestSequenceAppendResults(builder, description, 100_000, 2000,
                                               new StringDotSplit(
                                                     AbstractSplitToBenchmark.REGEX_2_PLUS),
                                               new PatternDotSplit(
                                                     AbstractSplitToBenchmark.REGEX_2_PLUS),
                                               new ReusedPatternSplit(
                                                     AbstractSplitToBenchmark.REGEX_2_PLUS),
                                               new PatternSplitAsStream(
                                                     AbstractSplitToBenchmark.REGEX_2_PLUS));
      System.out.println(builder.toString());
      System.out.println();
   }

   private static void runSuiteSplitSpace() {
      String description = "Regex: \"" + AbstractSplitToBenchmark.REGEX_SPACE + "\"";
      StringBuilder builder = new StringBuilder();
      Benchmarker.runTestSequenceAppendResults(builder, description, 100_000, 2000,
                                               new StringDotSplit(
                                                     AbstractSplitToBenchmark.REGEX_SPACE),
                                               new PatternDotSplit(
                                                     AbstractSplitToBenchmark.REGEX_SPACE),
                                               new ReusedPatternSplit(
                                                     AbstractSplitToBenchmark.REGEX_SPACE),
                                               new PatternSplitAsStream(
                                                     AbstractSplitToBenchmark.REGEX_SPACE));
      System.out.println(builder.toString());
      System.out.println();
   }

   private static void runSuiteReplaceAll() {
      StringBuilder builder = new StringBuilder();
      Benchmarker.runTestSequenceAppendResults(builder, null, 100_000, 2000,
                                               new StringDotReplaceAll(),
                                               new PatternDotReplaceAll(),
                                               new ReusedPatternReplaceAll(),
                                               new ReusedMatcherReplaceAll());
      System.out.println(builder.toString());
      System.out.println();
   }

   private static void runSuiteNeg12To12() {
      StringBuilder builder = new StringBuilder();
      Benchmarker.runTestSequenceAppendResults(builder, null, 100_000, 2000,
                                               new Neg12To12BadEveryNumberOrd(),
                                               new Neg12To12BadBoundedNonCaptured(),
                                               new Neg12To12Good0t9First(),
                                               new Neg12To12Good10to12First());
      System.out.println(builder.toString());
      System.out.println();
   }

   public static void runSuiteNeg400To400() {
      StringBuilder builder = new StringBuilder();
      Benchmarker.runTestSequenceAppendResults(builder, null, 100_000, 2000,
                                               new Neg400To400EveryNumberOrd(),
                                               new Neg400To400BadToGood01NonCapturingWithBounds(),
                                               new Neg400To400BadToGood02Dash(),
                                               new Neg400To400BadToGood03ZeroToNine(),
                                               new Neg400To400BadToGood04Tens(),
                                               new Neg400To400BadToGood05Hundreds(),
                                               new Neg400To400BadToGood06Consolidated(),
                                               new Neg400To400BadToGood07EasiestOr1st(),
                                               new Neg400To400EvenBetter());
      System.out.println(builder.toString());
      System.out.println();
   }
}
