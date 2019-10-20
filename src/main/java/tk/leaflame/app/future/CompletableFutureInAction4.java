package tk.leaflame.app.future;

import java.util.concurrent.CompletableFuture;

public class CompletableFutureInAction4 {

    public static void main(String[] args) {
        CompletableFuture.supplyAsync(() -> 1)
                .thenApply(i -> Integer.sum(i, 10))
                .handle((v, t) -> Integer.sum(v, 10))
                .whenComplete((v, t) -> System.out.println(v))
                .thenRun(System.out::println);

        CompletableFuture.supplyAsync(() -> 1)
                .thenAccept(System.out::println);

        CompletableFuture.supplyAsync(() -> 1)
                .thenCompose(i -> CompletableFuture.supplyAsync(() -> i * 10))//compose two CompletableFuture
                .thenAccept(System.out::println);

        CompletableFuture.supplyAsync(() -> 1)
                .thenCombine(CompletableFuture.supplyAsync(() -> 2.0d), Double::sum)//combine two return value(result)
                .thenAccept(System.out::println);

        CompletableFuture.supplyAsync(() -> 1)
                .thenAcceptBoth(CompletableFuture.supplyAsync(() -> 2.0d), (i, d) ->
                        System.out.println(i + "\n" + d + "\n" + (i + d)));
    }
}
