package regextalk.split;

import java.util.Arrays;

public class StringDotSplit extends AbstractSplitToBenchmark {

   public StringDotSplit(String regex) {
      super(regex);
   }

   public static void main(String[] cmd_lineParams) {
      new StringDotSplit(REGEX_SPACE).setupRunBreakdown();
      new StringDotSplit(REGEX_2_PLUS).setupRunBreakdown();
   }

   @Override
   public void runCodeToBeTimed() {
      Arrays.stream(getInputs()).forEach(input -> {
         System.out.println(input);
         System.out.println("\t" + Arrays.toString(
               input.split(getRegex(), -1)
         ));
      });
   }
}
