package cn.dxxy.demo;

import cn.dxxy.entity.Trader;
import cn.dxxy.entity.Transaction;
import org.junit.Before;
import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;

public class TransactionTest {
    List<Transaction> transactions = null;

    @Before
    public void before() {
        Trader raoul = new Trader("Raoul", "Cambridge");
        Trader mario = new Trader("Mario", "Milan");
        Trader alan = new Trader("Alan", "Cambridge");
        Trader brian = new Trader("Brian", "Cambridge");
        transactions = Arrays.asList(
                new Transaction(brian, 2011, 300),
                new Transaction(raoul, 2012, 1000),
                new Transaction(raoul, 2011, 400),
                new Transaction(mario, 2012, 710),
                new Transaction(mario, 2012, 700),
                new Transaction(alan, 2012, 950)
        );
    }

    @Test
    public void test1() {
        //找出2011年发生的所有交易，并按照交易额排序（从低到高）
        transactions.stream()
                .filter((e) -> e.getYear() == 2011)
                .sorted(Comparator.comparingInt(Transaction::getValue))
                .forEach(System.out::println);
    }

    @Test
    public void test2() {
        //交易员都在哪些不同的城市工作过
//        Map<String, List<Transaction>> collect = transactions.stream()
//                .collect(Collectors.groupingBy((e) -> e.getTrader().getCity()));
//        collect.keySet().forEach(System.out::println);
        transactions.stream()
                .map((e)->e.getTrader().getCity())
                .distinct()
                .forEach(System.out::println);
    }

    @Test
    public void test3() {
        //查找所有来自剑桥的交易员，并按姓名排序
        transactions.stream()
                .map(Transaction::getTrader)
                .distinct()
                .filter((e) -> e.getCity().equals("Cambridge"))
                .sorted(Comparator.comparing(Trader::getName)).forEach(System.out::println);
        System.out.println("-------------------");
        transactions.stream()
                .filter((e)->e.getTrader().getCity().equals("Cambridge"))
                .map(Transaction::getTrader)
                .sorted(Comparator.comparing(Trader::getName))
                .distinct()
                .forEach(System.out::println);
    }

    @Test
    public void test4() {
        //返回所有交易员的姓名字符串，并按字母顺序排序
        String collect = transactions.stream()
                .map((e) -> e.getTrader().getName())
                .distinct()
                .sorted().collect(Collectors.joining(","));
        System.out.println(collect);
    }

    @Test
    public void test5() {
        //有没有 交易员是在米兰工作的
        boolean milan = transactions.stream()
                .map(Transaction::getTrader)
                .anyMatch((e) -> e.getCity().equals("Milan"));
        System.out.println(milan);
    }

    @Test
    public void test6() {
        //打印生活在剑桥的交易员的所有交易额
        Integer cambridge = transactions.stream()
                .filter((e) -> e.getTrader().getCity().equals("Cambridge"))
                .collect(Collectors.summingInt(Transaction::getValue));
        System.out.println(cambridge);
    }

    @Test
    public void test7() {
        //所有交易中，最高的交易额是多少
        Optional<Integer> max = transactions.stream()
                .map(Transaction::getValue)
                .max(Integer::compare);
        System.out.println(max.get());
    }

    @Test
    public void test8(){
        Optional<Transaction> min = transactions.stream()
                .min(Comparator.comparing(Transaction::getValue));
        System.out.println(min.get());


    }
}
