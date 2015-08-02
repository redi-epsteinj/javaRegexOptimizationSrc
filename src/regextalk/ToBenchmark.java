package regextalk;

/**
 *
 */
public interface ToBenchmark {
   String[] getInputs();
   void runCodeToBeTimed();
   public static final String IGNORED_INPUT = "ignored input";
}
