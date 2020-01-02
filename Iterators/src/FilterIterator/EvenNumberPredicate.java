package Ex2;

public class EvenNumberPredicate<T extends Number> implements Predicate<T> {
    @Override
    public boolean accept(T args) {
        return (args.doubleValue() % 2 == 0);
    }
}
