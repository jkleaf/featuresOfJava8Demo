package tk.leaflame.app.lambda;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;

public class MethodInferenceDemo {

    private static <T> void useConsumer(Consumer<T> consumer, T t) {
        consumer.accept(t);
        consumer.accept(t);
    }

    public static void main(String[] args) {
        useConsumer(System.out::println, "Hello");
        List<Apple> list = Arrays.asList(new Apple("red", 212),
                new Apple("green", 211), new Apple("Green", 233));
        list.sort(Comparator.comparing(Apple::getColor));
        System.out.println(list);
        list.forEach(System.out::println);

        Function<String, Integer> function = Integer::parseInt;
        System.out.println(function.apply("123"));

        BiFunction<String, Integer, Character> bif = String::charAt;
        Character c = bif.apply("hello", 3);
        System.out.println(c);

        String s = "hello";
        Function<Integer, Character> f = s::charAt;
        System.out.println(f.apply(4));

        BiFunction<String, Integer, Apple> bifApple = Apple::new;//Constructor is a special method
        System.out.println(bifApple.apply("purple",600));//two params

        ThreeParamsFunction<String, Integer, String, ComplexApple> thf = ComplexApple::new;
        System.out.println(thf.apply("purple",400,"UK"));//three params
    }
}
