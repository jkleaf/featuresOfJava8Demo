package tk.leaflame.app.stream;

import java.util.stream.Stream;

public class StreamMatchDemo {

    private static Stream<Integer> testStream(){
        return Stream.of(1, 2, 3, 3, 4, 5, 6, 7, 7, 7, 10);
    }

    public static void main(String[] args) {
        boolean matched = testStream().allMatch(i -> i > 0);
        assert matched : "some elements not matched.";//-enableAssertion(-ea)
        assert testStream().anyMatch(i->i>7):"no elements greater than 7";
        
    }
}
