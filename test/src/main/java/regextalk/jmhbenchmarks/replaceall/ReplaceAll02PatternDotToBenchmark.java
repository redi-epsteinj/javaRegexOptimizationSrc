package regextalk.jmhbenchmarks.replaceall;

import static regextalk.jmhbenchmarks.replaceall.ReplaceAllUtil.REGEX;
import static regextalk.jmhbenchmarks.replaceall.ReplaceAllUtil.REPLACE_WITH;

import java.util.regex.Pattern;

//@formatter:off
/**
 java -Djmh.ignoreLock=true -jar target/benchmarks.jar -f 1 -wi 0 -i 2 -v EXTRA ReplaceAll02PatternDotToBenchmark
 */
//@formatter:on
public class ReplaceAll02PatternDotToBenchmark extends AbstractReplaceAllToBenchmark {

    @Override
    String replaceAll(String input) {
        return Pattern.compile(REGEX).matcher(input).replaceAll(REPLACE_WITH);
    }
}
