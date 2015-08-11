package benchmark;

import regextalk.numericrange.twogrand.NumericRangeLogic;
import regextalk.numericrange.twogrand.RangeRegexAnchoredFind;
import regextalk.numericrange.twogrand.RangeRegexUnanchoredFind;
import regextalk.numericrange.twogrand.RangeRegexUnanchoredMatches;
import regextalk.numericrange.twelve.Neg12To12BadBoundedNonCaptured;
import regextalk.numericrange.twelve.Neg12To12BadEveryNumberOrd;
import regextalk.numericrange.twelve.Neg12To12Good0t9First;
import regextalk.numericrange.twelve.Neg12To12Good10to12First;
import regextalk.numericrange.FourHundred02BoundedOnlyToBenchmark;
import regextalk.numericrange.FourHundred03NonCapturingOnlyToBenchmark;
import regextalk.numericrange.FourHundred04BoundedAndNonCapturingToBenchmark;
import regextalk.numericrange.FourHundred05OptionalDashToBenchmark;
import regextalk.numericrange.FourHundred06DigitOnesDigitDToBenchmark;
import regextalk.numericrange.FourHundred07OnesDigit0to9ToBenchmark;
import regextalk.numericrange.FourHundred08TensDigitTo0to9ToBenchmark;
import regextalk.numericrange.FourHundred09HundredsDigitTo0to9ToBenchmark;
import regextalk.numericrange.FourHundred10FurtherConsolidationToBenchmark;
import regextalk.numericrange.Neg400To400BadToGood07EasiestOr1st;
import regextalk.numericrange.FourHundred11BestToBenchmark;
import regextalk.numericrange.FourHundred01EveryNumberInOrToBenchmark;
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
      runSuiteSplitSpace();
      runSuiteSplit2PlusChars();
      runSuiteReplaceAll();
      if (true) { return; }
      runSuiteNeg400To400();
      runSuiteNeg12To12();
      runSuiteRange();
   }

   private static void runSuitePasswordThreeRules() {
      StringBuilder builder = new StringBuilder();
      Benchmarker.runTestSuiteAppendResults(builder, null, 100_000, 2000,
                                            new PasswordRegexThreeRules(),
                                            new PasswordLogicThreeRules());
      System.out.println(builder.toString());
      System.out.println();
   }

   private static void runSuitePasswordTwoRules() {
      StringBuilder builder = new StringBuilder();
      Benchmarker.runTestSuiteAppendResults(builder, null, 100_000, 2000,
                                            new PasswordRegexTwoRules(),
                                            new PasswordLogicTwoRules());
      System.out.println(builder.toString());
      System.out.println();
   }

   private static void runSuiteRange() {
      StringBuilder builder = new StringBuilder();
      Benchmarker.runTestSuiteAppendResults(builder, null, 100_000, 2000,
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
      Benchmarker.runTestSuiteAppendResults(builder, description, 100_000, 2000,
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
      Benchmarker.runTestSuiteAppendResults(builder, description, 100_000, 2000,
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
      Benchmarker.runTestSuiteAppendResults(builder, null, 100_000, 2000,
                                            new StringDotReplaceAll(),
                                            new PatternDotReplaceAll(),
                                            new ReusedPatternReplaceAll(),
                                            new ReusedMatcherReplaceAll());
      System.out.println(builder.toString());
      System.out.println();
   }

   private static void runSuiteNeg12To12() {
      StringBuilder builder = new StringBuilder();
      Benchmarker.runTestSuiteAppendResults(builder, null, 100_000, 2000,
                                            new Neg12To12BadEveryNumberOrd(),
                                            new Neg12To12BadBoundedNonCaptured(),
                                            new Neg12To12Good0t9First(),
                                            new Neg12To12Good10to12First());
      System.out.println(builder.toString());
      System.out.println();
   }

   public static void runSuiteNeg400To400() {
      StringBuilder builder = new StringBuilder();
      Benchmarker.runTestSuiteAppendResults(builder, null, 100_000, 2000,
                                            new FourHundred01EveryNumberInOrToBenchmark(),
                                            new FourHundred02BoundedOnlyToBenchmark(),
                                            new FourHundred03NonCapturingOnlyToBenchmark(),
                                            new FourHundred04BoundedAndNonCapturingToBenchmark(),
                                            new FourHundred05OptionalDashToBenchmark(),
                                            new FourHundred06DigitOnesDigitDToBenchmark(),
                                            new FourHundred07OnesDigit0to9ToBenchmark(),
                                            new FourHundred08TensDigitTo0to9ToBenchmark(),
                                            new FourHundred09HundredsDigitTo0to9ToBenchmark(),
                                            new FourHundred10FurtherConsolidationToBenchmark(),
                                            new Neg400To400BadToGood07EasiestOr1st(),
                                            new FourHundred11BestToBenchmark());
      System.out.println(builder.toString());
      System.out.println();
   }
}
