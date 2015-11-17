package org.sample.numericrange;

//@formatter:off
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;

import java.util.List;

/**
 java -Djmh.ignoreLock=true -jar target/benchmarks.jar -f 1 -wi 0 -i 2 -v EXTRA FourHundred12LogicToBenchmark
 */
//@formatter:on
@State(Scope.Thread)
public class FourHundred12LogicToBenchmark {

    private static final List<Integer> INPUTS = NumericRangeUtil.newIntInputs();

    @Benchmark
    public void runCodeToBeTimed() {
        INPUTS.forEach(input -> {
            boolean inRange = (-400 <= input && input <= 400);
            System.out.printf("\"%d\" is %sin range.%n", input, (inRange ? "" : "*NOT* "));
        });
    }
}
