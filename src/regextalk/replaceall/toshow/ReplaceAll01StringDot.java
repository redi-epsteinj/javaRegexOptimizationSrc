package regextalk.replaceall.toshow;

import java.util.Arrays;

public class ReplaceAll01StringDot {

   public static void main(String[] ignored) {
      
      String regex = "[,./ ]+";            //Replace each instance of: "One or more comma, 
                                           //                           period, up-right slash, 
                                           //                           or space"
      String replaceWith = "---";          //With three dashes

      String[] inputs = newInputs();
      
      Arrays.stream(inputs).forEach(input -> {
         String replaced = input.replaceAll(regex, replaceWith);
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
