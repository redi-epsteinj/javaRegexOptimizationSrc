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

   private final TimedTestConsoleOutput testOutput;

   /**
    * Create an instance with the execution try count, and where all test output is suppressed.
    *
    * @see TimedTestConsoleOutput#SUPPRESS
    */
   public Benchmarker(int iterations) {
      this(iterations, TimedTestConsoleOutput.SUPPRESS);
   }

   /**
    * Create an instance with the execution try count.
    *
    * @param iterations    May not be less than zero. Get with {@link #getIterations()}.
    * @param output_action May not be null. Get with {@link #getTimedTestOutputEnum()}.
    */
   public Benchmarker(int iterations, TimedTestConsoleOutput output_action) {
      Objects.requireNonNull(output_action, "output_action");
      if (iterations < 1) {
         throw new IllegalArgumentException("iterations=" + iterations);
      }

      System.out.println(iterations + " iterations");

      this.iterations = iterations;
      this.testOutput = output_action;
   }

   /**
    * The number of times to execute the task.
    *
    * @see #Benchmarker(int, TimedTestConsoleOutput)
    */
   public int getIterations() {
      return iterations;
   }

   /**
    * Defines if the console output in the timed test should be printed or supressed.
    *
    * @see #Benchmarker(int, TimedTestConsoleOutput)
    */
   public TimedTestConsoleOutput getTimedTestOutputEnum() {
      return testOutput;
   }

   /**
    * Executes the task the requested number of times, returning the number of nanoseconds it took.
    *
    * @param to_benchmark May not be null.
    * @return The number of nanoseconds the task takes to execute, the {@linkplain #getIterations()
    * requested number of times}. Both {@link TaskToBenchmark#setup() setup} and {@link
    * TaskToBenchmark#breakdown() breakdown} are included in this time.
    */
   public BenchmarkResult executeGetDuration(TaskToBenchmark to_benchmark) {
      Objects.requireNonNull(to_benchmark, "to_benchmark");

      System.out.print(to_benchmark.getClass().getSimpleName() + "...");

      PrintStream originalPrintStream = null;
      if (getTimedTestOutputEnum() == TimedTestConsoleOutput.SUPPRESS) {
         originalPrintStream = System.out;
         System.setOut(new PrintStream(new OutputStream() {
            @Override
            public void write(int b) throws IOException {}
         }));
      }

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
         if (originalPrintStream != null) {
            System.setOut(originalPrintStream);
         }
      }

      System.out.println(BenchmarkComparer.DEFAULT_NANOS_FORMAT.format(totalNanos));

      return new BenchmarkResult(to_benchmark, getIterations(), totalNanos);
   }

   public static StringBuilder runTestSuiteAppendResults(StringBuilder builder,
                                                         String description, int iterations,
                                                         int seconds_pauseBetweenTests,
                                                         TaskToBenchmark... to_benchmarks) {
      return runTestSuiteAppendResults(builder, description, iterations,
                                       TimedTestConsoleOutput.SUPPRESS,
                                       seconds_pauseBetweenTests,
                                       to_benchmarks);
   }

   public static StringBuilder runTestSuiteAppendResults(StringBuilder builder, String
         description, int iterations, TimedTestConsoleOutput output_action,
                                                         int mills_pauseBetweenTests,
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
      Benchmarker benchmarker = new Benchmarker(iterations, output_action);

      int[] idxElement0 = {0};
      Arrays.stream(to_benchmarks).forEach(task -> {
         BenchmarkResult result = benchmarker.executeGetDuration(task);
         resultList.add(result);

         if (idxElement0[0] < to_benchmarks.length - 1 && mills_pauseBetweenTests > 0) {
            try {
               Thread.sleep(mills_pauseBetweenTests);
            } catch (InterruptedException ex) {
               Thread.currentThread().interrupt();
            }
         }

         idxElement0[0]++;
      });

      if (description != null) {
         System.out.println(description);
      }

      new BenchmarkComparer.Builder().build(resultList.get(0), resultList.get(1)).
            appendCurrentVsPreviousTwoLineOutput(builder);

      if (size == 2) {
         return builder;
      }

      builder.append(LINE_SEP);

      BenchmarkResult first = resultList.get(0);
      IntStream.range(2, resultList.size()).forEach(index -> {
         ThreeWayComparer comparer = new ThreeWayComparer(first, resultList.get(index - 1),
                                                          resultList.get(index), null);
         comparer.appendOutputForCurrentVsPrevAndFirst(builder);
         if (index < (size - 1)) {
            builder.append(LINE_SEP);
         }
      });

      return builder;
   }
}
