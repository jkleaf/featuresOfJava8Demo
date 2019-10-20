package tk.leaflame.app.collector;

import tk.leaflame.app.stream.Dish;

import java.util.*;
import java.util.stream.Collectors;

public class CollectorsInAction {

    public final static List<Dish> menu = Arrays.asList(new Dish("pork", false, 800, Dish.Type.MEAT),
            new Dish("beef", false, 700, Dish.Type.MEAT),
            new Dish("chicken", false, 400, Dish.Type.MEAT),
            new Dish("french fries", true, 530, Dish.Type.OTHER),
            new Dish("rice", true, 350, Dish.Type.OTHER),
            new Dish("season fruit", true, 120, Dish.Type.OTHER),
            new Dish("pizza", true, 550, Dish.Type.OTHER),
            new Dish("prawns", false, 400, Dish.Type.FISH),
            new Dish("salmon", false, 450, Dish.Type.FISH));

    public static void main(String[] args) {
        testAveragingDouble();
        testCollectingAndThen();
        testCounting();
        testGroupByFunction();
        testGroupByFunctionAndCollector();
        testGroupByFunctionAndSupplierAndCollector();
        testSummarizingInt();
    }

    private static void testAveragingDouble() {
        Optional.ofNullable(menu.stream().collect(Collectors.averagingDouble(Dish::getCalories))).ifPresent(System.out::println);
    }

    private static void testCollectingAndThen() {
        Optional.ofNullable(menu.stream()
                .collect(Collectors.collectingAndThen(Collectors.averagingInt(Dish::getCalories),
                        d -> "Averaging to int " + d))).ifPresent(System.out::println);

        List<Dish> list = menu.stream().filter(d -> d.getType().equals(Dish.Type.MEAT))
                .collect(Collectors.collectingAndThen(Collectors.toList(), Collections::unmodifiableList));//dishes -> Collections.unmodifiableList(dishes)
//        list.add(new Dish("",false,233,Dish.Type.FISH));
        System.out.println(list);
    }

    private static void testCounting() {
        Optional.of(menu.stream().collect(Collectors.counting())).ifPresent(System.out::println);//no need ofNullable
//        Optional.of(menu.size()).ifPresent(System.out::println);
    }

    private static void testGroupByFunction() {
        Optional.ofNullable(menu.stream().collect(Collectors.groupingBy(Dish::getType))).ifPresent(System.out::println);
    }

    private static void testGroupByFunctionAndCollector() {
        Optional.ofNullable(menu.stream().collect(Collectors.groupingBy(Dish::getType, Collectors.counting())))
                .ifPresent(System.out::println);

        Optional.ofNullable(menu.stream().collect(Collectors.groupingBy(Dish::getType, Collectors.averagingInt(Dish::getCalories))))
                .ifPresent(System.out::println);
    }

    private static void testGroupByFunctionAndSupplierAndCollector() {
        Map<Dish.Type, Long> map = menu.stream().collect(Collectors.groupingBy(Dish::getType, TreeMap::new, Collectors.counting()));
        System.out.println(map.getClass());//convert to TreeMap
        Optional.of(map).ifPresent(System.out::println);
    }

    private static void testSummarizingInt(){
        IntSummaryStatistics result = menu.stream().collect(Collectors.summarizingInt(Dish::getCalories));
        Optional.of(result).ifPresent(System.out::println);//toString()
    }
}
