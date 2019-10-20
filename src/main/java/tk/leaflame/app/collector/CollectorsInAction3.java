package tk.leaflame.app.collector;

import tk.leaflame.app.stream.Dish;

import java.util.*;
import java.util.function.BinaryOperator;
import java.util.stream.Collectors;

import static tk.leaflame.app.collector.CollectorsInAction.*;

public class CollectorsInAction3 {

    public static void main(String[] args) {
        testPartitioningByWithPredicate();
        testPartitioningByWithPredicateAndCollector();
        testReducingBinaryOperator();
        testSummarizingInt();
    }

    private static void testPartitioningByWithPredicate(){
        Map<Boolean, List<Dish>> map = menu.stream().collect(Collectors.partitioningBy(Dish::isVegetarian));
        Optional.ofNullable(map).ifPresent(System.out::println);
    }

    private static void testPartitioningByWithPredicateAndCollector() {
        Map<Boolean, Double> map = menu.stream()
                .collect(Collectors.partitioningBy(Dish::isVegetarian,Collectors.averagingInt(Dish::getCalories)));
        Optional.ofNullable(map).ifPresent(System.out::println);
    }

    private static void testReducingBinaryOperator(){
        menu.stream().collect(
                Collectors.reducing(BinaryOperator.maxBy(Comparator.comparing(Dish::getCalories)))
        ).ifPresent(System.out::println);
    }

    private static void testSummarizingInt(){
        IntSummaryStatistics result = menu.stream().collect(Collectors.summarizingInt(Dish::getCalories));
        Optional.ofNullable(result).ifPresent(System.out::println);
    }
}
