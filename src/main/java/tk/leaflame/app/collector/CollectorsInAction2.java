package tk.leaflame.app.collector;

import tk.leaflame.app.stream.Dish;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.stream.Collectors;

import static tk.leaflame.app.collector.CollectorsInAction.*;

public class CollectorsInAction2 {

    public static void main(String[] args) {
        testGroupingByConcurrent();
        testGroupingByConcurrentWithFunctionAndCollector();
        testGroupingByConcurrentWithFunctionAndSupplierAndCollector();
        testJoining();
        testJoiningWithDelimiter();
        testMapping();
        testMaxBy();
    }

    private static void testGroupingByConcurrent() {//concurrent
        ConcurrentMap<Integer, List<Dish>> map = menu.stream().collect(Collectors.groupingByConcurrent(Dish::getCalories));
        Optional.ofNullable(map).ifPresent(System.out::println);
    }

    private static void testGroupingByConcurrentWithFunctionAndCollector() {//concurrent
        ConcurrentMap<Dish.Type, Double> map = menu.stream()
                .collect(Collectors.groupingByConcurrent(Dish::getType, Collectors.averagingInt(Dish::getCalories)));
        Optional.ofNullable(map).ifPresent(System.out::println);
    }

    //redis skipList data structure
    private static void testGroupingByConcurrentWithFunctionAndSupplierAndCollector() {
        Optional.ofNullable(menu.stream()
                .collect(Collectors.groupingByConcurrent(
                        Dish::getType, ConcurrentSkipListMap::new, Collectors.summarizingInt(Dish::getCalories)
                )))
                .ifPresent(System.out::println);
    }

    private static void testJoining(){
        Optional.ofNullable(menu.stream().map(Dish::getName).collect(Collectors.joining())).ifPresent(System.out::println);
    }

    private static void testJoiningWithDelimiter(){
        Optional.ofNullable(menu.stream().map(Dish::getName).collect(Collectors.joining(",","Names[","]"))).ifPresent(System.out::println);
    }

    private static void testMapping(){
        Optional.ofNullable(menu.stream().collect(Collectors.mapping(Dish::getName, Collectors.joining(",","Names[","]"))))
                .ifPresent(System.out::println);
    }

    private static void testMaxBy(){
        menu.stream().collect(Collectors.maxBy(Comparator.comparing(Dish::getCalories)))//max(Comparator.comparing(Dish::getCalories))
                .ifPresent(System.out::println);
    }
}
