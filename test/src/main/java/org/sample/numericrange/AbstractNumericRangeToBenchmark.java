package org.sample.numericrange;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;

import java.util.regex.Pattern;

@State(Scope.Thread)
public abstract class AbstractNumericRangeToBenchmark {


    private final Pattern pattern;
    private static final String[] INPUTS = NumericRangeUtil.newInputs();

    public AbstractNumericRangeToBenchmark(String regex) {
        pattern = Pattern.compile(regex);
    }

    public Pattern getPattern() {
        return pattern;
    }

    @Benchmark
    public void testMethod() {
        NumericRangeUtil.runCodeToTest(getPattern(), INPUTS);
    }
}
