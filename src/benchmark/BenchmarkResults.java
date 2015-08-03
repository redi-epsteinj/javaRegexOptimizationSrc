package benchmark;

import java.util.Objects;

public class BenchmarkResults {

   private final long totalNanos;
   private final ToBenchmark toBenchmark;
   private final int iterations;

   /**
    * Create an instance with task-name, iteration count, and nanosecond length.
    * @param to_benchmark May not be null. Get with {@link #getToBenchmark()}.
    * @param iterations May not be less than one. Get with {@link #getIterations()}.
    * @param total_nanos May not be less than one. Get with {@link #getToBenchmark()}.
    */
   public BenchmarkResults(ToBenchmark to_benchmark, int iterations, long total_nanos) {
      Objects.requireNonNull(to_benchmark, "to_benchmark");
      if (iterations < 1) {
         throw new IllegalArgumentException("iterations=" + iterations);
      }
      if (total_nanos < 1L) {
         throw new IllegalArgumentException("total_nanos=" + iterations);
      }

      this.toBenchmark = to_benchmark;
      this.iterations = iterations;
      this.totalNanos = total_nanos;
   }

   /**
    * Descriptive name of the task.
    * @see #BenchmarkResults(ToBenchmark, int, long)
    */
   public ToBenchmark getToBenchmark() {
      return toBenchmark;
   }

   /**
    * The nanoseconds it took to execute the task {@code getIterations()} times.
    * @see #BenchmarkResults(ToBenchmark, int, long)
    */
   public long getTotalNanos() {
      return totalNanos;
   }

   /**
    * The number of times the task was executed.
    * @see #BenchmarkResults(ToBenchmark, int, long)
    */
   public int getIterations() {
      return iterations;
   }

   @Override
   public String toString() {
      return  String.format("%s: iterations: %d, total-nanos=%10d",
                            getToBenchmark().getClass().getName(), getIterations(),
                            getTotalNanos());
   }
}
