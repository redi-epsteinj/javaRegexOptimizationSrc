package regextalk.split;

import regextalk.ToBenchmark;

public interface SplitToBenchmark extends ToBenchmark {
   public static final String REGEX = "[,./ ]+";

   default String[] getInputs() {
      return new String[] {
         "",
         ",./ ,. /. ,./. ",
         "One, two, three. four/five six",
         "The snow glows white on the mountain tonight",
         "abcde",
         "a b c d e"
      };
   }
}

