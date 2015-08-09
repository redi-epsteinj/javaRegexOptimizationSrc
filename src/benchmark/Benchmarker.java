package benchmark;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.IntStream;

/**
 * Determines how long it takes to execute a single task, multiple times.
 *
 * @see <CODE><A HREF="http://stackoverflow.com/questions/180158/how-do-i-time-a-methods-execution-in-java">http://stackoverflow.com/questions/180158/how-do-i-time-a-methods-execution-in-java</A></CODE>
 **/
public class Benchmarker {

   private static final String LINE_SEP = System.getProperty("line.separator", "\n");
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

      System.out.println(iterations + " iterations");

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

      System.out.println(to_benchmark.getClass().getSimpleName() + "...");

      PrintStream originalPrintStream = System.out;
      System.setOut(new PrintStream(new OutputStream() {
         @Override public void write(int b) throws IOException {}
      }));

      long totalNanos;
      try {

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

         totalNanos = System.nanoTime() - lStart;

      } finally {
         System.setOut(originalPrintStream);
      }

      return new BenchmarkResult(to_benchmark, getIterations(), totalNanos);
   }

   public static StringBuilder runTestSequenceAppendResults(StringBuilder builder,  int iterations,
                                                  TaskToBenchmark... to_benchmarks) {
      int size;
      try {
         size = to_benchmarks.length;
      } catch (NullPointerException x) {
         throw new NullPointerException("to_benchmarks");
      }

      if (size < 2) {
         throw new IllegalArgumentException("to_benchmarks.length is " + size);
      }

      List<BenchmarkResult> resultList = new ArrayList<>(to_benchmarks.length);
      Benchmarker benchmarker = new Benchmarker(iterations);
      Arrays.stream(to_benchmarks).forEach(task -> {
         BenchmarkResult result = benchmarker.executeGetDuration(task);
         resultList.add(result);
      });

      if (size == 2) {
          return new BenchmarkComparer(resultList.get(0), resultList.get(1))
                .appendCurrentVsPreviousTwoLineOutput(builder);
      }

      BenchmarkResult first = resultList.get(0);
      builder.append(BenchmarkComparer.getTaskTookNanosOutput(first)).append(LINE_SEP);
      IntStream.range(1, resultList.size()).forEach(index -> {
         ThreeWayComparer comparer = new ThreeWayComparer(first, resultList.get(index - 1),
                                                          resultList.get(index));
         comparer.appendOutputForCurrentVsPrevAndFirst(builder);
         if (index < (size - 1)) {
             builder.append(LINE_SEP);
         }
      });

      return builder;
   }
}
