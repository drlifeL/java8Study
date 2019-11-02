package cn.dxxy.demo;

import cn.dxxy.entity.Employee;
import cn.dxxy.utils.Util;
import org.junit.Test;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamDemo {
    List<Employee> emps = Util.empList;

    @Test
    public void test1() {
        //给定一个数字列表，返回每个数平方构成的列表
        List<Integer> collect = Stream.of(1, 2, 3, 4, 5)
                .map(e -> e * e)
                .collect(Collectors.toList());
        System.out.println(collect);
    }

    @Test
    public void test2() {
        //怎么用map和reduce方法中数一数流中有多少个Employee
        Object reduce = Stream.of("1", "2", "3", new Employee(), new Employee(), new Employee())
                .map((e) -> {
                            if (e instanceof Employee) {
                                return e;
                            }
                            return null;
                        }
                ).reduce(0, (x, y) -> {
                    if (y instanceof Employee)
                        return (int) x + 1;
                    else {
                        return 0;
                    }
                });
        Optional<Integer> reduce2 = Stream.of("1", "2", "3", new Employee(), new Employee(), new Employee())
                .map((e) -> {
//                    if (e instanceof Employee) {
//                        return 1;
//                    }
//                    return 0;
                   return e instanceof Employee ? 1 : 0;
                }).reduce(Integer::sum);
        System.out.println(reduce2.get());
        System.out.println(reduce);
        Integer reduce1 = emps.stream()
                .map(e -> 1)
                .reduce(0, Integer::sum);
        System.out.println(reduce1);

//        emps.stream()
//                .map(Employee::getName)
//                .reduce("", (x, y) -> x + y);
    }
}
