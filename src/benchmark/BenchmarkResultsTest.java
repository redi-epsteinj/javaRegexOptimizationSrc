package benchmark;

import static org.junit.Assert.assertEquals;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class BenchmarkResultsTest {
   @Rule
   public ExpectedException thrown = ExpectedException.none();

   @Test
   public void testConstructor_validInputs_nameReturnedByGetter() {
      BenchmarkResults results = new BenchmarkResults("name", 1, 2L);
      assertEquals("name", results.getTaskName());
   }

   @Test
   public void testConstructor_validInputs_itersReturnedByGetter() {
      BenchmarkResults results = new BenchmarkResults("name", 1, 2L);
      assertEquals(1, results.getIterations());
   }

   @Test
   public void testConstructor_validInputs_nanosReturnedByGetter() {
      BenchmarkResults results = new BenchmarkResults("name", 1, 2L);
      assertEquals(2L, results.getTotalNanos());
   }

   @Test
   public void testConstructor_nullName_throwsNPX() {
      thrown.expect(NullPointerException.class);
      BenchmarkResults results = new BenchmarkResults(null, 1, 2L);
   }

   @Test
   public void testConstructor_emptyName_throwsIAX() {
      thrown.expect(IllegalArgumentException.class);
      BenchmarkResults results = new BenchmarkResults("", 1, 2L);
   }

   @Test
   public void testConstructor_iterationsLTOne_throwsIAX() {
      thrown.expect(IllegalArgumentException.class);
      BenchmarkResults results = new BenchmarkResults("name", -1, 2L);
   }

   @Test
   public void testConstructor_nanosLTOne_throwsIAX() {
      thrown.expect(IllegalArgumentException.class);
      BenchmarkResults results = new BenchmarkResults("name", 1, -1L);
   }
}