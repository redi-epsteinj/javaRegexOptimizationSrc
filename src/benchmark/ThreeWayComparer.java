package benchmark;

import static java.lang.String.format;

import java.text.NumberFormat;

/**
 * A {@code BenchmarkComparer} for three tasks, intended for getting results of the current in
 * comparison to the immediately-previous, and the first (in a sequence of task-results).
 */
public class ThreeWayComparer {

   private final BenchmarkComparer firstCurrent;
   private final BenchmarkComparer prevCurrent;

   public ThreeWayComparer(BenchmarkResult first, BenchmarkResult previous,
                           BenchmarkResult current) {
      this(first, previous, current, BenchmarkComparer.DEFAULT_NUMBER_FORMAT);
   }
   public ThreeWayComparer(BenchmarkResult first, BenchmarkResult previous,
                           BenchmarkResult current, NumberFormat format) {
      firstCurrent = new BenchmarkComparer(first, current, format);
      prevCurrent = new BenchmarkComparer(previous, current, format);
   }

   public BenchmarkComparer getFirstCurrent() {
      return firstCurrent;
   }

   public BenchmarkComparer getPrevCurrent() {
      return prevCurrent;
   }

   public StringBuilder appendOutputForCurrentVsPrevAndFirst(StringBuilder builder) {
      return builder.append(format("%s (%%%s of previous, %%%s of first)",
                                    getPrevCurrent().getCurrentTaskTookNanosOutput(),
                                    getPrevCurrent().getPercentageSpeedOfPrevious(),
                                    getFirstCurrent().getPercentageSpeedOfPrevious()));
   }
}
