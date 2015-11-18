package regextalk.jmhbenchmarks.password;

import benchmark.TaskToBenchmark;

public interface PasswordToBenchmark extends TaskToBenchmark {
   boolean isPasswordValid(String password);
}
