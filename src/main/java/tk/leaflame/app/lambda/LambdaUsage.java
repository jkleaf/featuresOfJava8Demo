package tk.leaflame.app.lambda;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.function.*;

public class LambdaUsage {

    private static <T> List<T> filter(List<T> list, Predicate<T> predicate) {
        List<T> res = new ArrayList<>();
        for (T t : list) {
            if (predicate.test(t)) {
                res.add(t);
            }
        }
        return res;
    }

    private static List<Apple> filterByBiPredicate(List<Apple> list, BiPredicate<String, Integer> biPredicate) {
        List<Apple> res = new ArrayList<>();
        for (Apple apple : list) {
            if (biPredicate.test(apple.getColor(), apple.getWeight())) {
                res.add(apple);
            }
        }
        return res;
    }

    private static void simpleTestConsumer(List<Apple> list, Consumer<Apple> consumer) {
        for (Apple apple : list) {
            consumer.accept(apple);
        }
    }

    private static void simpleTestBiConsumer(String s, List<Apple> list, BiConsumer<Apple, String> consumer) {
        for (Apple apple : list) {
            consumer.accept(apple, s);
        }
    }

    private static Apple simpleTestFunction(Apple apple, Function<Apple, Apple> function) {
        return function.apply(apple);
    }

    private static Apple simpleTestBiFunction(Apple apple, BiFunction<String, Integer, Apple> function) {
        return function.apply(apple.getColor(), apple.getWeight());
    }

    public static void main(String[] args) {
        List<Apple> apples = Arrays.asList(new Apple("red", 212),
                new Apple("green", 211), new Apple("Green", 233));
        /*Predicate*/
        System.out.println("Predicate:");
        //Method inference
        List<Apple> greenApples = filter(apples, a -> a.getColor().equalsIgnoreCase("green"));
        System.out.println(greenApples);
        //filterByBiPredicate
        System.out.println(filterByBiPredicate(apples, (s, w) -> s.equalsIgnoreCase("green") && w > 211));
        /*Consumer*/
        System.out.println("Consumer:");
        simpleTestConsumer(apples, System.out::println);//a-> System.out.println(a)
        simpleTestBiConsumer(new SimpleDateFormat("yyyyMMdd").format(new Date()), apples, (a, s) -> System.out.println(s + "_" + a.getColor() + ":" + a.getWeight()));
        /*Function*/
        System.out.println(simpleTestFunction(new Apple("yellow", 456), a -> a));
        System.out.println(simpleTestBiFunction(new Apple("green",123),(c,w)->new Apple(c.toUpperCase(),w*10)));
        /*Supplier*/
        Supplier<Apple> supplier= Apple::new;//()->new Apple()
        System.out.println(supplier.get());

//        int i=0;
//        Runnable runnable=()-> System.out.println(i);
//        i++;// variable should be final!
    }
}
