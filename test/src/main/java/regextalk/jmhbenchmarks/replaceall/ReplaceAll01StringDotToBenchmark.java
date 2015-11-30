package regextalk.jmhbenchmarks.replaceall;

import static regextalk.jmhbenchmarks.replaceall.ReplaceAllUtil.*;//@formatter:off
/**
 java -Djmh.ignoreLock=true -jar target/benchmarks.jar -f 1 -wi 0 -i 2 -v EXTRA ReplaceAll01StringDotToBenchmark
 */
//@formatter:on
public class ReplaceAll01StringDotToBenchmark extends AbstractReplaceAllToBenchmark {

    @Override
    String replaceAll(String input) {
        return input.replaceAll(REGEX, REPLACE_WITH);
    }
}
