package regextalk.jmhbenchmarks.replaceall;

public class ReplaceAllUtil {

    static final String[] INPUTS = {
                "",
                ",./ /,.  ./,/.",
                "One, two, three. four/five six",
                "Hello ,./, ./,. /, ./, ./, ./ ,there, ,./ ,./, ./, .!"
        };

    public static final String REGEX = "[,./ ]+";
    public static final String REPLACE_WITH = "---";
}
