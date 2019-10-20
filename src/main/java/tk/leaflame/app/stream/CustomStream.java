package tk.leaflame.app.stream;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class CustomStream {

    private static Stream<String> createStreamFromCollection(){
        List<String> list=Arrays.asList("wc","vb","php","ioc","aop");
        return list.stream();
    }

    private static Stream<String> createStreamFromValues(){
        return Stream.of("wc","vb","php","ioc","aop");
    }

    private static Stream<String> createStreamFromArrays(){
        return Arrays.stream(new String[]{"wc","vb","php","ioc","aop"});
    }

    private static void createStreamFromFile(){
        Path path= Paths.get("E:\\intelliJ_project\\featuresOfJava8Demo\\pom.xml");
        try(Stream<String> lines = Files.lines(path)){
            lines.forEach(System.out::println);
        } catch (IOException e) {
            throw new RuntimeException(e);//no catch
        }
//        return lines;
    }

    /*infinite stream*/
    private static Stream<long[]> createStreamFromIterator(){
//        return Stream.iterate(0, n->n+2).limit(100);
        return Stream.iterate(new long[]{1,1},a->new long[]{a[1],a[0]+a[1]}).limit(10);
    }

    private static Stream<Double> createStreamFromGenerator(){
        return Stream.generate(Math::random).limit(10);//()->{return Math.random();}
    }

    public static void main(String[] args) {
        createStreamFromCollection().forEach(System.out::println);
        createStreamFromValues().forEach(System.out::println);
        createStreamFromArrays().forEach(System.out::println);
        createStreamFromFile();
//        createStreamFromIterator().forEach(System.out::println);
        createStreamFromIterator().forEach(a-> System.out.println(a[0]));
        createStreamFromGenerator().forEach(System.out::println);
    }

}
