package test.benchmark;

import benchmark.BenchmarkResults;

class ResultWithNanos extends BenchmarkResults {
   public ResultWithNanos(long nanos) {
      this(10, nanos);
   }
   public ResultWithNanos(int iterations, long nanos) {
      super(new ValidTest(), iterations, nanos);
   }
}