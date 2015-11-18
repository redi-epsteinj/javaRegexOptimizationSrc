package regextalk.jmhbenchmarks.password;

import java.util.Arrays;
import java.util.Objects;

public class PasswordUtil {

    protected static final String[] INPUTS = new String[]{
            "",                     //bad (bad rules, bad length)
            "abcdefghij",           //bad (bad rules, good length)
            "abc123",               //bad (good rules, bad length)
            "abc123abc",            //3 rules: bad, 2 rules: good
            "a1$A",                 //bad (good rules, bad length)
            "abc123$%^ABC",         //good
            "abcABC123$&*",         //good
            "abc ABC123$#$"         //bad (whitespace)
      };

   public static void runCodeToBeTimed(PasswordToBenchmark toBenchmark) {
      Objects.requireNonNull(toBenchmark, "toBenchmark");

      Arrays.stream(INPUTS).forEach(input -> {
         //System.out.print(input + ": ");
         boolean isValid = toBenchmark.isPasswordValid(input);
         //System.out.println(isValid);
      });
   }
}
