package regextalk.password;

import benchmark.TaskToBenchmark;

public interface PasswordToBenchmark extends TaskToBenchmark {
   boolean isPasswordValid(String password);
}
