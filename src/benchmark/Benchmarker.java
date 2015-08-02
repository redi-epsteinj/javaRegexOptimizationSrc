package benchmark;

import java.util.Objects;

/**
 * Determines how long it takes to execute a task multiple times.
 *
 * @see <CODE><A HREF="http://stackoverflow.com/questions/180158/how-do-i-time-a-methods-execution-in-java">http://stackoverflow.com/questions/180158/how-do-i-time-a-methods-execution-in-java</A></CODE>
 **/
public class Benchmarker {

   private final int tryCount;

   /**
    * Create an instance with the execution try count.
    * @param try_count May not be less than zero. Get with {@link #getTryCount()}.
    */
   public Benchmarker(int try_count) {
      if (try_count < 1) {
         throw new IllegalArgumentException("try_count=" + try_count);
      }

      tryCount = try_count;
   }

   /**
    * The number of times to execute the task.
    * @see #Benchmarker(int)
    */
   public int getTryCount() {
      return tryCount;
   }

   /**
    * Executes the task the requested number of times, returning the number of nanoseconds it took.
    * @param to_benchmark May not be null.
    * @return The number of nanoseconds the task takes to execute, the {@linkplain #getTryCount()
    * requested number of times}. Both {@link ToBenchmark#setup() setup} and {@link
    * ToBenchmark#breakdown() breakdown} are included in this time.
    */
   public BenchmarkResults executeGetDuration(String task_name, ToBenchmark to_benchmark) {
      Objects.requireNonNull(to_benchmark, "to_benchmark");

      long lStart = System.nanoTime();

      try {
         to_benchmark.setup();
      } catch (Exception x) {
         throw new BenchmarkSetupFailedException(x);
      }

      for (int tries = 0; tries < getTryCount(); tries++) {
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

      return new BenchmarkResults(task_name, getTryCount(), totalNanos);
   }
}
