package regextalk.numericrange;

import benchmark.TaskToBenchmark;

public interface NumericRangeToBenchmark extends TaskToBenchmark {

   default String[] getInputs() {
      return new String[]{"-2056", "-2055", "-10", "0", "10", "2055", "2056"};
   }
}
