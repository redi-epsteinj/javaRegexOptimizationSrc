package regextalk.jmhbenchmarks.password;

//@formatter:off
/**
 java -Djmh.ignoreLock=true -jar target/benchmarks.jar -f 1 -wi 0 -i 2 -v EXTRA Password03LogicThreeRulesToBenchmark
 */
//@formatter:on
public class Password03LogicThreeRulesToBenchmark extends AbstractPasswordLogic {

    public Password03LogicThreeRulesToBenchmark() {
        super(3);
    }
}
