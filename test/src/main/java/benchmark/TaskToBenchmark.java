package benchmark;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.TearDown;

/**
 * A task that may be repeatedly timed by {@code Benchmarker}, whose result may
 * be compared to another timed-task.
 */
public interface TaskToBenchmark {
   default void setupRunBreakdown() {
      setup();
      runCodeToBeTimed();
      breakdown();
   }

   /**
    * One-time only actions that occur before the first call to {@code
    * runCodeToBeTimed()}. By default this does nothing.
    */
   @Setup
   default void setup() {
      //Does nothing
   }

   /**
    * The code to be executed multiple times.
    */
   @Benchmark
   void runCodeToBeTimed();

   /**
    * One-time only actions that occur after the last call to {@code
    * runCodeToBeTimed()}. By default this does nothing.
    */
   @TearDown
   default void breakdown() {
      //Does nothing
   }
}
