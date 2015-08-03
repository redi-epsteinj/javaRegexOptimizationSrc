package test.benchmark;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import benchmark.BenchmarkImprovement;
import benchmark.BenchmarkResults;

public class BenchmarkImprovementTest {
   @Rule
   public ExpectedException thrown = ExpectedException.none();

   private static final BenchmarkResults FIRST_SLOW = new ResultWithNanos(10L);
   private static final BenchmarkResults SECOND_MEDIUM = new ResultWithNanos(5L);
   private static final BenchmarkResults THIRD_FAST = new ResultWithNanos(1L);

   public BenchmarkImprovement getDefaultResults() {
      return new BenchmarkImprovement(FIRST_SLOW, SECOND_MEDIUM, THIRD_FAST);
   }
   @Test
   public void testConstructor_threeParam_localeIsDefault() throws Exception {
      assertEquals(BenchmarkImprovement.DEFAULT_NUMBER_FORMAT, getDefaultResults().getNumberFormat());
   }

   @Test
   public void testConstructor_first_returnedByGetter() throws Exception {
      assertEquals(FIRST_SLOW, getDefaultResults().getFirst());
   }

   @Test
   public void testConstructor_previos_returnedByGetter() throws Exception {
      assertEquals(SECOND_MEDIUM, getDefaultResults().getPrevious());
   }

   @Test
   public void testConstructor_current_returnedByGetter() throws Exception {
      assertEquals(THIRD_FAST, getDefaultResults().getCurrent());
   }

   @Test
   public void testConstructor_firstNull_throwsNPX() {
      thrown.expect(NullPointerException.class);
      new BenchmarkImprovement(null, SECOND_MEDIUM, THIRD_FAST);
   }

   @Test
   public void testConstructor_previousNull_throwsNPX() {
      thrown.expect(NullPointerException.class);
      new BenchmarkImprovement(FIRST_SLOW, null, THIRD_FAST);
   }

   @Test
   public void testConstructor_currentNull_throwsNPX() {
      thrown.expect(NullPointerException.class);
      new BenchmarkImprovement(FIRST_SLOW, SECOND_MEDIUM, null);
   }

   @Test
   public void testConstructor_numberFormatNull_throwsNPX() {
      thrown.expect(NullPointerException.class);
      new BenchmarkImprovement(FIRST_SLOW, SECOND_MEDIUM, THIRD_FAST, null);
   }

   @Test
   public void testConstructor_firstAndPrevDiffIterCounts_throwsIAX() {
      thrown.expect(IllegalArgumentException.class);
      new BenchmarkImprovement(FIRST_SLOW,
                               new ResultWithNanos(FIRST_SLOW.getIterations() + 1, 10L),
                               THIRD_FAST);
   }

   @Test
   public void testConstructor_firstAndCurrDiffIterCounts_throwsIAX() {
      thrown.expect(IllegalArgumentException.class);
      new BenchmarkImprovement(FIRST_SLOW, SECOND_MEDIUM,
                               new ResultWithNanos(SECOND_MEDIUM.getIterations() + 1, 10L));
   }

   @Test
   public void testConstructor_prevAndCurrSameObj_throwsIAX() {
      thrown.expect(IllegalArgumentException.class);
      new BenchmarkImprovement(FIRST_SLOW, SECOND_MEDIUM, SECOND_MEDIUM);
   }

   @Test
   public void testConstructor_prevAndCurrSameObj_isCurrentSecondTaskTrue() {
      BenchmarkImprovement improvement = new BenchmarkImprovement(FIRST_SLOW, FIRST_SLOW, THIRD_FAST);
      assertTrue(improvement.isCurrentSecondTask());
   }

   @Test
   public void testGetNumberFormat() throws Exception {

   }

   @Test
   public void testGetImprovementPercentageOverPrevious() throws Exception {

   }

   @Test
   public void testGetImprovementPercentageOverFirst() throws Exception {

   }

   @Test
   public void testGetComparisonOutput() throws Exception {

   }

   @Test
   public void testGetCurrentNanosForItersOutput() throws Exception {

   }
}
