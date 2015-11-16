package benchmark;

/**
 * Thrown when the {@link TaskToBenchmark#runCodeToBeTimed()} throws an
 * exception for any reason.
 */
public class BenchmarkProperFailedException extends RuntimeException {

   public BenchmarkProperFailedException(int tries_soFar, String message,
                                         Throwable cause) {

      super(
            (message == null)
            ? "tries-so-far=" + tries_soFar
            : message + " -- (tries so far" + tries_soFar + ")",
            cause);
   }

   public BenchmarkProperFailedException(int tries_soFar, Throwable cause) {
      this(tries_soFar, null, cause);
   }
}
