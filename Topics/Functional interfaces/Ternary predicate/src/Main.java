
class Predicate {

    public static final TernaryIntPredicate allValuesAreDifferentPredicate = (x, y, z) -> x != y && x != z && y != z;

    @FunctionalInterface
    public interface TernaryIntPredicate {
        boolean test(int a, int b, int c);
    }
}
