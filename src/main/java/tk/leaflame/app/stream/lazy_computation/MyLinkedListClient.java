package tk.leaflame.app.stream.lazy_computation;

/**
 * @author leaflame
 * @date 2020/1/27 15:19
 */
public class MyLinkedListClient {

    public static void main(String[] args) {
        MyLinkedList<Integer> list = new MyLinkedList<>(5, new MyLinkedList<>(10, new Empty<>()));
        System.out.println(list.head());
        System.out.println(list.tail().head());
    }
}
