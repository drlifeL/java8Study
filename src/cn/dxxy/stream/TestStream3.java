package cn.dxxy.stream;

import cn.dxxy.entity.Employee;
import cn.dxxy.utils.Util;
import org.junit.Test;

import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

//终端操作
public class TestStream3 {

    List<Employee> emps = Util.empList;

    /*
    	allMatch----检查是否匹配所有元素  boolean
        anyMatch----检查是否至少匹配一个元素  boolean
        noneMatch---- 检查是否没有匹配所有元素 boolean
        findFirst----返回第一个元素  Optional<T>
        findAny----返回当前流中的任意元素  Optional<T>
     */

    @Test
    public void test1() {
        boolean b = emps.stream()
                .allMatch((e) -> e.getStatus().equals(Employee.Status.BUSY));
        System.out.println(b);
        boolean b1 = emps.stream()
                .anyMatch((e) -> e.getStatus().equals(Employee.Status.VOCATION));
        System.out.println(b1);

        boolean b2 = emps.stream()
                .noneMatch(e -> e.getStatus().equals(Employee.Status.NONE));
        System.out.println(b2);

        Optional<Employee> first = emps.stream()
                .filter(e -> e.getAge() > 30)
                .findFirst();
        Employee employee = first.get();
        System.out.println(employee);

        //使用并行流，多个线程同时找，那个线程快就返回那个
        Optional<Employee> any = emps.parallelStream()
                .findAny();
        System.out.println(any.get());

    }

    @Test
    public void test2() {
        long count = emps.stream()
                .filter(e -> e.getSalary() > 6000)
                .count();
        System.out.println(count);

        Optional<Double> max = emps.stream()
                .map(e -> e.getSalary())
                .max(Double::compareTo);
        System.out.println(max.get());

        Optional<Double> min = emps.stream()
                .map(Employee::getSalary)
                .min(Double::compareTo);
        System.out.println(min.get());
        //拿到工资第二低的员工信息
        Optional<Employee> first = emps.stream()
                .sorted(Comparator.comparingDouble(Employee::getSalary))
                .skip(1)
                .findFirst();
        System.out.println(first.get());
    }

    @Test
    public void test3() {
        //reduce 规约，将流中的值反复结合起来，形成一个新的值
        Integer reduce = Stream.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10).reduce(0, Integer::sum);

        System.out.println(reduce);
        //identity
        Double reduce1 = emps.stream().map(Employee::getSalary)
                .reduce(0.0, Double::sum);
        System.out.println(reduce1);
        //方法的重载
        Optional<Double> reduce2 = emps.stream()
                .map(Employee::getSalary)
                .reduce(Double::sum);
        System.out.println(reduce2.get());
    }

    @Test
    public void test4() {
        //collect 收集
        //将所有的名字收集起来变成一个List
        List<String> collect = emps.stream()
                .map(Employee::getName)
                .collect(Collectors.toList());
        collect.forEach(System.out::println);
        //将所有的对象收集起来，加入到Set集合中，注意这里去重必须要重写HashCode和Equals方法
        Set<Employee> collect1 = emps.stream()
                .collect(Collectors.toSet());
        collect1.forEach(System.out::println);
        //将所有的对象收集，加入到HashSet集合中
        HashSet<String> collect2 = emps.stream()
                .map(Employee::getName)
                .collect(Collectors.toCollection(HashSet::new));
        collect2.forEach(System.out::println);

    }

    @Test
    public void test5() {
        //收集
        //总数
        long count = emps.stream()
                .collect(Collectors.counting());
        System.out.println(count);
        //平均值
        Double avg = emps.stream()
                .collect(Collectors.averagingDouble(Employee::getSalary));
        System.out.println(avg);
        //最大，最小
        Optional<Double> max = emps.stream()
                .map(Employee::getSalary)
                .collect(Collectors.maxBy(Double::compareTo));
        System.out.println(max.get());
        Optional<Double> min = emps.stream()
                .map(Employee::getSalary)
                .collect(Collectors.minBy(Double::compareTo));
        System.out.println(min.get());
        //也可以写成这样
        Optional<Employee> maxBy = emps.stream()
                .collect(Collectors.maxBy(Comparator.comparingDouble(Employee::getSalary)));
        System.out.println(maxBy);
        //工资总和
        Double salarySum = emps.stream()
                .collect(Collectors.summingDouble(Employee::getSalary));
        System.out.println(salarySum);
    }

    //分组
    @Test
    public void test6() {
        Map<Employee.Status, List<Employee>> collect = emps.stream()
                .collect(Collectors.groupingBy(Employee::getStatus));
        System.out.println(collect);
    }

    //多级分组
    @Test
    public void test7() {
        Map<Employee.Status, Map<String, List<Employee>>> collect = emps.stream()
                .collect(Collectors.groupingBy(Employee::getStatus, Collectors.groupingBy((e) -> {
                    if (e.getAge() <= 35) {
                        return "青年";
                    } else if (e.getAge() <= 50) {
                        return "中年";
                    } else {
                        return "老年";
                    }
                })));
        System.out.println(collect);
    }

    @Test
    public void test8() {
        Map<Boolean, List<Employee>> collect = emps.stream()
                .collect(Collectors.partitioningBy(e -> e.getAge() > 35));
        System.out.println(collect);
    }

    @Test
    public void test9() {
        DoubleSummaryStatistics collect = emps.stream()
                .collect(Collectors.summarizingDouble(Employee::getSalary));
        double average = collect.getAverage();
        long count = collect.getCount();
        double max = collect.getMax();
        double min = collect.getMin();
        double sum = collect.getSum();
        System.out.println("平均值:" + average + " 总共:" + count + "条 最大值：" + max + " 最小值：" + min + " 总和：" + sum);
    }

    @Test
    public void test10() {
        String collect = emps.stream()
                .map(Employee::getName)
                .collect(Collectors.joining());
        System.out.println(collect);
        System.out.println("-------------");
        String collect1 = emps.stream()
                .map(Employee::getName)
                .collect(Collectors.joining(","));
        System.out.println(collect1);

        String collect2 = emps.stream()
                .map(Employee::getName)
                .collect(Collectors.joining(",", "---", "==="));
        System.out.println(collect2);


    }

}
