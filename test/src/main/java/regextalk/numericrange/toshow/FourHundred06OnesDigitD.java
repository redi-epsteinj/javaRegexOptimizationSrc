package regextalk.numericrange.toshow;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FourHundred06OnesDigitD {

   public static void main(String[] ignored) {
//@formatter:off
      String regex = "" +
         "\\b" +
         "(?:" +
            "(?<!-)0+|" +        //Zero or more "0"-s, as long as not preceded by dash
            "-?" +               //Optional dash, followed by 1..400
            "(?:" +
               "\\d|1\\d|2\\d|3\\d|4\\d|5\\d|6\\d|7\\d|8\\d|9\\d|" +
               "10\\d11\\d|12\\d|13\\d|14\\d|15\\d|16\\d|17\\d|18\\d|19\\d|" +
               "20\\d21\\d|22\\d|23\\d|24\\d|25\\d|26\\d|27\\d28\\d|29\\d|" +
               "30\\d|31\\d|32\\d|33\\d|34\\d|35\\d|36\\d|37\\d|38\\d|39\\d|" +
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
