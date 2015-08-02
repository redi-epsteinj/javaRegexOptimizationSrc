package regextalk.password;

import java.util.Arrays;

import benchmark.ToBenchmark;

/**
 *
 */
public interface PasswordToBenchmark extends ToBenchmark {

   @Override
   default void runCodeToBeTimed() {
      Arrays.stream(getInputs()).forEach(input -> {
         System.out.print(input + ": ");
         boolean isValid = isPasswordValid(input);
         System.out.println(isValid);
      });
   }

   boolean isPasswordValid(String to_test);

   default String[] getInputs() {
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

}
