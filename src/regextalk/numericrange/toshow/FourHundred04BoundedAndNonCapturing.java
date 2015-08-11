package regextalk.numericrange.toshow;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import regextalk.numericrange.FourHundred01EveryNumberInOrToBenchmark;

public class FourHundred04BoundedAndNonCapturing {

   public static void main(String[] ignored) {
      String regex = "\\b(?:" + FourHundred01EveryNumberInOrToBenchmark.NEG_400_TO_400 + ")\\b";
      System.out.println(regex);

      String[] inputs = FourHundred01EveryNumberInOr.newInputs();

      Matcher matcher = Pattern.compile(regex).matcher("ignored input");

      Arrays.stream(inputs).forEach(input -> {

         boolean matches = matcher.reset(input).matches();

         System.out.printf("\"%s\" is %sin range.%n", input, (matches ? "" : "*NOT* "));
      });
   }
}
