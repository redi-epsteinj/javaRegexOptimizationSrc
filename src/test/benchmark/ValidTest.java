package test.benchmark;

import benchmark.TaskToBenchmark;

public class ValidTest implements TaskToBenchmark {

   @Override
   public void runCodeToBeTimed() {
      int i = 1;
   }
}
