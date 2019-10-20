package tk.leaflame.app.collector;

import tk.leaflame.app.lambda.Apple;

import java.util.*;
import java.util.stream.Collectors;

public class CollectorIntroduce {

    public static void main(String[] args) {
        List<Apple> list = Arrays.asList(new Apple("green", 153), new Apple("green", 100),
                new Apple("red", 322), new Apple("yellow", 233));
        System.out.println(groupByNormal(list));
        System.out.println(groupByFunction(list));
        System.out.println(groupByCollector(list));
    }

    private static Map<String, List<Apple>> groupByNormal(List<Apple> apples) {
        Map<String, List<Apple>> map = new HashMap<>();
        for (Apple apple : apples) {
            List<Apple> list = map.get(apple.getColor());
            if (list == null) {
                list = new ArrayList<>();
                map.put(apple.getColor(), list);
            }
            list.add(apple);
        }
        return map;
    }

    private static Map<String, List<Apple>> groupByFunction(List<Apple> apples) {
        Map<String, List<Apple>> map = new HashMap<>();
        apples.forEach(apple -> {
            List<Apple> colorList = Optional.ofNullable(map.get(apple.getColor())).orElseGet(() -> {
                List<Apple> list = new ArrayList<>();
                map.put(apple.getColor(), list);
                return list;
            });
            colorList.add(apple);
        });
        return map;
    }

    private static Map<String,List<Apple>> groupByCollector(List<Apple> apples){
        return apples.stream().collect(Collectors.groupingBy(Apple::getColor));
    }
}
