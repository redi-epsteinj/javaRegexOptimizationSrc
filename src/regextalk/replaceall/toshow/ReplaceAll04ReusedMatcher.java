package regextalk.replaceall.toshow;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ReplaceAll04ReusedMatcher {

      public static void main(String[] ignored) {

         String regex = "[,./ ]+";            //Replace each instance of: "One or more comma, 
                                              //                           period, up-right slash, 
                                              //                           or space"
         String replaceWith = "---";          //With three dashes

         String[] inputs = newInputs();

         Matcher matcher = Pattern.compile(regex).matcher("ignored input");

         Arrays.stream(inputs).forEach(input -> {
            String replaced = matcher.reset(input).replaceAll(replaceWith);
            System.out.printf("\"%s\"    ->    \"%s\"%n", input, replaced);
         });
      }

      public static String[] newInputs() {
         return new String[]{
               "",
               ",./ /,.  ./,/.",
               "One, two, three. four/five six",
               "Hello ,./, ./,. /, ./, ./, ./ ,there, ,./ ,./, ./, .!"
         };
      }
   }
