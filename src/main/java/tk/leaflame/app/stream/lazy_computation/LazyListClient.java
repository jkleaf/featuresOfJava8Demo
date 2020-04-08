package tk.leaflame.app.stream.lazy_computation;

/**
 * @author leaflame
 * @date 2020/1/27 16:05
 * indefinite Stream => StackOverflowError
 * 可能有性能问题
 */
public class LazyListClient {

    public static void main(String[] args) {
        LazyList<Integer> numbers = from(2);
        System.out.println(numbers.head() + " " + numbers.tail().head() + " " + numbers.tail().tail().head()); // 2 3 4

        // TODO: 2020/1/27
        LazyList<Integer> numbers2 = from(2); // 2 -> 2 3 5 , 1 -> StackOverflowError , 3 -> 3 4 5 (没有2,不能排除4) , ...
        System.out.println(primes(numbers2).head() + " " + primes(numbers2).tail().head() + " " + primes(numbers2).tail().tail().head());

        LazyList.printAll(primes(from(2))); // 一直运行下去直到栈溢出
    }

    private static LazyList<Integer> from(int n) {
        return new LazyList<>(n, () -> from(n + 1)); // indefinite
    }

    private static MyList<Integer> primes(MyList<Integer> numbers) {
        return new LazyList<>(
                numbers.head(),
                () -> primes(
                        numbers.tail() // 由from计算而得
                                .filter(n -> n % numbers.head() != 0) // 判断质数(n<=>numbers.tail().head())
                )
        );
    }
}
