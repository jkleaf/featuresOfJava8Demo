package tk.leaflame.app.collector;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collector;

public class CustomCollectorInAction {

    public static void main(String[] args) {
        Collector<String, List<String>, List<String>> collector = new CustomToListCollector<>();
        String[] strings = new String[]{"ajax", "axios", "oauth2", "redHat", "tensorflow", "vue", "undertow", "nginx", "redis"};
        List<String> result = Arrays.stream(strings).filter (s -> s.length() > 5).collect(collector);
//        List<String> result = Arrays.asList(strings).parallelStream().filter(s -> s.length() > 5).collect(collector);
        System.out.println(result);
    }
}
