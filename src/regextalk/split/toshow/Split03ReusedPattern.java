package regextalk.split.toshow;

import java.util.Arrays;
import java.util.regex.Pattern;

import regextalk.split.AbstractSplit;

public class Split03ReusedPattern extends AbstractSplit {

   public Split03ReusedPattern(String regex) {
      super(regex);
   }

   public static void main(String[] cmd_lineParams) {
      new Split03ReusedPattern(REGEX_SPACE).setupRunBreakdown();
      new Split03ReusedPattern(REGEX_2_PLUS).setupRunBreakdown();
   }


   @Override
   public void runCodeToBeTimed() {
      Pattern pattern = Pattern.compile(getRegex());
      Arrays.stream(newInputs()).forEach(input -> {
         System.out.println(input);
         System.out.println(Arrays.toString(
               pattern.split(input)
         ));
      });
   }
}
