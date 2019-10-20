package tk.leaflame.app.stream;

import java.util.stream.Stream;

public class StreamReduceDemo {

    private static Stream<Integer> testStream() {
        return Stream.of(1, 2, 3, 3, 4, 5, 6, 7, 7, 7, 10);
    }

    public static void main(String[] args) {
        /*association accumulation*/
        Integer result = testStream().reduce(0, Integer::sum);//(i,j)->i+j return T
        System.out.println(result);
        testStream().reduce(Integer::sum).ifPresent(System.out::println);//no identity return Optional;
        testStream().reduce((i, j) -> i > j ? i : j).ifPresent(System.out::println);
        testStream().reduce(Integer::min).ifPresent(System.out::println);
        System.out.println(testStream().filter(i -> i % 2 != 0).reduce(1, (i, j) -> i * j));
    }
}
