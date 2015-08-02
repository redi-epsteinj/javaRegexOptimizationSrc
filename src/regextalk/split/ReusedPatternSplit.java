package regextalk.split;

   import java.util.Arrays;
   import java.util.regex.Pattern;

public class ReusedPatternSplit implements SplitToBenchmark {

   public static void main(String[] cmd_lineParams) {
      new ReusedPatternSplit().runCodeToBeTimed();
   }

   @Override
   public void runCodeToBeTimed() {
      Pattern pattern = Pattern.compile(REGEX);
      Arrays.stream(getInputs()).forEach(input -> {
         System.out.println(input);
         System.out.println(Arrays.toString(
            pattern.split(input)
         ));
      });
   }
}
