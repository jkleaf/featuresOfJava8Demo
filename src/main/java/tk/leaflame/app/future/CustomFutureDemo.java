package tk.leaflame.app.future;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;

public class CustomFutureDemo {

    public static void main(String[] args) throws InterruptedException {
        Future<String> future = invoke(()->{
            try {
                Thread.sleep(1000);
                return "finished";
            } catch (InterruptedException e) {
                return "Error";
            }
        });
        System.out.println(future.get());
        System.out.println(future.get());
        System.out.println(future.get());
        while(!future.isDone()){//in one second(async to do something)
            Thread.sleep(10);
        }
        System.out.println(future.get());
    }

    private static <T> T block(Callable<T> callable){//just wait
        return callable.action();
    }

    private static <T> Future<T> invoke(Callable<T> callable) {
        AtomicReference<T> result = new AtomicReference<>();//
        AtomicBoolean finished = new AtomicBoolean(false);//
        Thread t = new Thread(() -> {
            T value = callable.action();
            result.set(value);
            finished.set(true);
        });
        t.start();

        Future<T> future = new Future<T>() {
            @Override
            public T get() {
                return result.get();
            }

            @Override
            public boolean isDone() {
                return finished.get();
            }
        };
        return future;
    }

    private interface Future<T> {

        T get();

        boolean isDone();
    }

    private interface Callable<T> {

        T action();
    }

}
