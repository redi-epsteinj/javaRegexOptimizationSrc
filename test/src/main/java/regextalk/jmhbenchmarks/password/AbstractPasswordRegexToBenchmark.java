package regextalk.jmhbenchmarks.password;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;

import java.util.regex.Pattern;

import regextalk.jmhbenchmarks.numericrange.RegexTalkUtil;

@State(Scope.Thread)
public abstract class AbstractPasswordRegexToBenchmark {

    private final Pattern pattern;

    public AbstractPasswordRegexToBenchmark(String regex) {
        pattern = Pattern.compile(regex);
    }

    public Pattern getPattern() {
        return pattern;
    }

    @Benchmark
    public void testMethod() {
        RegexTalkUtil.runCodeToTest(getPattern(), PasswordUtil.INPUTS);
    }
}
