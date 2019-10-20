package tk.leaflame.app.future;

import java.util.Optional;
import java.util.Random;
import java.util.concurrent.CompletableFuture;

public class CompletableFutureInAction1 {

    private final static Random RANDOM = new Random(System.currentTimeMillis());//seed

    public static void main(String[] args) {
        CompletableFuture<Double> completableFuture = new CompletableFuture<>();
        new Thread(() -> {
            Double value=get();
            completableFuture.complete(value);
        }).start();
        System.out.println("no-block...");
        completableFuture.whenComplete((v,t)->{
            Optional.ofNullable(v).ifPresent(System.out::println);
            Optional.ofNullable(t).ifPresent(Throwable::printStackTrace);
        });
    }

    static Double get() {
        try {
            Thread.sleep(RANDOM.nextInt(10000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return RANDOM.nextDouble();
    }
}
