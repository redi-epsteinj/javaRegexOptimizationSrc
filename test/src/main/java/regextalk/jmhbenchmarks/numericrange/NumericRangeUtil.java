package regextalk.jmhbenchmarks.numericrange;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class NumericRangeUtil {

    public static String[] newInputs() {
        List<String> strList = newIntInputs().stream().map(Object::toString)
                .collect(Collectors.toList());

        //Now a fraction the size of the original, with random elements from it.

        strList.add(0, "-401");
        strList.addAll(Arrays.asList("401", "Jimmy", "u390x", "-0", "0000", "   (45)   "));

        return strList.toArray(new String[strList.size()]);
    }

    public static List<Integer> newIntInputs() {
        //All numbers, including some non-valid
        List<Integer> intList = IntStream.range(-450, 450).boxed().collect(Collectors.toList());

        Collections.shuffle(intList);

        final int size = intList.size();
        intList.subList(size / 10, size).clear();

        Collections.sort(intList);

        //Now a tenth of the original size, with random elements from it.

        return intList.stream().collect(Collectors.toList());
    }
}
