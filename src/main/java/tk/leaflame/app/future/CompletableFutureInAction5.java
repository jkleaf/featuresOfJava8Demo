package tk.leaflame.app.future;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.function.IntFunction;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CompletableFutureInAction5 {

    public static void main(String[] args) throws InterruptedException {

        /*runAfterBoth*/
        CompletableFuture.supplyAsync(() -> {
            System.out.println(Thread.currentThread().getName() + " is running");
            return 1;
        }).runAfterBoth(CompletableFuture.supplyAsync(() -> {
            System.out.println(Thread.currentThread().getName() + " is running");
            return 2;
        }), () -> System.out.println("done"));

        /*applyToEither*/
        //TODO

        /*acceptEither*/
        //TODO

        /*runAfterEither*/
        //TODO

        /*anyOf*/
        CompletableFuture.allOf(Stream.of(1, 2, 3, 4)
                .map(i -> CompletableFuture.supplyAsync(() -> {
                    Double value = CompletableFutureInAction1.get();
                    Optional.of(value).ifPresent(System.out::println);
                    return value;
                }))
                .toArray(CompletableFuture[]::new))//IntFunction<CompletableFuture<?>[]>) value -> new CompletableFuture[0]
                .thenRun(() -> System.out.println("done"));

        /*allOf*/
        CompletableFuture.anyOf(Stream.of(1, 2, 3, 4)
                .map(i -> CompletableFuture.supplyAsync(() -> {
                    Double value = CompletableFutureInAction1.get();
                    Optional.of(value).ifPresent(System.out::println);
                    return value;
                }))
                .toArray(CompletableFuture[]::new))
                .thenRun(() -> System.out.println("done"));

        Thread.currentThread().join();
    }
}
