package tk.leaflame.app.defaultmethod;

public class DefaultInAction {

    public static void main(String[] args) {
        A a = () -> 10;
        System.out.println(a.size());
        System.out.println(a.isEmpty());
    }

    private interface A {

        int size();

        default void todo(){}//TODO

        default boolean isEmpty() {
            return size() == 0;
        }
    }
}
