package benchmark;

import static java.lang.String.format;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;
import java.util.MissingFormatArgumentException;
import java.util.Objects;

/**
 * Compares the results of two timed tasks, with convenience functions for basic output strings.
 */
public class BenchmarkComparer {

   public static final String DEFAULT_TOOK_NANOS_TEMPLATE = "%s: %s";
   public static final String DEFAULT_FASTER_THAN_PREV_TMPL =
         "%s (%s%% faster than previous)";
   public static final NumberFormat DEFAULT_NANOS_FORMAT =
         NumberFormat.getNumberInstance();
   public static final NumberFormat DEFAULT_PERCENTAGE_FORMAT = newDefaultPctgFmt();

   private static NumberFormat newDefaultPctgFmt() {
      DecimalFormat format = (DecimalFormat) NumberFormat.getInstance(Locale.getDefault());

      format.setMaximumFractionDigits(2); // << This should
      format.setMinimumFractionDigits(2); // << do the trick
      return format;
   }

   private final BenchmarkResult previous;
   private final BenchmarkResult current;
   private final NumberFormat nanosFormat;
   private final NumberFormat percentageFormat;
   private final String fasterThanPrevTmpl;


   private final String taskNameNanosTmpl;
   private Locale locale;

   public BenchmarkComparer(final BenchmarkResult previous, final BenchmarkResult current) {
      this(new BenchmarkComparer.Builder().prevCurrResults(previous, current));
   }

   /**
    * <p>Create an instance comparing two tasks and number format.</p>
    *
    * <p>All parameters must be non-null.</p>
    */
   public BenchmarkComparer(final BenchmarkComparer.Builder builder) {
      Objects.requireNonNull(builder, "builder");

      previous = builder.previous;
      current = builder.current;
      nanosFormat = builder.nanosFormat;
      percentageFormat = builder.percentageFormat;
      taskNameNanosTmpl = builder.taskNameNanosTmpl;
      fasterThanPrevTmpl = builder.fasterThanPrevTmpl;
      locale = builder.locale;

      Objects.requireNonNull(nanosFormat, "builder.nanosFormat");
      Objects.requireNonNull(percentageFormat, "builder.percentageFormat");
      Objects.requireNonNull(taskNameNanosTmpl, "builder.taskNameNanosTmpl");
      Objects.requireNonNull(fasterThanPrevTmpl, "builder.fasterThanPrevTmpl");
      Objects.requireNonNull(locale, "builder.locale");

      try {
         if (previous.getIterations() != current.getIterations()) {
            final String message = "previous.getIterations() (" + previous.getIterations() + ") " +
                                   "different than current.getIterations() (" +
                                   current.getIterations() + ")";
            throw new IllegalArgumentException(message);
         }
      } catch (NullPointerException x) {
         Objects.requireNonNull(previous, "builder.previous");
         Objects.requireNonNull(current, "builder.current");
      }
   }

   /**
    * The previous task to compare to.
    *
    * @see #BenchmarkComparer(BenchmarkComparer.Builder)
    * @see #getCurrent()
    */
   public BenchmarkResult getPrevious() {
      return previous;
   }

   /**
    * The previous task to compare against.
    *
    * @see #BenchmarkComparer(BenchmarkComparer.Builder)
    * @see #getPrevious()
    */
   public BenchmarkResult getCurrent() {
      return current;
   }

   /**
    * The nanoseconds formatter.
    *
    * @see #BenchmarkComparer(BenchmarkComparer.Builder)
    */
   public NumberFormat getNanosFormat() {
      return nanosFormat;
   }

   /**
    * The percentage formatter.
    *
    * @see #BenchmarkComparer(BenchmarkComparer.Builder)
    */
   public NumberFormat getPercentageFormat() {
      return percentageFormat;
   }

   public String getFasterThanPrevTmpl() {
      return fasterThanPrevTmpl;
   }

   public String getTaskNameNanosTmpl() {
      return taskNameNanosTmpl;
   }

   public Locale getLocale() {
      return locale;
   }


