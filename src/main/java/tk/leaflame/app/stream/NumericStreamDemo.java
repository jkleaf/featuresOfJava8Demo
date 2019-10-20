package tk.leaflame.app.stream;

import java.util.stream.IntStream;
import java.util.stream.Stream;

public class NumericStreamDemo {

    private static Stream<Integer> testStream() {
        return Stream.of(1, 2, 3, 3, 4, 5, 6, 7, 7, 7, 10);
    }

    public static void main(String[] args) {
        testStream().filter(i -> i > 3).reduce(Integer::sum).ifPresent(System.out::println);
        /*concrete specific(int) to reduce memory*/
        System.out.println(testStream().mapToInt(i -> i).filter(i -> i > 3).sum());
        int a=9;
        IntStream.rangeClosed(1,100).filter(b->Math.sqrt(a*a+b*b)%1==0)
            .boxed().map(b->new int[]{a,b, (int) Math.sqrt(a*a+b*b)})//mapToObj
                .forEach(arr->System.out.println("a="+arr[0]+",b="+arr[1]+",c="+arr[2]));
    }
}
