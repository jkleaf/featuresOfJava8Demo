package tk.leaflame.app.stream.streamInAction;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.*;
import static java.util.stream.Collectors.toList;

/*P98*/
public class PuttingIntoPractice {

    private static List<Transaction> transactions() {
        Trader raoul = new Trader("Raoul", "Cambridge");
        Trader mario = new Trader("Mario", "Milan");
        Trader alan = new Trader("Alan", "Cambridge");
        Trader brian = new Trader("Brian", "Cambridge");

        return Arrays.asList(
                new Transaction(brian, 2011, 300),
                new Transaction(raoul, 2012, 1000),
                new Transaction(raoul, 2011, 400),
                new Transaction(mario, 2012, 710),
                new Transaction(mario, 2012, 700),
                new Transaction(alan, 2012, 950)
        );
    }

    public static void main(String[] args) {
        /*Query 1*/
        List<Transaction> tr2011 = transactions().stream().filter(transaction -> transaction.getYear() == 2011)
                .sorted(comparing(Transaction::getValue))
                .collect(toList());
        tr2011.forEach(System.out::println);

        /*Query 2*/
        List<String> cities = transactions().stream()
                .map(transaction -> transaction.getTrader().getCity())
                .distinct().collect(toList());
        cities.forEach(System.out::println);

        Set<String> cities2 = transactions().stream()
                .map(transaction -> transaction.getTrader().getCity())
                .collect(toSet());//toSet

        /*Query 3*/
        List<Trader> traders = transactions().stream()
                .map(Transaction::getTrader)
                .filter(trader -> trader.getCity().equalsIgnoreCase("Cambridge"))
                .distinct()
                .sorted(comparing(Trader::getName))
                .collect(toList());
        traders.forEach(System.out::println);

        /*Query 4*/
        String names = transactions().stream()
                .map(transaction -> transaction.getTrader().getName())
                .distinct()
                .sorted()
                .collect(joining());
        System.out.println(names);

        String names2=transactions().stream()
                .map(transaction -> transaction.getTrader().getName())
                .distinct()
                .sorted()
                .reduce("",(name1,name2)->name1+name2);
        System.out.println(names2);

        /*Query 5*/
        boolean milanBased = transactions().stream()
                .anyMatch(transaction -> transaction.getTrader().getCity().equalsIgnoreCase("Milan"));
        assert milanBased : "no one works in Milan";

        /*Query 6*/
        transactions().stream()
                .filter(transaction -> transaction.getTrader().getCity().equalsIgnoreCase("Cambridge"))
                .mapToInt(Transaction::getValue)
                .forEach(System.out::println);

        /*Query 7*/
        transactions().stream()
                .mapToInt(Transaction::getValue)
                .max().ifPresent(System.out::println);

        Optional<Integer> highestValue=transactions().stream()
                .map(Transaction::getValue)
                .reduce(Integer::max);
        highestValue.ifPresent(System.out::println);

        /*Query 8*/
        transactions().stream()
                .reduce((t1,t2)->t1.getValue()<t2.getValue()?t1:t2)
                .ifPresent(System.out::println);

        transactions().stream()
                .min(comparing(Transaction::getValue))
                .ifPresent(System.out::println);
    }
}
