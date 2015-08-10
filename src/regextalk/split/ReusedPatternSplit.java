package regextalk.split;

import java.util.Arrays;
import java.util.regex.Pattern;

public class ReusedPatternSplit extends AbstractSplitToBenchmark {

   public ReusedPatternSplit(String regex) {
      super(regex);
   }

   public static void main(String[] cmd_lineParams) {
      new ReusedPatternSplit(REGEX_SPACE).setupRunBreakdown();
      new ReusedPatternSplit(REGEX_2_PLUS).setupRunBreakdown();
   }


   @Override
   public void runCodeToBeTimed() {
      Pattern pattern = Pattern.compile(getRegex());
      Arrays.stream(getInputs()).forEach(input -> {
         System.out.println(input);
         System.out.println(Arrays.toString(
               pattern.split(input)
         ));
      });
   }
}
