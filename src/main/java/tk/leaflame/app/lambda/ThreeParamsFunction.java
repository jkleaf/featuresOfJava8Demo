package tk.leaflame.app.lambda;

@FunctionalInterface
public interface ThreeParamsFunction<T, U, K, R> {

    R apply(T t, U u, K k);

}
