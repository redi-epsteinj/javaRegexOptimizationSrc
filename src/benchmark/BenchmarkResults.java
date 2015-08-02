package benchmark;

public class BenchmarkResults {

   private final long totalNanos;
   private final String taskName;
   private final int iterations;

   public BenchmarkResults(String task_name, int iterations, long total_nanos) {
      try {
         if (task_name.length() == 0) {
             throw new IllegalArgumentException("task_name has no characters");
         }
      } catch(NullPointerException x) {
         throw new NullPointerException("task_name");
      }
      if (iterations < 1) {
         throw new IllegalArgumentException("iterations=" + iterations);
      }
      if (total_nanos < 1) {
         throw new IllegalArgumentException("total_nanos=" + iterations);
      }

      this.taskName = task_name;
      this.iterations = iterations;
      this.totalNanos = total_nanos;
   }

   /**
    * Descriptive name of the task.
    * @see #BenchmarkResults(String, int, long)
    */
   public String getTaskName() {
      return taskName;
   }

   /**
    * The nanoseconds it took to execute the task {@code getIterations()} times.
    * @see #BenchmarkResults(String, int, long)
    */
   public long getTotalNanos() {
      return totalNanos;
   }

   /**
    * The number of times the task was executed.
    * @see #BenchmarkResults(String, int, long)
    */
   public int getIterations() {
      return iterations;
   }

   @Override
   public String toString() {
      return  String.format("%s: iterations: %d, total-nanos=%10d",
                            getTaskName(), getIterations(), getTotalNanos());
   }
   public static final BenchmarkImprovement improvements(BenchmarkResults other_results) {
      return null;
   }
}
