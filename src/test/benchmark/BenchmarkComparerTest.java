package test.benchmark;

import static org.junit.Assert.assertEquals;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import benchmark.BenchmarkComparer;
import benchmark.BenchmarkResult;

public class BenchmarkComparerTest {

   //http://stackoverflow.com/a/8385364/2736496
   private static final double DELTA = 1e-15;

   @Rule
   public ExpectedException thrown = ExpectedException.none();

   private static final BenchmarkResult NANOS_10 = new ResultWithNanos(10L);
   private static final BenchmarkResult NANOS_5 = new ResultWithNanos(5L);
   private static final BenchmarkResult NANOS_1 = new ResultWithNanos(1L);

   public BenchmarkComparer new10Prev5CurrComparer() {
      return new BenchmarkComparer.Builder().build(NANOS_10, NANOS_5);
   }

   @Test
   public void constructor_threeParam_localeIsDefault() throws Exception {
      assertEquals(BenchmarkComparer.DEFAULT_PERCENTAGE_FORMAT,
                   new10Prev5CurrComparer().getPercentageFormat());
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
      new BenchmarkComparer.Builder().build(null, NANOS_1);
   }

   @Test
   public void constructor_currentNull_throwsNPX() {
      thrown.expect(NullPointerException.class);
      new BenchmarkComparer.Builder().build(NANOS_5, null);
   }

   @Test
   public void constructor_nanosFormatNull_throwsNPX() {
      thrown.expect(NullPointerException.class);
      new BenchmarkComparer.Builder().nanosFormat(null).build(NANOS_5, NANOS_1);
   }

   @Test
   public void constructor_percentageFormatNull_throwsNPX() {
      thrown.expect(NullPointerException.class);
      new BenchmarkComparer.Builder().percentageFormat(null).build(NANOS_5, NANOS_1);
   }

   @Test
   public void constructor_firstAndPrevDiffIterCounts_throwsIAX() {
      thrown.expect(IllegalArgumentException.class);
      new BenchmarkComparer.Builder().build(
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
   public void getTaskTookNanosOutput_10prev3curr_returns70() {
      BenchmarkComparer comparer = new BenchmarkComparer.Builder().build(new ResultWithNanos(10L),
                                                                         new ResultWithNanos(3L));
      assertEquals(70.0d,
                   comparer.getPercentageSpeedOfPrevious(),
                   DELTA);
   }

   @Test
   public void getPreviousTaskTookNanosOutput_containsNameAndNanos() {
      String output = new BenchmarkComparer.Builder().build(new ResultWithNanos(3L),
                                                            new ResultWithNanos(1003338347384738L)).
            getPreviousTaskTookNanosOutput();
      assertEquals(ValidTest.class.getName() + ": 3", output);
   }

   @Test
   public void getCurrentTaskTookNanosOutput_containsNameAndNanos() {
      String output = new BenchmarkComparer.Builder().build(new ResultWithNanos(3L),
                                                            new ResultWithNanos(1003338347384738L)).
            getCurrentTaskTookNanosOutput();
      assertEquals(ValidTest.class.getName() + ": 1,003,338,347,384,738", output);
   }
}
