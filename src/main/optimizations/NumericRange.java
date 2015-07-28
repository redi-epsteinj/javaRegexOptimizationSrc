package main.optimizations;

import java.io.PrintStream;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 */
public class NumericRange {

   public static final String NEG_TO_POS_2055_REGEX =
      "(" +            //Capture group for the entire number
         "-?\\b" +            //Optional dash, followed by a word (number) boundary
         "(?:20" +            //Followed by "20", which is followed by one of
            "(?:5[0-5]" +        //50 through 55
            "|" +                      //or
            "[0-4][0-9])" +      //00 through 49
         "|" +                                         //or
            "1[0-9]{3}" +        //a one followed by any three digits
         "|" +                                         //or
            "[1-9][0-9]{0,2}" +  //1-9 followed by 0 through 2 of any digit
         "|" +                                         //or
            "(?<!-)0+" +         //one-or-more zeros *not* preceded by a dash
         ")" +            //end "or" non-capture group
      ")\\b";      //End number capture group, followed by a word-bound

   public static void main(String[] cmd_lineParams) {
      String[] stringNumsToTest = new String[]{"-2056", "-2055", "-10", "0", "10", "2055", "2056"};

      new NumericRange().goAllNumsUnanchored(System.out, stringNumsToTest);
      new NumericRange().goAllNumsAnchored(System.out, stringNumsToTest);
   }

   public void goAllNumsUnanchored(PrintStream out, String[] str_nums) {
      out.println("Unanchored");

      Matcher matcher = Pattern.compile(NEG_TO_POS_2055_REGEX).matcher("ignored input");

      goAllNums(out, matcher, str_nums);
   }

   public void goAllNumsAnchored(PrintStream out, String[] str_nums) {
      out.println("Anchored");

      Matcher matcher = Pattern.compile("^" + NEG_TO_POS_2055_REGEX + "$").matcher("ignored input");

      goAllNums(out, matcher, str_nums);
   }

   private void goAllNums(PrintStream out, Matcher matcher, String[] str_nums) {
      for (String strNum: str_nums) {
         //matches() is the same as find() with ^...$
         boolean matches = matcher.reset(strNum).find();
         out.println(strNum + " is " +
                     (matches ? "" : "not ") +
                            "in range");
      }
   }
}
