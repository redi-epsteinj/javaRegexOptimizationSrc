package regextalk.numericrange.toshow;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FourHundred09HundredsDigitTo0to9 {
   public static void main(String[] ignored) {
//@formatter:off
      String regex = "" +
         "\\b" +
         "(?:" +
            "(?<!-)0+|" +        //Zero or more "0"-s, as long as not preceded by dash
            "-?" +               //Optional dash, followed by 1..400
            "(?:" +
               "[1-9]|" +
               "[1-9][0-9]|" +
               "[1-3][0-9][0-9]|" +
               "400" +
            ")" +
         ")\\b";
//@formatter:on

      System.out.println(regex);

      String[] inputs = FourHundred01EveryNumberInOr.newInputs();

      Matcher matcher = Pattern.compile(regex).matcher("ignored input");

      Arrays.stream(inputs).forEach(input -> {

         boolean matches = matcher.reset(input).find();

         System.out.printf("\"%s\" is %sin range.%n", input, (matches ? "" : "*NOT* "));
      });
   }
}
