package regextalk.numericrange;

import java.util.Arrays;
import benchmark.TaskToBenchmark;

public class NumericRangeLogic implements TaskToBenchmark {

   public static void main(String[] cmd_lineParams) {
      new NumericRangeLogic().setupRunBreakdown();
   }

   @Override
   public void setup() {
      System.out.println(getClass().getSimpleName());
   }

   @Override
   public void runCodeToBeTimed() {
      Arrays.stream(RangeRegexAnchoredFind.INPUTS).forEach(strNum -> {
         int num = Integer.parseInt(strNum);       //Definitely a valid integer
         boolean inRange = (-2055 <= num && num <= 2055);
         System.out.println(num + ": " + (inRange ? "I" : "NOT i") + "n range");
      });
   }
}
