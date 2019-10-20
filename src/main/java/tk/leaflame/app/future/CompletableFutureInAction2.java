package tk.leaflame.app.future;

import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicBoolean;

public class CompletableFutureInAction2 {

    public static void main(String[] args) throws InterruptedException {
//        AtomicBoolean finished = new AtomicBoolean(false);
        ExecutorService executor = Executors.newFixedThreadPool(2, r -> {
            Thread t = new Thread(r);
            t.setDaemon(false);//no daemon
            return t;
        });
        CompletableFuture.supplyAsync(CompletableFutureInAction1::get, executor)
                .whenComplete((v, t) -> {
                    Optional.ofNullable(v).ifPresent(System.out::println);
                    Optional.ofNullable(t).ifPresent(Throwable::printStackTrace);
//                    finished.set(true);
                });
        System.out.println("no-block...");
        executor.shutdown();//shutdown
//        while (!finished.get()) {
//            Thread.sleep(1);
//        }
    }
}
