package benchmark;

/**
 * Thrown when the {@link ToBenchmark#setup()} throws an exception for any reason.
 */
public class BenchmarkSetupFailedException extends RuntimeException {

   public BenchmarkSetupFailedException(String message, Throwable cause) {
      super(message, cause);
   }

   public BenchmarkSetupFailedException(Throwable cause) {
      super(cause);
   }
}
