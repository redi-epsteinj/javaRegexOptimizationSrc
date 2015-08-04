package test.benchmark;

import benchmark.BenchmarkResult;

class ResultWithNanos extends BenchmarkResult {

   public ResultWithNanos(long nanos) {
      this(10, nanos);
   }

   public ResultWithNanos(int iterations, long nanos) {
      super(new ValidTest(), iterations, nanos);
   }
}