package tk.leaflame.app.stream;

import java.util.*;
import java.util.stream.Stream;

import static java.util.Comparator.*;//static import
import static java.util.stream.Collectors.*;

public class SimpleStream {

    private static List<String> getDishNamesByStream(List<Dish> menu) {
        return menu.stream().filter(d -> d.getCalories() < 400)
                .sorted(comparing(Dish::getCalories)).map(Dish::getName).collect(toList());
    }

    private static List<String> getDishNamesByStream2(List<Dish> menu) {
        return menu.stream().filter(d -> {
            System.out.println("filtering->" + d.getName());
            return d.getCalories() > 300;
        })
                .map(d -> {
                    System.out.println("mapping->" + d.getName());
                    return d.getName();
                })
                .limit(7).collect(toList());
    }

    private static List<String> getDishNamesByCollections(List<Dish> menu) {
        List<Dish> lowCalories = new ArrayList<>();
        for (Dish dish : menu) {
            if (dish.getCalories() < 400) {
                lowCalories.add(dish);
            }
        }
        lowCalories.sort(comparingInt(Dish::getCalories));
        List<String> dishNameList = new ArrayList<>();
        for (Dish dish : lowCalories) {
            dishNameList.add(dish.getName());
        }
        return dishNameList;
    }

    public static void main(String[] args) {
        List<Dish> menu =
                Arrays.asList(new Dish("pork", false, 800, Dish.Type.MEAT),
                        new Dish("beef", false, 700, Dish.Type.MEAT),
                        new Dish("chicken", false, 400, Dish.Type.MEAT),
                        new Dish("french fries", true, 530, Dish.Type.OTHER),
                        new Dish("rice", true, 350, Dish.Type.OTHER),
                        new Dish("season fruit", true, 120, Dish.Type.OTHER),
                        new Dish("pizza", true, 550, Dish.Type.OTHER),
                        new Dish("prawns", false, 400, Dish.Type.FISH),
                        new Dish("salmon", false, 450, Dish.Type.FISH));
        //traditional
        System.out.println(getDishNamesByCollections(menu));
        //stream
        System.out.println(getDishNamesByStream(menu));
        System.out.println(getDishNamesByStream2(menu));
        //wrong
//        Stream<Dish> stream=menu.stream();
//        stream.forEach(System.out::println);
//        stream.forEach(System.out::println);
    }
}
