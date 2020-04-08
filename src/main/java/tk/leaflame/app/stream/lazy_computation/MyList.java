package tk.leaflame.app.stream.lazy_computation;

import java.util.function.Predicate;

/**
 * @author leaflame
 * @date 2020/1/27 14:20
 */
interface MyList<T> {

    T head();

    MyList<T> tail();

    MyList<T> filter(Predicate<T> p);

    default boolean isEmpty() { // default return true
        return true;
    }
}
