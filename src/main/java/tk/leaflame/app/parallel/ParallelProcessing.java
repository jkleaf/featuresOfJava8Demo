package tk.leaflame.app.parallel;

import java.util.function.Function;
import java.util.stream.LongStream;
import java.util.stream.Stream;

public class ParallelProcessing {

    private static final long LIMIT = 100_000_000;

    public static void main(String[] args) {
        System.setProperty("java.util.concurrent.ForkJoinPool.common.parallelism","20");
//        System.out.println(Runtime.getRuntime().availableProcessors());
//        System.out.println("Best precess time(iterateStream)=>" + measureSumPerformance(ParallelProcessing::iterateStream, LIMIT) + " MS");
        System.out.println("Best process time(normalAdd)=>" + measureSumPerformance(ParallelProcessing::normalAdd, LIMIT) + " MS");
        System.out.println("Best process time(parallelStream)=>" + measureSumPerformance(ParallelProcessing::parallelStream, LIMIT)+" MS");
    }

    private static long measureSumPerformance(Function<Long, Long> adder, long limit) {//get the best time during every turn
        long fastest = Long.MAX_VALUE;
        for (long i = 0; i < 10; i++) {
            long startTimeStamp = System.currentTimeMillis();
            Long result = adder.apply(limit);
            long duration = System.currentTimeMillis() - startTimeStamp;
            System.out.println(i + 1 + "-sum result=>" + result);
            if (duration < fastest) fastest = duration;
        }
        return fastest;
    }

    private static long iterateStream(long limit) {
        return Stream.iterate(1L, i -> i + 1).limit(limit).reduce(0L, Long::sum);
    }

    private static long parallelStream(long limit) {
        return LongStream.rangeClosed(1, limit).parallel().reduce(0L, Long::sum);//.sum();
    }

    private static long normalAdd(long limit) {
        long result = 0L;
        for (long i = 1L; i <= limit; i++) {
            result += i;
        }
        return result;
    }
}
