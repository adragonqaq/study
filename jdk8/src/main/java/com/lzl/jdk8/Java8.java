package com.lzl.jdk8;

import lombok.Data;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * @author liaozhilong
 * @date 2021/8/19 14:09
 * @Description
 */
@Data
public class Java8 {

    public static void main(String[] args) {

        Trader raoul = new Trader("Raoul", "Cambridge");
        Trader mario = new Trader("Mario", "Milan");
        Trader alan = new Trader("Alan", "Cambridge");
        Trader brian = new Trader("Brian", "Cambridge");

        List<Transaction> transactions = Arrays.asList(
                new Transaction(brian, 2011, 300),
                new Transaction(raoul, 2012, 1000),
                new Transaction(raoul, 2011, 400),
                new Transaction(mario, 2012, 710),
                new Transaction(mario, 2012, 700),
                new Transaction(alan, 2012, 950)
        );

        // 1. Find all transactions in the year 2011 and sort them by value (small to high).
        /*transactions.stream()
                .filter((d) -> 2011 == d.getYear())
                //.sorted(Comparator.comparing(Transaction::getValue))
                .sorted((o1, o2) -> o2.getValue() - o1.getValue())
                .forEach(System.out::println);*/
        /*List<Transaction> trx2011 = new ArrayList<>();
        for (Transaction transaction : transactions) {
            if (2011 == transaction.getYear()) {
                trx2011.add(transaction);
            }
        }
        trx2011.sort(new Comparator<Transaction>() {
            @Override
            public int compare(Transaction o1, Transaction o2) {
                return 0;
            }
        });
        for (Transaction t : trx2011) {
            System.out.println(t);
        }*/
        /*List<Transaction> trx2011 = new ArrayList<>();
        for (Transaction transaction : transactions) {
            if (2011 == transaction.getYear()) {
                trx2011.add(transaction);
            }
        }
        trx2011.sort((o1, o2) -> {
            return o1.getValue() - o2.getValue();
        });
        *//*for (Transaction t : trx2011) {
            System.out.println(t);
        }*//*
        trx2011.forEach(d-> System.out.println(d));*/
        // 2. What are all the unique cities where the traders work?
        /*transactions.stream()
                .map(t-> t.getTrader())
                .map(Trader::getCity)
                .distinct()
                .forEach(System.out::println);*/
        // 3. Find all traders from Cambridge and sort them by name.
        /*transactions.stream()
                .map(Transaction::getTrader)
                .filter(t -> "Cambridge".equals(t.getCity()))
                .sorted(Comparator.comparing(Trader::getName))
                .forEach(System.out::println);*/
        // 4. Ret a string of all traders’ names sorted alphabetically.(a, a+(x,y))
        //transactions.stream().map(Transaction::getTrader).map(Trader::getName).distinct().forEach(System.out::println);
        transactions.stream()
                .map(Transaction::getTrader)
                .map(Trader::getName)
                .distinct()
                .sorted()
                .reduce(String::concat)
                .ifPresent(System.out::println);
        /*transactions.stream()
                .map(Transaction::getTrader)
                .map(Trader::getName)
                .distinct()
                .sorted((t1, t2) -> t2.compareTo(t1))
                .reduce(String::concat)
                .ifPresent(System.out::println);*/
        // 5. Are any traders based in Milan?
        boolean b = transactions.stream().anyMatch(t -> "Milan".equals(t.getTrader().getCity()));
        // System.out.println(b);
        // 6. Print all transactions’ values from the traders living in Cambridge.
        /*transactions.stream()
                .filter(t -> "Cambridge".equals(t.getTrader().getCity()))
                .forEach(t-> System.out.println(t.getValue()));*/
        // 7. What’s the highest value of all the transactions?
        transactions.stream()
                .max(Comparator.comparing(Transaction::getValue))
                .ifPresent(System.out::println);
        // transactions.stream().map(Transaction::getValue).reduce(Integer::max).ifPresent(System.out::println);
        // 8. Find the transaction with the smallest value.
        transactions.stream().
                min(Comparator.comparing(Transaction::getValue))
                .ifPresent(System.out::println);
    }

    @Data
    static class Trader {
        private final String name;
        private final String city;
    }

    @Data
    static class Transaction implements Comparable{
        private final Trader trader;
        private final int year;
        private final int value;

        @Override
        public int compareTo(Object o) {
            return 0;
        }
    }
}
