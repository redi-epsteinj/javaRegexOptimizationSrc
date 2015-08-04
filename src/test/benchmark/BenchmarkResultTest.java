package test.benchmark;

import static org.junit.Assert.assertEquals;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import benchmark.BenchmarkResult;
import benchmark.TaskToBenchmark;

public class BenchmarkResultTest {

   @Rule
   public ExpectedException thrown = ExpectedException.none();

   @Test
   public void constructor_validInputs_toBenchmarkReturnedByGetter() {
      TaskToBenchmark tb = new DoNothingToBenchmark();
      BenchmarkResult results = new BenchmarkResult(tb, 1, 2L);
      assertEquals(tb, results.getTask());
   }

   @Test
   public void constructor_validInputs_itersReturnedByGetter() {
      BenchmarkResult
            results =
            new BenchmarkResult(new DoNothingToBenchmark(), 1, 2L);
      assertEquals(1, results.getIterations());
   }

   @Test
   public void constructor_validInputs_nanosReturnedByGetter() {
      BenchmarkResult
            results =
            new BenchmarkResult(new DoNothingToBenchmark(), 1, 2L);
      assertEquals(2L, results.getTotalNanos());
   }

   @Test
   public void constructor_nullToBenchmark_throwsNPX() {
      thrown.expect(NullPointerException.class);
      BenchmarkResult results = new BenchmarkResult(null, 1, 2L);
   }

   @Test
   public void constructor_iterationsLTOne_throwsIAX() {
      thrown.expect(IllegalArgumentException.class);
      BenchmarkResult
            results =
            new BenchmarkResult(new DoNothingToBenchmark(), -1, 2L);
   }

   @Test
   public void constructor_nanosLTOne_throwsIAX() {
      thrown.expect(IllegalArgumentException.class);
      BenchmarkResult
            results =
            new BenchmarkResult(new DoNothingToBenchmark(), 1, -1L);
   }
}

class DoNothingToBenchmark implements TaskToBenchmark {

   @Override
   public void runCodeToBeTimed() {

   }
}