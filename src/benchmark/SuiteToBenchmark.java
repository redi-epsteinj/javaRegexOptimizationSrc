package benchmark;

import com.github.xbn2.lang.ThrowNPXIf;
import com.github.xbn2.number.ThrowIAEIfInt;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * A sequential series of tasks to benchmark, ideally from slowest to fastest (or vice-versa), with
 * the run-time for each task averaged from multiple runs of the entire suite.
 */
public class SuiteToBenchmark {
   public static int DEFAULT_MILLS_PAUSE_BETWEEN_TASKS = 2000;
   public static int DEFAULT_MILLS_PAUSE_BETWEEN_SUITES = 8000;

   private final List<TaskToBenchmark> immutableTaskList;
   private final String description;
   private final int taskIters;
   private final int suiteIters;
   private final int millsBetweenTasks;
   private final int millsBetweenSuites;

   public SuiteToBenchmark(SuiteToBenchmark.Builder builder) {
      try {
         immutableTaskList = Collections.unmodifiableList(Arrays.asList(builder.tasks));
      } catch (NullPointerException x) {
         Objects.requireNonNull(builder, "builder");
         throw ThrowNPXIf.nullOrReturnCause(builder.tasks, "builder.tasks", x);
      }
      description = builder.description;
      taskIters = builder.taskIters;
      suiteIters = builder.suiteIters;
      millsBetweenTasks = builder.millsBetweenTasks;
      millsBetweenSuites = builder.millsBetweenSuites;

      ThrowIAEIfInt.lessThan(immutableTaskList.size(), "builder.tasks.length", 2);
      ThrowIAEIfInt.lessThan(taskIters, "builder.taskIters", 1);
      ThrowIAEIfInt.lessThan(suiteIters, "builder.suiteIters", 1);
      ThrowIAEIfInt.lessThan(millsBetweenTasks, "builder.millsBetweenTasks", 1);
      ThrowIAEIfInt.lessThan(millsBetweenSuites, "builder.millsBetweenSuites", 1);
   }

   public List<TaskToBenchmark> getImmutableTaskList() {
      return immutableTaskList;
   }

   public String getDescription() {
      return description;
   }

   public int getTaskIters() {
      return taskIters;
   }

   public int getSuiteIters() {
      return suiteIters;
   }

   public int getMillsBetweenTasks() {
      return millsBetweenTasks;
   }

   public int getMillsBetweenSuites() {
      return millsBetweenSuites;
   }

   @Override
   public String toString() {
      return "SuiteToBenchmark{" +
             "immutableTaskList=" + immutableTaskList +
             ((getDescription() == null) ? "" : ", description='" + description + '\'') +
             ", taskIters=" + taskIters +
             ", suiteIters=" + suiteIters +
             ", millsBetweenTasks=" + millsBetweenTasks +
             ", millsBetweenSuites=" + millsBetweenSuites +
             '}';
   }

   public static class Builder {

      public TaskToBenchmark[] tasks;
      public String description;
      public int taskIters;
      public int suiteIters;

      public int millsBetweenTasks;
      public int millsBetweenSuites;

      public Builder() {
         description(null);
         taskIterations(-1);
         suiteIterations(-1);
         millsBetweenTasks(DEFAULT_MILLS_PAUSE_BETWEEN_TASKS);
         millsBetweenSuites(DEFAULT_MILLS_PAUSE_BETWEEN_SUITES);
      }

      public Builder suiteIterations(int iterations) {
         suiteIters = iterations;
         return this;
      }

      public Builder taskIterations(int iterations) {
         taskIters = iterations;
         return this;
      }

      public Builder millsBetweenTasks(int mills) {
         this.millsBetweenTasks = mills;
         return this;
      }

      public Builder millsBetweenSuites(int mills) {
         this.millsBetweenSuites = mills;
         return this;
      }

      public Builder description(String description) {
         this.description = description;
         return this;
      }

      public Builder tasks(TaskToBenchmark... tasks) {
         this.tasks = tasks;
         return this;
      }

      public SuiteToBenchmark build(TaskToBenchmark... tasks) {
         tasks(tasks);
         return new SuiteToBenchmark(this);
      }
   }
}
