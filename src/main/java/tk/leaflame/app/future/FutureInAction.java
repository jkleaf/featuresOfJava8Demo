package tk.leaflame.app.future;

import java.util.concurrent.*;

public class FutureInAction {

    public static void main(String[] args) throws ExecutionException, InterruptedException, TimeoutException {
        ExecutorService executorService= Executors.newSingleThreadExecutor();
        Future<String> future=executorService.submit(()->{
            try {
                Thread.sleep(2000);
                return "finished";
            } catch (InterruptedException e) {
                return "Error";
            }
        });
//        String value=future.get(1, TimeUnit.MILLISECONDS);
//        System.out.println(value);
        while(!future.isDone()){
            System.out.println("wait...");
            Thread.sleep(500);
        }
        System.out.println(future.get());//outside
        executorService.shutdown();//exit process
    }
}
