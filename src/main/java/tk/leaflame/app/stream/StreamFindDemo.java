package tk.leaflame.app.stream;

import java.util.Optional;
import java.util.stream.Stream;

public class StreamFindDemo {

    private static Stream<Integer> testStream(){
        return Stream.of(1, 2, 3, 3, 4, 5, 6, 7, 7, 7, 10);
    }

    public static void main(String[] args) {
        Optional<Integer> optional1 = testStream().filter(i->i%2==0).findAny();
        System.out.println(optional1.get());
        System.out.println(optional1.orElse(-1));
        optional1=testStream().filter(i->i>10).findFirst();
        optional1.ifPresent(System.out::println);
    }
}