   /**
    * <p>The percentage of current task's speed as compared to the previous.</p>
    *
    * <p>Equal to <br> &nbsp; &nbsp; <code> {@link #getPercentageSpeedOfPrevious (BenchmarkResult,
    * BenchmarkResult) getPercentageSpeedOfPrevious}({@link #getPrevious()}, {@link
    * #getCurrent()})</code>
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
      final String nanos = getNanosFormat().format(getCurrent().getTotalNanos());
      final String percent = getPercentageFormat().format(getPercentageSpeedOfPrevious());
      String message;
      final String prevTook = getTaskTookNanosOutput(getPrevious());
      final String currTook = getTaskTookNanosOutput(getCurrent());
      try {
         message = format("%s%n" + getFasterThanPrevTmpl(), prevTook, currTook, percent);
      } catch (MissingFormatArgumentException x) {
         final String message2 = format("getFasterThanPrevTmpl() (%s) has an extra format " +
                                        "specifier. Must have exactly two string specifiers (%%s).",
                                       getFasterThanPrevTmpl());
         MissingFormatArgumentException x2 = new MissingFormatArgumentException(message2);
         x2.initCause(x);
         throw x2;
      }
      return builder.append(message);
   }

   /**
    * A basic display-worthy string containing the current task's name and speed, using the default
    * template and locale.
    *
    * @see #getTaskTookNanosOutput(BenchmarkResult)
    * @see #getCurrent()
    */
   public String getCurrentTaskTookNanosOutput() {
      return getTaskTookNanosOutput(getCurrent());
   }

   /**
    * A basic display-worthy string containing the previous task's name and speed, using the default
    * template and locale.
    *
    * @see #getTaskTookNanosOutput(BenchmarkResult)
    * @see #getPrevious()
    */
   public String getPreviousTaskTookNanosOutput() {
      return getTaskTookNanosOutput(getPrevious());
   }

   /**
    * A basic display-worthy string containing the current task's name and speed, using the default
    * template and locale.
    *
    * @param result May not be null.
    * @see #getCurrentTaskTookNanosOutput()
    */
   private String getTaskTookNanosOutput(BenchmarkResult result) {
      try {
         return format(getLocale(), getTaskNameNanosTmpl(), result.getTask().getClass()
                             .getName(),
                       getNanosFormat().format(result.getTotalNanos()));
      } catch (MissingFormatArgumentException x) {
         final String message = format("getTaskNameNanosTmpl() (%s) has an extra format specifier." +
                                       " Must have exactly two string specifiers (%%s).",
                                       getTaskNameNanosTmpl());
         MissingFormatArgumentException x2 = new MissingFormatArgumentException(message);
         x2.initCause(x);
         throw x2;
      } catch (NullPointerException x) {
         Objects.requireNonNull(result, "result");
         throw x;
      }
   }

   public static class Builder {

      public BenchmarkResult previous;
      public BenchmarkResult current;
      public NumberFormat nanosFormat;
      public NumberFormat percentageFormat;
      public String taskNameNanosTmpl;
      public String fasterThanPrevTmpl;
      private Locale locale;

      public Builder(Builder to_copy) {
         try {
            nanosFormat(to_copy.nanosFormat);
         } catch (NullPointerException x) {
            Objects.requireNonNull(to_copy, "to_copy");
         }
         percentageFormat(to_copy.percentageFormat);
         taskNameNanosTemplate(to_copy.taskNameNanosTmpl);
         fasterThanPrevTemplate(to_copy.fasterThanPrevTmpl);
         locale(to_copy.locale);
      }

      public Builder() {
         nanosFormat(BenchmarkComparer.DEFAULT_NANOS_FORMAT);
         percentageFormat(BenchmarkComparer.DEFAULT_PERCENTAGE_FORMAT);
         taskNameNanosTemplate(BenchmarkComparer.DEFAULT_TOOK_NANOS_TEMPLATE);
         fasterThanPrevTemplate(BenchmarkComparer.DEFAULT_FASTER_THAN_PREV_TMPL);
         defaultLocale();
      }

      private Builder defaultLocale() {
         return locale(Locale.getDefault());
      }

      private Builder locale(Locale locale) {
         this.locale = locale;
         return this;
      }

      public Builder fasterThanPrevTemplate(String template) {
         fasterThanPrevTmpl = template;
         return this;
      }

      public Builder taskNameNanosTemplate(String template) {
         taskNameNanosTmpl = template;
         return this;
      }

      public Builder percentageFormat(NumberFormat format) {
         percentageFormat = format;
         return this;
      }

      public Builder nanosFormat(NumberFormat format) {
         nanosFormat = format;
         return this;
      }

      /**
       * @param previous Must have the same {@linkplain BenchmarkResult#getIterations() iteration
       *                 count} as {@code current}. Get with {@link BenchmarkComparer#getPrevious()}.
       * @param current  Get with {@link BenchmarkComparer#getCurrent()}.
       */
      public Builder prevCurrResults(BenchmarkResult previous, BenchmarkResult current) {
         this.previous = previous;
         this.current = current;
         return this;
      }

      public BenchmarkComparer build(BenchmarkResult previous, BenchmarkResult current) {
         prevCurrResults(previous, current);
         return new BenchmarkComparer(this);
      }
   }
}
