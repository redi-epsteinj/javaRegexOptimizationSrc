package regextalk.jmhbenchmarks.replaceall;

import static regextalk.jmhbenchmarks.replaceall.ReplaceAllUtil.REGEX;
import static regextalk.jmhbenchmarks.replaceall.ReplaceAllUtil.REPLACE_WITH;

import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;

import java.util.regex.Pattern;

//@formatter:off
/**
 java -Djmh.ignoreLock=true -jar target/benchmarks.jar -f 1 -wi 0 -i 2 -v EXTRA ReplaceAll03ReusedPatternToBenchmark
 */
//@formatter:on
@State(Scope.Thread)
public class ReplaceAll03ReusedPatternToBenchmark extends AbstractReplaceAllToBenchmark {


    private Pattern pattern = Pattern.compile(REGEX);

    @Override
    String replaceAll(String input) {
        return pattern.matcher(input).replaceAll(REPLACE_WITH);
    }
}
