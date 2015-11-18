package regextalk.password;

/**
 * Benchmarks using my own custom benchmark library, meaning unreliable. Use the JMH benchmarks instead.
 */
public class Password03LogicThreeRulesToBenchmark extends AbstractPasswordLogic {
   public Password03LogicThreeRulesToBenchmark() {
      super(3);
   }

   public static void main(String[] args) {
      new Password03LogicThreeRulesToBenchmark().setupRunBreakdown();
   }
}
