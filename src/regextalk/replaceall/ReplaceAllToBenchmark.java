package regextalk.replaceall;

import benchmark.TaskToBenchmark;

public interface ReplaceAllToBenchmark extends TaskToBenchmark {

   default String[] getInputs() {
      return new String[]{
            "",
            ",./ /,.  ./,/.",
            "One, two, three. four/five six",
            "Hello ,./, ./,. /, ./, ./, ./ ,there, ,./ ,./, ./, .!"
      };
   }

   static final String FIND_WHAT_REGEX = "[,./ ]+";
   static final String REPLACE_WITH = "---";
}
