package tk.leaflame.app.collector;

import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;

public class CustomToListCollector<T> implements Collector<T, List<T>, List<T>> {

    private void log(final String log){
        System.out.println(Thread.currentThread().getName()+"-"+log);
    }

    @Override
    public Supplier<List<T>> supplier() {
        log("supplier");
        return ArrayList::new;
    }

    @Override
    public BiConsumer<List<T>, T> accumulator() {
        log("accumulator");
        return List::add;
    }

    @Override
    public BinaryOperator<List<T>> combiner() {//addAll(add to first one) fork join
        log("combiner");
        return (list1, list2) -> {
            list1.addAll(list2);
            return list1;
        };
    }

    @Override
    public Function<List<T>, List<T>> finisher() {
        log("finisher");
        return Function.identity();//return t->t;
    }

    @Override
    public Set<Characteristics> characteristics() {//特征值
        log("characteristics");
        return Collections.unmodifiableSet(EnumSet.of(Characteristics.IDENTITY_FINISH,
                Characteristics.CONCURRENT));
    }
}
