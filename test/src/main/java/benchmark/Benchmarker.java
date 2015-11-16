package benchmark;

import static java.lang.String.format;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Collections;
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

   public static AllSuiteResults runTestSuiteGetResults(TimedTestConsoleOutput 
                                                                      output_action,
                                                              SuiteToBenchmark suite) {
      Objects.requireNonNull(suite, "suite");

      System.out.println(suite);
      
      List<TaskToBenchmark> taskList = suite.getImmutableTaskList();
      int taskCount = taskList.size();

      int suiteIters = suite.getSuiteIters();
      List<List<BenchmarkResult>> resultListList = new ArrayList<>(suiteIters);
      Benchmarker benchmarker = new Benchmarker(suite.getTaskIters(), output_action);

      for (int suiteItersSoFar = 0; suiteItersSoFar < suiteIters; suiteItersSoFar++) {
         System.out.printf("Suite run %d of %d%n", (suiteItersSoFar + 1), suiteIters);
         
         List<BenchmarkResult> resultList = new ArrayList<>(taskCount);
         
         int[] idxElement0 = {0};
         taskList.stream().forEach(task -> {
            BenchmarkResult result = benchmarker.executeGetDuration(task);
            resultList.add(result);
   
            if ((idxElement0[0] < taskCount - 1)) {
               try {
                  Thread.sleep(suite.getMillsBetweenTasks());
               } catch (InterruptedException ex) {
                  Thread.currentThread().interrupt();
               }
            }
   
            idxElement0[0]++;
         });
         
         resultListList.add(Collections.unmodifiableList(resultList));

         if ((suiteItersSoFar < suite.getSuiteIters() - 1)) {
            try {
               Thread.sleep(suite.getMillsBetweenSuites());
            } catch (InterruptedException ex) {
               Thread.currentThread().interrupt();
            }
         }
      }

      return new AllSuiteResults(resultListList);
   }

   public static StringBuilder runTestSuiteAppendResults(StringBuilder buffer, 
                                                         TimedTestConsoleOutput output_action,
                                                         SuiteToBenchmark suite) {
      AllSuiteResults allResults = runTestSuiteGetResults(output_action, suite);
      List<BenchmarkResult> averagedResults = allResults.getAveragedResultList();

      final String introLine = format("Task iterations=%d, suite iterations=%d (below results averaged)%n",
                                      suite.getTaskIters(), suite.getSuiteIters());
      System.out.print(introLine);
      
      if (suite.getDescription() != null) {
         System.out.println(suite.getDescription());
      }

      new BenchmarkComparer.Builder().build(averagedResults.get(0), averagedResults.get(1)).
            appendCurrentVsPreviousTwoLineOutput(buffer);

      int taskCount = averagedResults.size();
      if (taskCount == 2) {
         return buffer;
      }

      buffer.append(LINE_SEP);

      System.out.print(introLine);

      BenchmarkResult first = averagedResults.get(0);
      IntStream.range(2, taskCount).forEach(index -> {
         ThreeWayComparer comparer = new ThreeWayComparer(first, averagedResults.get(index - 1),
                                                          averagedResults.get(index), null);
         comparer.appendOutputForCurrentVsPrevAndFirst(buffer);
         if (index < (taskCount - 1)) {
            buffer.append(LINE_SEP);
         }
      });

      return buffer;
   }
}
   