package regextalk.password;

import java.util.Arrays;
import java.util.Objects;

public class PasswordToBenchmarkComposer {

   public String[] getInputs() {
      return new String[]{
            "",                     //bad (bad rules, bad length)
            "abcdefghij",           //bad (bad rules, good length)
            "abc123",               //bad (good rules, bad length)
            "abc123abc",            //3 rules: bad, 2 rules: good
            "a1$A",                 //bad (good rules, bad length)
            "abc123$%^ABC",         //good
            "abcABC123$&*",         //good
            "abc ABC123$#$"         //bad (whitespace)
      };
   }

   public void runCodeToBeTimed(PasswordToBenchmark to_benchmark) {
      Objects.requireNonNull(to_benchmark, "to_benchmark");

      System.out.println(Arrays.toString(getInputs()));
      Arrays.stream(getInputs()).forEach(input -> {
         System.out.print(input + ": ");
         boolean isValid = to_benchmark.isPasswordValid(input);
         System.out.println(isValid);
      });
   }

}
