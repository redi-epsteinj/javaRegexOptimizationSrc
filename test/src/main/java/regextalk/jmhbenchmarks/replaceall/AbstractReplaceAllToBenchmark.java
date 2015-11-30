package regextalk.jmhbenchmarks.replaceall;

import static regextalk.jmhbenchmarks.replaceall.ReplaceAllUtil.INPUTS;

import org.openjdk.jmh.annotations.Benchmark;

import java.util.Arrays;

public abstract class AbstractReplaceAllToBenchmark {

    abstract String replaceAll(String input);

    @Benchmark
    public void runCodeToBeTimed() {
        Arrays.stream(INPUTS).forEach(input -> {
            //System.out.print("\"" + input + "\"    ->    \"");
            String replacedUnused = replaceAll(input);
            //System.out.println(replaced + "\"");
        });
    }
}
