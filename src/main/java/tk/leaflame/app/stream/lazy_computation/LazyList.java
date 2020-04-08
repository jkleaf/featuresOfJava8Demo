package tk.leaflame.app.stream.lazy_computation;

import java.util.function.Predicate;
import java.util.function.Supplier;

/**
 * @author leaflame
 * @date 2020/1/27 15:24
 */
public class LazyList<T> implements MyList<T> {

    private final T head;

    private final Supplier<MyList<T>> tail; // void -> T

    public LazyList(T head, Supplier<MyList<T>> tail) {
        this.head = head;
        this.tail = tail;
    }

    @Override
    public T head() {
        return head;
    }

    @Override
    public MyList<T> tail() {
        return tail.get(); // use Supplier to support lazy ...
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    /**
     * @param p
     * @return 定义延迟过滤器
     */
    @Override
    public MyList<T> filter(Predicate<T> p) {
        return isEmpty() ?
                this : // 返回一个空对象（等价于返回一个新的Empty<>())
                p.test(head()) ? // 判断head是否满足条件(是否为质数)
                        new LazyList<>(head(), () -> tail().filter(p)) : // 列表包含head
                        tail().filter(p); // 列表不包含head
    }

    static <T> void printAll(MyList<T> list) {
        if (list.isEmpty())
            return;
        System.out.println(list.head());
        printAll(list.tail()); // recursive
    }
}
