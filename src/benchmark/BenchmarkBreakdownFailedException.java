package benchmark;

/**
 * Thrown when the {@link ToBenchmark#breakdown()} throws an exception for any reason.
 */
public class BenchmarkBreakdownFailedException extends RuntimeException {

   public BenchmarkBreakdownFailedException(String message, Throwable cause) {
      super(message, cause);
   }

   public BenchmarkBreakdownFailedException(Throwable cause) {
      super(cause);
   }
}
