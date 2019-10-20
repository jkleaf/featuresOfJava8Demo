package tk.leaflame.app.stream;

import tk.leaflame.app.stream.Dish;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static java.util.stream.Collectors.*;

public class StreamMapDemo {

    private static List<Dish> listDish(){
        return Arrays.asList(new Dish("pork", false, 800, Dish.Type.MEAT),
                new Dish("beef", false, 700, Dish.Type.MEAT),
                new Dish("chicken", false, 400, Dish.Type.MEAT),
                new Dish("french fries", true, 530, Dish.Type.OTHER),
                new Dish("rice", true, 350, Dish.Type.OTHER),
                new Dish("season fruit", true, 120, Dish.Type.OTHER),
                new Dish("pizza", true, 550, Dish.Type.OTHER),
                new Dish("prawns", false, 400, Dish.Type.FISH),
                new Dish("salmon", false, 450, Dish.Type.FISH));
    }

    public static void main(String[] args) {
        List<Integer> list = Arrays.asList(1, 2, 3, 3, 4, 5, 6, 7, 7, 7, 10);
        List<Integer> result = list.stream().map(i -> i * 2).collect(toList());
        System.out.println(result);
        List<String> dishNames=listDish().stream().map(Dish::getName).collect(toList());
        System.out.println(dishNames);
        /*flatMap*/
        String[] words={"HELLO","WORLD"};
        Stream<String[]> stream = Arrays.stream(words).map(w -> w.split(""));
        Stream<String> stringStream = stream.flatMap(Arrays::stream);//Stream::of(strings->Arrays.stream(strings))
        stringStream.distinct().forEach(System.out::println);
        System.out.println("<==>");
        //<==>
        Arrays.stream(words).map(w->w.split("")).flatMap(Stream::of).distinct().forEach(System.out::println);
    }

}
