package regextalk.numericrange.toshow;

      import java.util.Arrays;
      import java.util.regex.Matcher;
      import java.util.regex.Pattern;

public class 
      FourHundred07OnesDigit0to9 {

   public static void main(String[] ignored) {
//@formatter:off
      String regex = "" +
         "\\b" +
         "(?:" +
            "(?<!-)0+|" +        //Zero or more "0"-s, as long as not preceded by dash
            "-?" +               //Optional dash, followed by 1..400
            "(?:" +
               "[1-9]|1[0-9]|2[0-9]|3[0-9]|4[0-9]|5[0-9]|6[0-9]|7[0-9]|8[0-9]|9[0-9]|" +
               "10[0-9]11[0-9]|12[0-9]|13[0-9]|14[0-9]|15[0-9]|16[0-9]|17[0-9]|18[0-9]|19[0-9]|" +
               "20[0-9]21[0-9]|22[0-9]|23[0-9]|24[0-9]|25[0-9]|26[0-9]|27[0-9]28[0-9]|29[0-9]|" +
               "30[0-9]|31[0-9]|32[0-9]|33[0-9]|34[0-9]|35[0-9]|36[0-9]|37[0-9]|38[0-9]|39[0-9]|" +
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
