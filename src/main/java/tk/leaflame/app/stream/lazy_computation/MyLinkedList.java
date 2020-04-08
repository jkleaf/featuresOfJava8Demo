package tk.leaflame.app.stream.lazy_computation;

import java.util.function.Predicate;

/**
 * @author leaflame
 * @date 2020/1/27 15:15
 */
public class MyLinkedList<T> implements MyList<T> {

    private final T head;

    private final MyList<T> tail;

    public MyLinkedList(T head, MyList<T> tail) {
        this.head = head;
        this.tail = tail;
    }

    @Override
    public T head() {
        return head;
    }

    @Override
    public MyList<T> tail() {
        return tail;
    }

    @Override
    public MyList<T> filter(Predicate<T> p) {
        return null;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }
}
