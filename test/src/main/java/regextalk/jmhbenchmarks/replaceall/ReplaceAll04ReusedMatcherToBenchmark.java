package regextalk.jmhbenchmarks.replaceall;

import static regextalk.jmhbenchmarks.replaceall.ReplaceAllUtil.REGEX;
import static regextalk.jmhbenchmarks.replaceall.ReplaceAllUtil.REPLACE_WITH;

import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

//@formatter:off
/**
 java -Djmh.ignoreLock=true -jar target/benchmarks.jar -f 1 -wi 0 -i 2 -v EXTRA ReplaceAll04ReusedMatcherToBenchmark
 */
//@formatter:on
@State(Scope.Thread)
public class ReplaceAll04ReusedMatcherToBenchmark extends AbstractReplaceAllToBenchmark {


    private Matcher matcher = Pattern.compile(REGEX).matcher("ignored input");

    @Override
    String replaceAll(String input) {
        return matcher.reset(input).replaceAll(REPLACE_WITH);
    }
}
