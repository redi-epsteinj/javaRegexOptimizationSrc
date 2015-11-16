package regextalk.numericrange;

import java.util.Arrays;
import java.util.List;

import benchmark.TaskToBenchmark;

public class FourHundred12LogicToBenchmark implements TaskToBenchmark {

    public static void main(String[] ignored) {
        new FourHundred12LogicToBenchmark().setupRunBreakdown();
    }

    private int[] inputs;

    @Override
    public void setup() {
        List<Integer> intList = FourHundred01EveryNumberInOrToBenchmark.newIntInputs();
        inputs = intList.stream().mapToInt(i -> i).toArray();
    }

    @Override
    public void runCodeToBeTimed() {
        Arrays.stream(inputs).forEach(input -> {
            boolean inRange = (-400 <= input && input <= 400);
            System.out.printf("\"%d\" is %sin range.%n", input, (inRange ? "" : "*NOT* "));
        });
    }
}
