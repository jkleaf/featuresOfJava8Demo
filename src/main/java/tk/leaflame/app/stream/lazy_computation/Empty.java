package tk.leaflame.app.stream.lazy_computation;

import java.util.function.Predicate;

/**
 * @author leaflame
 * @date 2020/1/27 15:18
 */
class Empty<T> implements MyList<T> {

    @Override
    public T head() {
        throw new UnsupportedOperationException();
    }

    @Override
    public MyList<T> tail() {
        throw new UnsupportedOperationException();
    }

    @Override
    public MyList<T> filter(Predicate<T> p) {
        throw new UnsupportedOperationException();
    }
}
