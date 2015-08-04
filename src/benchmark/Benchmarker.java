package benchmark;

import java.util.Objects;

/**
 * Determines how long it takes to execute a single task, multiple times.
 *
 * @see <CODE><A HREF="http://stackoverflow.com/questions/180158/how-do-i-time-a-methods-execution-in-java">http://stackoverflow.com/questions/180158/how-do-i-time-a-methods-execution-in-java</A></CODE>
 **/
public class Benchmarker {

   private final int iterations;

   /**
    * Create an instance with the execution try count.
    *
    * @param iterations May not be less than zero. Get with {@link
    *                   #getIterations()}.
    */
   public Benchmarker(int iterations) {
      if (iterations < 1) {
         throw new IllegalArgumentException("iterations=" + iterations);
      }

      this.iterations = iterations;
   }

   /**
    * The number of times to execute the task.
    *
    * @see #Benchmarker(int)
    */
   public int getIterations() {
      return iterations;
   }

   /**
    * Executes the task the requested number of times, returning the number of
    * nanoseconds it took.
    *
    * @param to_benchmark May not be null.
    * @return The number of nanoseconds the task takes to execute, the
    * {@linkplain #getIterations() requested number of times}. Both {@link
    * TaskToBenchmark#setup() setup} and {@link TaskToBenchmark#breakdown()
    * breakdown} are included in this time.
    */
   public BenchmarkResult executeGetDuration(TaskToBenchmark to_benchmark) {
      Objects.requireNonNull(to_benchmark, "to_benchmark");

      long lStart = System.nanoTime();

      try {
         to_benchmark.setup();
      } catch (Exception x) {
         throw new BenchmarkSetupFailedException(x);
      }

      for (int tries = 0; tries < getIterations(); tries++) {
         try {
            to_benchmark.runCodeToBeTimed();
         } catch (Exception x) {
            throw new BenchmarkProperFailedException(tries, x);
         }
      }

      try {
         to_benchmark.breakdown();
      } catch (Exception x) {
         throw new BenchmarkBreakdownFailedException(x);
      }

      long totalNanos = System.nanoTime() - lStart;

      return new BenchmarkResult(to_benchmark, getIterations(), totalNanos);
   }
}
