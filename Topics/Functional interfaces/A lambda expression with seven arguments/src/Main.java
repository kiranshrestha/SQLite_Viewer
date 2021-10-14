import java.util.Locale;

class Seven {
    public static SeptenaryStringFunction fun = (s1, s2, s3, s4, s5, s6, s7) ->
            s1.toUpperCase(Locale.ROOT) +
            s2.toUpperCase(Locale.ROOT) +
            s3.toUpperCase(Locale.ROOT) +
            s4.toUpperCase(Locale.ROOT) +
            s5.toUpperCase(Locale.ROOT) +
            s6.toUpperCase(Locale.ROOT) +
            s7.toUpperCase(Locale.ROOT);
}

@FunctionalInterface
interface SeptenaryStringFunction {
    String apply(String s1, String s2, String s3, String s4, String s5, String s6, String s7);
}