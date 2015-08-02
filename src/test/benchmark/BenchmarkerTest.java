package test.benchmark;

import static org.junit.Assert.assertEquals;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

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
   public void testConstructor_itersLT1_throwsIAX() {
      thrown.expect(IllegalArgumentException.class);
      Benchmarker benchmarker = new Benchmarker(1);
   }

   @Test
   public void testExecuteGetDuration_nullToBenchmark_throwsNPX() throws Exception {
      thrown.expect(NullPointerException.class);
      new Benchmarker(1).executeGetDuration("name", null);
   }
}
class DemoToBenchmark implements ToBenchmark {
   @Override
   public void runCodeToBeTimed() {
      int i = 1;
   }
}