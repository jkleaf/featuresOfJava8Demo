package tk.leaflame.app.future;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

public class CompletableFutureInAction3 {

    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(2, r -> {
            Thread t = new Thread(r);
            t.setDaemon(false);//no daemon
            return t;
        });
        List<Integer> prodIDs = Arrays.asList(1, 2, 3, 4, 5);
        /*concurrent+stream+join*/
        List<Double> result = prodIDs.stream()
                .map(i -> CompletableFuture.supplyAsync(() -> queryByProdID(i), executor)
                        .whenComplete((v, t) -> {
                            Optional.ofNullable(v).ifPresent(System.out::println);
                            Optional.ofNullable(t).ifPresent(Throwable::printStackTrace);
                        }))
                .map(future -> future.thenApply(CompletableFutureInAction3::multiply))
                .map(CompletableFuture::join)
                .collect(Collectors.toList());
        System.out.println(result);
        executor.shutdown();
    }

    private static double multiply(double value) {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return value * 10d;
    }

    private static double queryByProdID(int i) {
        return CompletableFutureInAction1.get();
    }
}
