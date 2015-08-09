package regextalk.numericrange.intro;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FifteenOnly15To16 {

   //15 only
   private static final String   FIFTEEN_ONLY   = "\\b15\\b";
   private static final String[] FIFTEEN_INPUTS = new String[]{"14", "15", "16", "81586"};

   //15 or 16
   private static final String[] FIFTEEN_OR_16_INPUTS = new String[]{"14", "15", "16", "17",
                                                                     "81586"};
   private static final String   FIFTEEN_OR_16        = "\\b(?:15|16)\\b";
   private static final String   ONE_THEN_5_OR_6      = "\\b1(?:5|6)\\b";
   private static final String   ONE_THEN_5_THROUGH_6 = "\\b1[5-6]\\b";

   //0-12
   private static final String[] ZERO_TO_12_INPUTS         = new String[]{"-13", "-12", "-11",
                                                                          "-10", "-9", "-8",
                                                                          "-7", "-6", "-5", "-4",
                                                                          "-3", "-2", "-1", "0",
                                                                          "1", "2", "3", "4", "5",
                                                                          "6", "7", "8", "9",
                                                                          "10", "11", "12", "13"};
   private static final String   ZERO_TO_12_WITH_D         = "\\b(\\d|1[0-2])\\b";
   private static final String   ZERO_TO_12_WITH_0T9       = "\\b([0-9]|1[0-2])\\b";
   private static final String   ZERO_TO_12_NOT_AFTER_DASH = "(?<!-)" + ZERO_TO_12_WITH_0T9;

   public static void main(String[] ignored) {
      go(FIFTEEN_ONLY, FIFTEEN_INPUTS);
      go(FIFTEEN_OR_16, FIFTEEN_OR_16_INPUTS);
      go(ONE_THEN_5_OR_6, FIFTEEN_OR_16_INPUTS);
      go(ONE_THEN_5_THROUGH_6, FIFTEEN_OR_16_INPUTS);
      go(ZERO_TO_12_WITH_D, ZERO_TO_12_INPUTS);
      go(ZERO_TO_12_WITH_0T9, ZERO_TO_12_INPUTS);
      go(ZERO_TO_12_NOT_AFTER_DASH, ZERO_TO_12_INPUTS);
   }

   private static void go(final String regex, final String[] inputs) {
      System.out.println(regex);
      final Matcher matcher = Pattern.compile(regex).matcher("ignored input");
      Arrays.stream(inputs).forEach(input -> {
         boolean matched = matcher.reset(input).matches();
         System.out.printf("* %s: %satched%n", input, (matched ? "M" : "Not m"));
      });
   }
}
