package tk.leaflame.app.lambda;

import java.util.Comparator;
import java.util.function.Function;
import java.util.function.Predicate;

public class LambdaExample {

    public static void main(String[] args) {
        Comparator<Apple> byColor = Comparator.comparing(Apple::getColor);//(a1, a2) -> a1.getColor().compareTo(a2.getColor())
        Function<String, Integer> flambda = String::length;//s -> s.length()
        Predicate<Apple> p = a -> a.getColor().equals("red");
    }
}
