package benchmark;

import regextalk.numericrange.FourHundred01EveryNumberInOrToBenchmark;
import regextalk.numericrange.FourHundred02BoundedOnlyToBenchmark;
import regextalk.numericrange.FourHundred03NonCapturingOnlyToBenchmark;
import regextalk.numericrange.FourHundred04BoundedAndNonCapturingToBenchmark;
import regextalk.numericrange.FourHundred05OptionalDashToBenchmark;
import regextalk.numericrange.FourHundred06DigitOnesDigitDToBenchmark;
import regextalk.numericrange.FourHundred07OnesDigit0to9ToBenchmark;
import regextalk.numericrange.FourHundred08TensDigitTo0to9ToBenchmark;
import regextalk.numericrange.FourHundred09HundredsDigitTo0to9ToBenchmark;
import regextalk.numericrange.FourHundred10FurtherConsolidationToBenchmark;
import regextalk.numericrange.FourHundred11BestToBenchmark;
import regextalk.numericrange.FourHundred12LogicToBenchmark;
import regextalk.numericrange.twelve.Neg12To12BadBoundedNonCaptured;
import regextalk.numericrange.twelve.Neg12To12BadEveryNumberOrd;
import regextalk.numericrange.twelve.Neg12To12Good0t9First;
import regextalk.numericrange.twelve.Neg12To12Good10to12First;
import regextalk.numericrange.twogrand.NumericRangeLogic;
import regextalk.numericrange.twogrand.RangeRegexAnchoredFind;
import regextalk.numericrange.twogrand.RangeRegexUnanchoredFind;
import regextalk.numericrange.twogrand.RangeRegexUnanchoredMatches;
import regextalk.password.Password01RegexThreeRulesToBenchmark;
import regextalk.password.Password02RegexTwoRulesToBenchmark;
import regextalk.password.Password04LogicTwoRulesToBenchmark;
import regextalk.password.Password03LogicThreeRulesToBenchmark;
import regextalk.replaceall.PatternDotReplaceAll;
import regextalk.replaceall.ReusedMatcherReplaceAll;
import regextalk.replaceall.ReusedPatternReplaceAll;
import regextalk.replaceall.StringDotReplaceAll;
import regextalk.split.AbstractSplit;
import regextalk.split.Split02PatternDotToBenchmark;
import regextalk.split.Split04PatternAsStreamToBenchmark;
import regextalk.split.Split03ReusedPatternToBenchmark;
import regextalk.split.Split01StringDotToBenchmark;

public class Test {

   private static final int MILLS_BETWEEN_SUITES = 8000;
   private static int TASK_ITERS = 100_000;
   private static int SUITE_ITERS = 25;

   public static void main(String[] cmd_lineParams) {

      runSuiteNeg12To12();
      runSuiteRange();
      runSuiteNeg400To400();
      if (true) {
         return;
      }
      runSuitePasswordTwoRules();
      runSuitePasswordThreeRules();
      runSuiteSplitSpace();
      runSuiteSplit2PlusChars();
      runSuiteReplaceAll();
   }

   private static void runSuitePasswordThreeRules() {
      SuiteToBenchmark suite = new SuiteToBenchmark.Builder().taskIterations(TASK_ITERS)
            .suiteIterations(SUITE_ITERS).build(new Password01RegexThreeRulesToBenchmark(),
                                                new Password03LogicThreeRulesToBenchmark());

      StringBuilder buffer = new StringBuilder();
      Benchmarker.runTestSuiteAppendResults(buffer, TimedTestConsoleOutput.SUPPRESS, suite);

      System.out.println(buffer.toString());
      System.out.println();
   }

   private static void runSuitePasswordTwoRules() {
      SuiteToBenchmark suite = new SuiteToBenchmark.Builder().taskIterations(TASK_ITERS)
            .millsBetweenSuites(MILLS_BETWEEN_SUITES).suiteIterations(SUITE_ITERS)
            .build(new Password02RegexTwoRulesToBenchmark(),
                   new Password04LogicTwoRulesToBenchmark());

      StringBuilder buffer = new StringBuilder();
      Benchmarker.runTestSuiteAppendResults(buffer, TimedTestConsoleOutput.SUPPRESS, suite);

      System.out.println(buffer.toString());
      System.out.println();
   }

   private static void runSuiteRange() {
      SuiteToBenchmark suite = new SuiteToBenchmark.Builder().taskIterations(TASK_ITERS)
            .suiteIterations(SUITE_ITERS).millsBetweenSuites(MILLS_BETWEEN_SUITES).
                  build(new RangeRegexUnanchoredFind(),
                        new RangeRegexAnchoredFind(),
                        new RangeRegexUnanchoredMatches(),
                        new NumericRangeLogic());

      StringBuilder buffer = new StringBuilder();
      Benchmarker.runTestSuiteAppendResults(buffer, TimedTestConsoleOutput.SUPPRESS, suite);

      System.out.println(buffer.toString());
      System.out.println();
   }

