package benchmark;

import static java.lang.String.format;

import java.text.NumberFormat;
import java.util.Locale;
import java.util.Objects;

/**
 * The comparison between the results of two or three timed tasks: The first, immediately-previous,
 * and current.
 */
public class BenchmarkImprovement {

   private final BenchmarkResults previous;
   private final BenchmarkResults current;
   private final NumberFormat numberFormat;


   private final BenchmarkResults first;

   /**
    * <p>Create an instance comparing the first, previous, and current tasks, using United States
    * number format.</p>
    *
    * <p>Equal to <br> &nbsp; &nbsp; <code>{@link #BenchmarkImprovement(BenchmarkResults,
    * BenchmarkResults, BenchmarkResults, NumberFormat) this}(first, previous, current, {@linkplain
    * NumberFormat#getNumberInstance(Locale) NumberFormat.getNumberInstance}({@link
    * Locale#US}))</code></p>
    */
   public BenchmarkImprovement(BenchmarkResults first, BenchmarkResults previous,
                               BenchmarkResults current) {
      this(first, previous, current, NumberFormat.getNumberInstance(Locale.US));
   }

   /**
    * <p>Create an instance comparing the first, previous, and current tasks, and number
    * format.</p>
    *
    * <p>All parameters must be non-null.</p>
    *
    * @param first         Must have the same {@linkplain BenchmarkResults#getIterations() iteration
    *                      count} as both {@code previous} and {@code current}. If {@code current}
    *                      is the second task, then this and {@code previous} must be the same
    *                      object. Get with {@link #getFirst()}.
    * @param previous      Get with {@link #getPrevious()}.
    * @param current       Must be a different object than {@code previous}. Get with {@link #getCurrent()}.
    * @param number_format Get with {@link #getNumberFormat()}.
    */
   public BenchmarkImprovement(BenchmarkResults first, BenchmarkResults previous,
                               BenchmarkResults current, NumberFormat number_format) {
      Objects.requireNonNull(first, "first");
      Objects.requireNonNull(previous, "previous");
      Objects.requireNonNull(current, "current");
      Objects.requireNonNull(number_format, "number_format");

      crashIfDifferentIterationCounts(current, first, "first");
      crashIfDifferentIterationCounts(current, previous, "previous");

      if (current == previous) {
          throw new IllegalArgumentException("current and previous are the same object: " + current);
      }

      this.first = first;
      this.previous = previous;
      this.current = current;
      this.numberFormat = number_format;
   }

   private void crashIfDifferentIterationCounts(BenchmarkResults current,
                                                BenchmarkResults the_other, String other_name) {
      if (current.getIterations() != getPrevious().getIterations()) {
         throw new IllegalArgumentException(
            "current.getIterations() (" + current.getIterations() +
            ") is different than " + other_name + ".getIterations() (" +
            the_other.getIterations() + ")");
      }
   }

   /**
    * The very first task to compare to.
    *
    * @see #BenchmarkImprovement(BenchmarkResults, BenchmarkResults, BenchmarkResults, NumberFormat)
    */
   public BenchmarkResults getFirst() {
      return first;
   }

   /**
    * The immediately-previous task to compare to.
    *
    * @see #BenchmarkImprovement(BenchmarkResults, BenchmarkResults, BenchmarkResults, NumberFormat)
    */
   public BenchmarkResults getPrevious() {
      return previous;
   }

   /**
    * The previous task to compare against.
    *
    * @see #BenchmarkImprovement(BenchmarkResults, BenchmarkResults, BenchmarkResults, NumberFormat)
    */
   public BenchmarkResults getCurrent() {
      return current;
   }

   /**
    * Is the current task the second one?
    * @return True if the {@linkplain #getFirst() first} and
    * {@linkplain #getPrevious() immediately-previous} tasks are the same object.
    */
   public boolean isCurrentSecondTask() {
      return (getFirst() == getPrevious());
   }

   /**
    * The number format.
    *
    * @see #BenchmarkImprovement(BenchmarkResults, BenchmarkResults, BenchmarkResults, NumberFormat)
    */
   public NumberFormat getNumberFormat() {
      return numberFormat;
   }

   /**
    * The percentage the current task's speed is over the previous
    * @return
    */
   public double getImprovementPercentageOverPrevious() {
      return getImprovementPercentageOver(getPrevious());
   }

   public double getImprovementPercentageOverFirst() {
      return getImprovementPercentageOver(getFirst());
   }

   private double getImprovementPercentageOver(BenchmarkResults to_compareTo) {
      return 100.00 - (getCurrent().getTotalNanos() * 100.00f / to_compareTo.getTotalNanos());
   }

   public String getComparisonOutput() {
      double improvementPctgFirst = getImprovementPercentageOverFirst();
      double improvementPctgPrev = getImprovementPercentageOverPrevious();

      String fasterOrSlowerFirst;
      String fasterOrSlowerPrev;
      //Taking the chance it's not exactly the same
      if (improvementPctgFirst <= 0.00f) {
         fasterOrSlowerFirst = "slower";
         improvementPctgFirst *= -1.00f;
      } else {
         fasterOrSlowerFirst = "faster";
      }

      if (improvementPctgPrev <= 0.00f) {
         fasterOrSlowerPrev = "slower";
         improvementPctgPrev *= -1.00f;
      } else {
         fasterOrSlowerPrev = "faster";
      }

      return format(
         "Task: \"%s\", %s nanoseconds for %d iterations (%%%s %s than previous, %%%s %s than first)",
         getCurrent().getTaskName(),
         getNumberFormat().format(getCurrent().getTotalNanos()),
         getCurrent().getIterations(),
         improvementPctgPrev, fasterOrSlowerPrev,
         improvementPctgFirst, fasterOrSlowerFirst);
   }
}