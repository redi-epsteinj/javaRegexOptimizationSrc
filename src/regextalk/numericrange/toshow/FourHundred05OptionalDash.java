package regextalk.numericrange.toshow;

import static java.util.stream.Collectors.joining;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import regextalk.numericrange.FourHundred01EveryNumberInOrToBenchmark;

public class FourHundred05OptionalDash {

   public static final String[] ONE_TO_400_PIECES =
         new String[]{FourHundred01EveryNumberInOrToBenchmark.ONE_TO_50, FourHundred01EveryNumberInOrToBenchmark.FIFTY1_TO_100,
                      FourHundred01EveryNumberInOrToBenchmark.ONE01_TO_150,
                      FourHundred01EveryNumberInOrToBenchmark.ONE51_TO_200,
                      FourHundred01EveryNumberInOrToBenchmark.TWO01_TO_250,
                      FourHundred01EveryNumberInOrToBenchmark.TWO51_TO_300,
                      FourHundred01EveryNumberInOrToBenchmark.THREE01_TO_350,
                      FourHundred01EveryNumberInOrToBenchmark.THREE51_TO_400};
   public static final String   ONE_TO_400        = Arrays.stream(ONE_TO_400_PIECES).
         collect(joining("|"));


   public static void main(String[] ignored) {
//@formatter:off
      String regex = "" +
         "\\b" +
         "(?:" +
            "(?<!-)0+|" +        //Zero or more "0"-s, as long as not preceded by dash
            "-?" +               //Optional dash, followed by 1..400
            "(?:" +
               ONE_TO_400 +
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

