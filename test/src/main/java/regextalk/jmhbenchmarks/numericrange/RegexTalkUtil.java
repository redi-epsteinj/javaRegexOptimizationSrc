package regextalk.jmhbenchmarks.numericrange;

import static java.lang.String.format;

import java.util.Arrays;
import java.util.regex.Pattern;

public class RegexTalkUtil {

    public static void runCodeToTest(Pattern pattern, String[] inputs) {
        int[] indexElem0 = {0};
        Arrays.stream(inputs).forEach(input -> {
            if (input == null) {
                String message = format("input element %d is null. All inputs: %s", indexElem0[0],
                                        Arrays.toString(inputs));
                //System.out.println(message);
            }

            assert input != null;

            boolean inRange = pattern.matcher(input).matches();
            //System.out.printf("* %s: %satched%n", input, (inRange ? "M" : "NOT m"));
            indexElem0[0]++;
        });
    }
}