   private static void runSuiteSplit2PlusChars() {
      final String regex = AbstractSplit.REGEX_2_PLUS;
      SuiteToBenchmark suite = new SuiteToBenchmark.Builder().
            description("Regex: \"" + regex + "\"").
            taskIterations(TASK_ITERS).suiteIterations(SUITE_ITERS).
            millsBetweenSuites(MILLS_BETWEEN_SUITES).
            build(new Split02PatternDotToBenchmark(regex),
                  new Split01StringDotToBenchmark(regex),
                  new Split03ReusedPatternToBenchmark(regex),
                  new Split04PatternAsStreamToBenchmark(regex));

      StringBuilder buffer = new StringBuilder();
      Benchmarker.runTestSuiteAppendResults(buffer, TimedTestConsoleOutput.SUPPRESS, suite);

      System.out.println(buffer.toString());
      System.out.println();
   }

   private static void runSuiteSplitSpace() {
      final String regex = AbstractSplit.REGEX_SPACE;
      
      SuiteToBenchmark suite = new SuiteToBenchmark.Builder().
            description("Regex: \"" + regex + "\"").
            taskIterations(TASK_ITERS).suiteIterations(SUITE_ITERS).
            millsBetweenSuites(MILLS_BETWEEN_SUITES).
            build(new Split02PatternDotToBenchmark(regex),
                  new Split01StringDotToBenchmark(regex),
                  new Split03ReusedPatternToBenchmark(regex),
                  new Split04PatternAsStreamToBenchmark(regex));

      StringBuilder buffer = new StringBuilder();
      Benchmarker.runTestSuiteAppendResults(buffer, TimedTestConsoleOutput.SUPPRESS, suite);

      System.out.println(buffer.toString());
      System.out.println();
   }

   private static void runSuiteReplaceAll() {
      SuiteToBenchmark suite = new SuiteToBenchmark.Builder().
            taskIterations(TASK_ITERS).suiteIterations(SUITE_ITERS).
            millsBetweenSuites(MILLS_BETWEEN_SUITES).
            build(new StringDotReplaceAll(),
                  new PatternDotReplaceAll(),
                  new ReusedPatternReplaceAll(),
                  new ReusedMatcherReplaceAll());

      StringBuilder buffer = new StringBuilder();
      Benchmarker.runTestSuiteAppendResults(buffer, TimedTestConsoleOutput.SUPPRESS, suite);

      System.out.println(buffer.toString());
      System.out.println();
   }

   private static void runSuiteNeg12To12() {
      SuiteToBenchmark suite = new SuiteToBenchmark.Builder().
            taskIterations(TASK_ITERS).suiteIterations(SUITE_ITERS).
            millsBetweenSuites(MILLS_BETWEEN_SUITES).
            build(new Neg12To12BadEveryNumberOrd(),
                  new Neg12To12BadBoundedNonCaptured(),
                  new Neg12To12Good0t9First(),
                  new Neg12To12Good10to12First());

      StringBuilder buffer = new StringBuilder();
      Benchmarker.runTestSuiteAppendResults(buffer, TimedTestConsoleOutput.SUPPRESS, suite);

      System.out.println(buffer.toString());
      System.out.println();
   }

   public static void runSuiteNeg400To400() {
      SuiteToBenchmark suite = new SuiteToBenchmark.Builder().
            taskIterations(TASK_ITERS).suiteIterations(SUITE_ITERS).
            millsBetweenSuites(MILLS_BETWEEN_SUITES).
            build(new FourHundred01EveryNumberInOrToBenchmark(),
                  new FourHundred02BoundedOnlyToBenchmark(),
                  new FourHundred03NonCapturingOnlyToBenchmark(),
                  new FourHundred04BoundedAndNonCapturingToBenchmark(),
                  new FourHundred05OptionalDashToBenchmark(),
                  new FourHundred06DigitOnesDigitDToBenchmark(),
                  new FourHundred07OnesDigit0to9ToBenchmark(),
                  new FourHundred08TensDigitTo0to9ToBenchmark(),
                  new FourHundred09HundredsDigitTo0to9ToBenchmark(),
                  new FourHundred10FurtherConsolidationToBenchmark(),
                  new FourHundred11BestToBenchmark(),
                  new FourHundred12LogicToBenchmark());

      StringBuilder buffer = new StringBuilder();
      Benchmarker.runTestSuiteAppendResults(buffer, TimedTestConsoleOutput.SUPPRESS, suite);

      System.out.println(buffer.toString());
      System.out.println();
   }
}
