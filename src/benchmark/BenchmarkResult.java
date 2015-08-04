package benchmark;

import static java.lang.String.format;

import java.util.Objects;

/**
 * The task, speed, and iteration-count of a single timed test.
 */
public class BenchmarkResult {

   private final long totalNanos;
   private final TaskToBenchmark task;
   private final int iterations;

   /**
    * Create an instance with task-name, iteration count, and nanosecond
    * length.
    *
    * @param to_benchmark May not be null. Get with {@link #getTask()}.
    * @param iterations   May not be less than one. Get with {@link
    *                     #getIterations()}.
    * @param total_nanos  May not be less than one. Get with {@link
    *                     #getTask()}.
    */
   public BenchmarkResult(TaskToBenchmark to_benchmark, int iterations,
                          long total_nanos) {
      Objects.requireNonNull(to_benchmark, "to_benchmark");
      if (iterations < 1) {
         throw new IllegalArgumentException("iterations=" + iterations);
      }
      if (total_nanos < 1L) {
         throw new IllegalArgumentException("total_nanos=" + iterations);
      }

      this.task = to_benchmark;
      this.iterations = iterations;
      this.totalNanos = total_nanos;
   }

   /**
    * Descriptive name of the task.
    *
    * @see #BenchmarkResult(TaskToBenchmark, int, long)
    */
   public TaskToBenchmark getTask() {
      return task;
   }

   /**
    * The nanoseconds it took to execute the task {@code getIterations()}
    * times.
    *
    * @see #BenchmarkResult(TaskToBenchmark, int, long)
    */
   public long getTotalNanos() {
      return totalNanos;
   }

   /**
    * The number of times the task was executed.
    *
    * @see #BenchmarkResult(TaskToBenchmark, int, long)
    */
   public int getIterations() {
      return iterations;
   }

   @Override
   public String toString() {
      return format("%s: iterations: %d, total-nanos=%10d",
                    getTask().getClass().getName(), getIterations(),
                    getTotalNanos());
   }
}
