package test.benchmark;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.text.NumberFormat;
import java.util.Locale;

import benchmark.BenchmarkComparer;
import benchmark.BenchmarkResult;

public class BenchmarkComparerTest {

   //http://stackoverflow.com/a/8385364/2736496
   private static final double DELTA = 1e-15;

   @Rule
   public ExpectedException thrown = ExpectedException.none();

   private static final BenchmarkResult NANOS_10 =
         new ResultWithNanos(10L);
   private static final BenchmarkResult
                                        NANOS_5  =
         new ResultWithNanos(5L);
   private static final BenchmarkResult
                                        NANOS_1  =
         new ResultWithNanos(1L);

   public BenchmarkComparer new10Prev5CurrComparer() {
      return new BenchmarkComparer(NANOS_10, NANOS_5);
   }

   @Test
   public void constructor_threeParam_localeIsDefault() throws Exception {
      assertEquals(BenchmarkComparer.DEFAULT_NUMBER_FORMAT,
                   new10Prev5CurrComparer().getNumberFormat());
   }

   @Test
   public void constructor_previous_returnedByGetter() throws Exception {
      assertEquals(NANOS_10, new10Prev5CurrComparer().getPrevious());
   }

   @Test
   public void constructor_current_returnedByGetter() throws Exception {
      assertEquals(NANOS_5, new10Prev5CurrComparer().getCurrent());
   }

   @Test
   public void constructor_previousNull_throwsNPX() {
      thrown.expect(NullPointerException.class);
      new BenchmarkComparer(null, NANOS_1);
   }

   @Test
   public void constructor_currentNull_throwsNPX() {
      thrown.expect(NullPointerException.class);
      new BenchmarkComparer(NANOS_5, null);
   }

   @Test
   public void constructor_numberFormatNull_throwsNPX() {
      thrown.expect(NullPointerException.class);
      new BenchmarkComparer(NANOS_5, NANOS_1, null);
   }

   @Test
   public void constructor_firstAndPrevDiffIterCounts_throwsIAX() {
      thrown.expect(IllegalArgumentException.class);
      new BenchmarkComparer(
            new ResultWithNanos(NANOS_5.getIterations() + 1,
                                10L),
            NANOS_1);
   }

   @Test
   public void getTaskTookNanosOutput_10prev5curr_returns50() {
      assertEquals(50.0d,
                   new10Prev5CurrComparer().getPercentageSpeedOfPrevious(),
                   DELTA);
   }

   @Test
   public void getTaskTookNanosOutput_containsNameAndNanos() {
      String output = BenchmarkComparer.
            getTaskTookNanosOutput("%s: %s", new
                  ResultWithNanos(1003338347384738L), Locale.US, NumberFormat
                                         .getNumberInstance(
                                               Locale.US));
      assertEquals(ValidTest.class.getName() + ": 1,003,338,347,384,738",
                   output);
   }

   @Test
   public void getTaskTookNanosOutput_containsClassName() {
      String output = BenchmarkComparer.getTaskTookNanosOutput(NANOS_10);
      boolean containsClassName = output.indexOf(ValidTest.class.getName())
                                  != -1;
      assertTrue(containsClassName);
   }

   @Test
   public void getTaskTookNanosOutput_containsNanos() {
      String output = BenchmarkComparer.getTaskTookNanosOutput(NANOS_10);
      assertTrue("\" 10\" not in output: <<<" + output + ">>>",
                 output.indexOf(" 10") != -1);
   }

   @Test
   public void zeroParamGetTaskTookNanosOutput_containsNanosOfCurrent() {
      BenchmarkResult current = new ResultWithNanos(1048374L);
      String output = new BenchmarkComparer(NANOS_1, current).
            getCurrentTaskTookNanosOutput();
      String expectedNanos = BenchmarkComparer.DEFAULT_NUMBER_FORMAT.
            format(current.getTotalNanos());
      assertTrue("\" " + expectedNanos +"\" not in output: <<<" +
                 output + ">>>",
                 output.indexOf(" " + expectedNanos) != -1);
   }
}
