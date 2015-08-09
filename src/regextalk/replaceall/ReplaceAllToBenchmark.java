package regextalk.replaceall;

import regextalk.RegexToBenchmark;

public interface ReplaceAllToBenchmark extends RegexToBenchmark {

   default String[] getInputs() {
      return new String[]{
            "",
            ",./ /,.  ./,/.",
            "One, two, three. four/five six",
            "Hello ,./, ./,. /, ./, ./, ./ ,there, ,./ ,./, ./, .!"
      };
   }

   default String getRegex() {
      return "[,./ ]+";
   }

   static final String REPLACE_WITH = "---";
}
