package benchmark;

/**
 * A task that may be repeatedly timed by {@code Benchmarker}, whose result may
 * be compared to another timed-task.
 */
public interface TaskToBenchmark {

   /**
    * One-time only actions that occur before the first call to {@code
    * runCodeToBeTimed()}. By default this does nothing.
    */
   default void setup() {
      //Does nothing
   }

   /**
    * The code to be executed multiple times.
    */
   void runCodeToBeTimed();

   /**
    * One-time only actions that occur after the last call to {@code
    * runCodeToBeTimed()}. By default this does nothing.
    */
   default void breakdown() {
      //Does nothing
   }
}
