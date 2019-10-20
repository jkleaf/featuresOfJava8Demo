package tk.leaflame.app.lambda;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FilterApple {

    private List<Apple> list;

    @FunctionalInterface//only one abstract method
    public interface AppleFilter {
        boolean filter(Apple apple);
        default void a(){}
        static void b(){}
    }

    private static List<Apple> findApple(List<Apple> apples, AppleFilter appleFilter) {
        List<Apple> list = new ArrayList<>();
        for (Apple apple : apples) {
            if (appleFilter.filter(apple)) {
                list.add(apple);
            }
        }
        return list;
    }

    public static class YellowLess300WeightFilter implements AppleFilter {

        @Override
        public boolean filter(Apple apple) {
            return apple.getColor().equals("yellow") && apple.getWeight() < 300;
        }

    }

    public static void main(String[] args) {
        List<Apple> list = Arrays.asList(new Apple("green", 153), new Apple("green", 100),
                new Apple("red", 322), new Apple("yellow", 233));
        List<Apple> yellowApples = findApple(list, new YellowLess300WeightFilter());
        System.out.println(yellowApples);

        //greenApplesWithLambda conciser
        List<Apple> greenApplesWithLambda = findApple(list, apple ->
                apple.getColor().equals("green") && apple.getWeight() >= 100);
        System.out.println(greenApplesWithLambda);

        //Thread
        new Thread(() -> System.out.println(Thread.currentThread().getName())).start();
    }

}
