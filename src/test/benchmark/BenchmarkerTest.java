package test.benchmark;

import static org.junit.Assert.assertEquals;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import benchmark.BenchmarkBreakdownFailedException;
import benchmark.BenchmarkProperFailedException;
import benchmark.BenchmarkResults;
import benchmark.BenchmarkSetupFailedException;
import benchmark.Benchmarker;
import benchmark.ToBenchmark;

public class BenchmarkerTest {
   @Rule
   public ExpectedException thrown = ExpectedException.none();

   @Test
   public void testConstructor_validIterationCount_returnedByGetter() {
      Benchmarker benchmarker = new Benchmarker(1);
      assertEquals(1, benchmarker.getIterations());
   }

   @Test
   public void testConstructor_validTest_returnsResult() {
      Benchmarker benchmarker = new Benchmarker(1);
      ToBenchmark validTest = new ValidTest();
      BenchmarkResults results = benchmarker.executeGetDuration(validTest);
      assertEquals(validTest, results.getToBenchmark());
   }

   @Test
   public void testConstructor_validTest_returnsResultWithSameIterCount() {
      Benchmarker benchmarker = new Benchmarker(1);
      ToBenchmark validTest = new ValidTest();
      BenchmarkResults results = benchmarker.executeGetDuration(validTest);
      assertEquals(1, results.getIterations());
   }

   @Test
   public void testConstructor_itersLT1_throwsIAX() {
      thrown.expect(IllegalArgumentException.class);
      new Benchmarker(-1);
   }

   @Test
   public void testExecuteGetDuration_nullToBenchmark_throwsNPX() throws Exception {
      thrown.expect(NullPointerException.class);
      new Benchmarker(1).executeGetDuration(null);
   }

   @Test
   public void testExecuteGetDuration_badSetup_throwsBSFX() {
      thrown.expect(BenchmarkSetupFailedException.class);
      new Benchmarker(1).executeGetDuration(new SetupThrowsException());

   }

   @Test
   public void testExecuteGetDuration_badBreakdown_throwsBBFX() {
      thrown.expect(BenchmarkBreakdownFailedException.class);
      new Benchmarker(1).executeGetDuration(new BreakdownThrowsException());

   }

   @Test
   public void testExecuteGetDuration_badProper_throwsBPFX() {
      thrown.expect(BenchmarkProperFailedException.class);
      new Benchmarker(1).executeGetDuration(new ProperThrowsException());
   }
}

class SetupThrowsException implements ToBenchmark {
   @Override
   public void setup() {
      throw new NullPointerException("setup message");
   }
   @Override
   public void runCodeToBeTimed() {
      int i = 1;
   }
}
class BreakdownThrowsException implements ToBenchmark {
   @Override
   public void breakdown() {
      throw new NullPointerException("breakdown message");
   }
   @Override
   public void runCodeToBeTimed() {
      int i = 1;
   }
}
class ProperThrowsException implements ToBenchmark {
   @Override
   public void runCodeToBeTimed() {
      throw new NullPointerException("proper message");
   }
}
