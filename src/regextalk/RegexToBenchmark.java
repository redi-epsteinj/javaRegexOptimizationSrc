package regextalk;

import benchmark.ToBenchmark;

public interface RegexToBenchmark extends ToBenchmark {

   String IGNORED_INPUT = "ignored input";

   String[] getInputs();
}
