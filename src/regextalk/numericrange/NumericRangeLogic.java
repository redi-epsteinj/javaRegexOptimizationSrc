package regextalk.numericrange;

import java.util.Arrays;

/**
 *
 */
public class NumericRangeLogic implements NumericRangeToBenchmark {

   public static void main(String[] cmd_lineParams) {
      new NumericRangeLogic().runCodeToBeTimed();
   }

   @Override
   public void runCodeToBeTimed() {
      Arrays.stream(getInputs()).forEach(strNum -> {
         int num = Integer.parseInt(strNum);       //Definitely a valid integer
         System.out.print(strNum + ": ");

         if(-2055 <= num && num <= 2055) {
            System.out.println("in range");
         } else {
             System.out.println("not in range");
         }
      });
   }
}
