package regextalk;

import benchmark.TaskToBenchmark;

public interface RegexToBenchmark extends TaskToBenchmark {
   String IGNORED_INPUT = "ignored input";
   String getRegex();
   String[] getInputs();
}
