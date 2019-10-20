package tk.leaflame.app.collector;

import tk.leaflame.app.stream.Dish;

import java.util.Collections;
import java.util.LinkedList;
import java.util.Optional;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import static tk.leaflame.app.collector.CollectorsInAction.*;

public class CollectorsAction4 {

    public static void main(String[] args) {
        testSummingDouble();
        testToCollection();
        testToConcurrentMap();
        testToConcurrentMapWithBinaryOperator();
        testToConcurrentMapWithBinaryOperatorAndSupplier();
        testToMap();
    }

    private static void testSummingDouble() {
        Optional.ofNullable(menu.stream().collect(Collectors.summingDouble(Dish::getCalories))).ifPresent(System.out::println);
    }

    private static void testToCollection() {
        Optional.of(menu.stream().collect(Collectors.toCollection(LinkedList::new))).ifPresent(System.out::println);
//        Optional.of(new LinkedList<>(menu)).ifPresent(System.out::println);
    }

    //thread safe and concurrent
    private static void testToConcurrentMap() {
        Optional.of(menu.stream().collect(Collectors.toConcurrentMap(Dish::getName, Dish::getCalories)))//key & value
                .ifPresent(System.out::println);
    }

    //merge
    private static void testToConcurrentMapWithBinaryOperator() {
        Optional.of(menu.stream().collect(Collectors.toConcurrentMap(Dish::getType, v -> 1, Integer::sum)))//key & value
                .ifPresent(System.out::println);
    }

    private static void testToConcurrentMapWithBinaryOperatorAndSupplier() {
        Optional.of(menu.stream().collect(Collectors.toConcurrentMap(Dish::getType, v -> 1, Integer::sum, ConcurrentSkipListMap::new)))//key & value
                .ifPresent(System.out::println);
    }

    //synchronized
    private static void testToMap(){
        Optional.of(menu.stream().collect(
                Collectors.collectingAndThen(Collectors.toMap(Dish::getType, v -> 1, Integer::sum, ConcurrentSkipListMap::new)
                , Collections::synchronizedMap)//thread safe
        )).ifPresent(System.out::println);
    }
}
