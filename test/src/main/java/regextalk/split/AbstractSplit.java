package regextalk.split;

import benchmark.TaskToBenchmark;

public abstract class AbstractSplit implements TaskToBenchmark {

   public static final String REGEX_2_PLUS = "[,./ ]+";
   public static final String REGEX_SPACE = " ";
   private final String regex;

   protected AbstractSplit(String regex) {
      this.regex = regex;
   }

   public String getRegex() {
      return regex;
   }

   public String[] newInputs() {
      return new String[]{
            "",
            ",./ ,. /. ,./. ",
            "One, two, three. four/five six",
            "The snow glows white on the mountain tonight",
            "abcde",
            "a b c d--e"
      };
   }
}

