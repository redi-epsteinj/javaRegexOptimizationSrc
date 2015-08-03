package test.benchmark;

import static org.junit.Assert.assertEquals;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import benchmark.BenchmarkResults;
import benchmark.ToBenchmark;

public class BenchmarkResultsTest {
   @Rule
   public ExpectedException thrown = ExpectedException.none();

   @Test
   public void testConstructor_validInputs_toBenchmarkReturnedByGetter() {
      ToBenchmark tb = new DoNothingToBenchmark();
      BenchmarkResults results = new BenchmarkResults(tb, 1, 2L);
      assertEquals(tb, results.getToBenchmark());
   }

   @Test
   public void testConstructor_validInputs_itersReturnedByGetter() {
      BenchmarkResults results = new BenchmarkResults(new DoNothingToBenchmark(), 1, 2L);
      assertEquals(1, results.getIterations());
   }

   @Test
   public void testConstructor_validInputs_nanosReturnedByGetter() {
      BenchmarkResults results = new BenchmarkResults(new DoNothingToBenchmark(), 1, 2L);
      assertEquals(2L, results.getTotalNanos());
   }

   @Test
   public void testConstructor_nullToBenchmark_throwsNPX() {
      thrown.expect(NullPointerException.class);
      BenchmarkResults results = new BenchmarkResults(null, 1, 2L);
   }

   @Test
   public void testConstructor_iterationsLTOne_throwsIAX() {
      thrown.expect(IllegalArgumentException.class);
      BenchmarkResults results = new BenchmarkResults(new DoNothingToBenchmark(), -1, 2L);
   }

   @Test
   public void testConstructor_nanosLTOne_throwsIAX() {
      thrown.expect(IllegalArgumentException.class);
      BenchmarkResults results = new BenchmarkResults(new DoNothingToBenchmark(), 1, -1L);
   }
}
class DoNothingToBenchmark implements ToBenchmark {

   @Override
   public void runCodeToBeTimed() {

   }
}