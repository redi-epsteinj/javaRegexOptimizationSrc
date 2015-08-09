package benchmark;

import static java.lang.String.format;

import java.text.NumberFormat;
import java.util.Locale;
import java.util.Objects;

/**
 * Compares the results of two timed tasks, with convenience functions for basic
 * output strings.
 */
public class BenchmarkComparer {

   public static final NumberFormat
         DEFAULT_NUMBER_FORMAT       =
         NumberFormat.getNumberInstance(
               Locale.US);
   public static final String
         DEFAULT_TOOK_NANOS_TEMPLATE =
         "%s: %s";
   private final BenchmarkResult previous;
   private final BenchmarkResult current;
   private final NumberFormat    numberFormat;

   /**
    * <p>Create an instance with two tasks, using United States number
    * format.</p>
    *
    * <p>Equal to <br> &nbsp; &nbsp; <code>{@link #BenchmarkComparer(
    *BenchmarkResult, BenchmarkResult, NumberFormat) this}(first, previous,
    * current, {@link #DEFAULT_NUMBER_FORMAT})</code></p>
    */
   public BenchmarkComparer(BenchmarkResult previous,
                            BenchmarkResult current) {
      this(previous, current, DEFAULT_NUMBER_FORMAT);
   }

   /**
    * <p>Create an instance comparing two tasks and number format.</p>
    *
    * <p>All parameters must be non-null.</p>
    *
    * @param previous     Must have the same {@linkplain BenchmarkResult#getIterations()
    *                     iteration count} as {@code current}. Get with {@link
    *                     #getPrevious()}.
    * @param current      Get with {@link #getCurrent()}.
    * @param numberFormat Get with {@link #getNumberFormat()}.
    */
   public BenchmarkComparer(BenchmarkResult previous, BenchmarkResult current,
                            NumberFormat numberFormat) {
      Objects.requireNonNull(previous, "previous");
      Objects.requireNonNull(current, "current");
      Objects.requireNonNull(numberFormat, "numberFormat");

      if (previous.getIterations() != current.getIterations()) {
         final String message = "previous.getIterations() (" +
                                previous.getIterations() + ") different than "
                                + "current.getIterations() (" +
                                current.getIterations() + ")";
         throw new IllegalArgumentException(message);
      }

      this.previous = previous;
      this.current = current;
      this.numberFormat = numberFormat;
   }

   /**
    * The previous task to compare to.
    *
    * @see #BenchmarkComparer(BenchmarkResult, BenchmarkResult, NumberFormat)
    * @see #getCurrent()
    */
   public BenchmarkResult getPrevious() {
      return previous;
   }

   /**
    * The previous task to compare against.
    *
    * @see #BenchmarkComparer(BenchmarkResult, BenchmarkResult, NumberFormat)
    * @see #getPrevious()
    */
   public BenchmarkResult getCurrent() {
      return current;
   }

   /**
    * The number format.
    *
    * @see #BenchmarkComparer(BenchmarkResult, BenchmarkResult, NumberFormat)
    */
   public NumberFormat getNumberFormat() {
      return numberFormat;
   }

   /**
    * <p>The percentage of current task's speed as compared to the
    * previous.</p>
    *
    * <p>Equal to <br> &nbsp; &nbsp; <code> {@link #getPercentageSpeedOfPrevious
    * (BenchmarkResult, BenchmarkResult) getPercentageSpeedOfPrevious}({@link
    * #getPrevious()}, {@link #getCurrent()})</code>
    */
   public double getPercentageSpeedOfPrevious() {
      return getPercentageSpeedOfPrevious(getPrevious(), getCurrent());
   }

   /**
    * The percentage of a task's speed as compared to a previous one.
    *
    * @see #getPercentageSpeedOfPrevious()
    */
   public double getPercentageSpeedOfPrevious(BenchmarkResult previous,
                                              BenchmarkResult current) {
      return 100.00 - (current.getTotalNanos() * 100.00f / previous
            .getTotalNanos());
   }

   public StringBuilder appendCurrentVsPreviousTwoLineOutput(StringBuilder builder) {
      return builder.append(getTaskTookNanosOutput(getPrevious())).
            append(System.getProperty("line.separator", "\n")).
            append(getCurrentTaskTookNanosOutput()).append(", (").
            append(getPercentageSpeedOfPrevious()).append("% of previous)");
   }

   /**
    * A basic display-worthy string containing the current task's name and
    * speed, using the default template and locale.
    *
    * @see #DEFAULT_TOOK_NANOS_TEMPLATE
    * @see #getCurrent()
    * @see #getNumberFormat()
    */
   public String getCurrentTaskTookNanosOutput() {
      return getTaskTookNanosOutput(DEFAULT_TOOK_NANOS_TEMPLATE, getCurrent(),
                                    null, getNumberFormat());
   }

   /**
    * A basic display-worthy string containing task's name and speed, using the
    * default template, locale and number format.
    *
    * @see #getTaskTookNanosOutput(String, BenchmarkResult, Locale,
    * NumberFormat)
    * @see #DEFAULT_TOOK_NANOS_TEMPLATE
    * @see #DEFAULT_NUMBER_FORMAT
    */
   public static String getTaskTookNanosOutput(BenchmarkResult result) {
      return getTaskTookNanosOutput(DEFAULT_TOOK_NANOS_TEMPLATE, result, null,
                                    DEFAULT_NUMBER_FORMAT);
   }

   /**
    * <p>A basic display-worthy string containing a task's name and speed.</p>
    *
    * @param templateWithTwoSs May not be null, and must contain exactly two
    *                          string {@linkplain String#format(Locale, String,
    *                          Object...) format specifiers} ({@code "%s"}). The
    *                          first specifier is the task's {@linkplain
    *                          Class#getName() fully-qualified class name}.
    * @param result            May not be null.
    * @param locale            Passed directly to {@code String#format}.
    * @param numberFormat      For formatting {@code result.}{@link
    *                          BenchmarkResult#getTotalNanos() getTotalNanos()}.
    *                          May not be null.
    * @see #getTaskTookNanosOutput(BenchmarkResult)
    * @see #getCurrentTaskTookNanosOutput()
    */
   public static String getTaskTookNanosOutput(String templateWithTwoSs,
                                               BenchmarkResult result,
                                               Locale locale,
                                               NumberFormat numberFormat) {
      try {
         return format(locale,
                       templateWithTwoSs,
                       result.getTask().getClass().getName(),
                       numberFormat.format(result.getTotalNanos()));
      } catch (NullPointerException npx) {
         if (templateWithTwoSs == null) {
            throw new NullPointerException("templateWithTwoSs");
         }
         throw npx;
      }
   }
}
