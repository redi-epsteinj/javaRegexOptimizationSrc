package regextalk.replaceall.toshow;

      import java.util.Arrays;
      import java.util.regex.Pattern;

public class ReplaceAll03ReusedPattern {

   public static void main(String[] ignored) {

      String regex = "[,./ ]+";            //Replace each instance of: "One or more comma, 
                                           //                           period, up-right slash, 
                                           //                           or space"
      String replaceWith = "---";          //With three dashes

      String[] inputs = newInputs();
      
      Pattern pattern = Pattern.compile(regex);

      Arrays.stream(inputs).forEach(input -> {
         String replaced = pattern.matcher(input).replaceAll(replaceWith);
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
