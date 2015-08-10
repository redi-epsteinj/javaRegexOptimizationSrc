package benchmark;

import static java.lang.String.format;

import java.util.MissingFormatArgumentException;

/**
 * A {@code BenchmarkComparer} for three tasks, intended for getting results of the current in
 * comparison to the immediately-previous, and the first (in a sequence of task-results).
 */
public class ThreeWayComparer {

   public static final String DEFAULT_FASTER_PREV_1ST_TMPL =
         "%s (%s%% faster than previous, %s%% than first)";

   private final BenchmarkComparer firstCurrent;
   private final BenchmarkComparer prevCurrent;
   public final String fasterThanPrev1stTmpl;


   public ThreeWayComparer(BenchmarkResult first, BenchmarkResult previous,
                           BenchmarkResult current, String faster_thanPrev1stTmplNullForDefault) {
      this(first, previous, current, new BenchmarkComparer.Builder(),
           faster_thanPrev1stTmplNullForDefault);
   }

   public ThreeWayComparer(BenchmarkResult first, BenchmarkResult previous,
                           BenchmarkResult current, BenchmarkComparer.Builder
                                 comparer_builder, String faster_thanPrev1stTmplNullForDefault) {
      firstCurrent = new BenchmarkComparer.Builder(comparer_builder).build(first, current);
      prevCurrent = new BenchmarkComparer.Builder(comparer_builder).build(previous, current);
      fasterThanPrev1stTmpl = (faster_thanPrev1stTmplNullForDefault == null)
                              ? DEFAULT_FASTER_PREV_1ST_TMPL : faster_thanPrev1stTmplNullForDefault;
   }

   public BenchmarkComparer getFirstCurrent() {
      return firstCurrent;
   }

   public BenchmarkComparer getPrevCurrent() {
      return prevCurrent;
   }

   public StringBuilder appendOutputForCurrentVsPrevAndFirst(StringBuilder builder) {
      final String prevTook = getPrevCurrent().getCurrentTaskTookNanosOutput();
      final String currPctOfPrev = getPrevCurrent().getPercentageFormat().
            format(getPrevCurrent().getPercentageSpeedOfPrevious());
      final String currPctOf1st = getPrevCurrent().getPercentageFormat().
            format(getFirstCurrent().getPercentageSpeedOfPrevious());
      try {
         return builder.append(format(getFasterThanPrev1stTemplate(), prevTook, currPctOfPrev,
                                   currPctOf1st));
      } catch (MissingFormatArgumentException x) {
         final String message2 = format("getFasterThanPrev1stTemplate() (%s) has an extra format " +
                                        "specifier. Must have exactly three string specifiers " +
                                        "(%%s).",
                                        getFasterThanPrev1stTemplate());
         MissingFormatArgumentException x2 = new MissingFormatArgumentException(message2);
         x2.initCause(x);
         throw x2;
      }
   }

   public String getFasterThanPrev1stTemplate() {
      return fasterThanPrev1stTmpl;
   }
}
