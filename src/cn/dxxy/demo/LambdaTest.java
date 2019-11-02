package cn.dxxy.demo;

import cn.dxxy.entity.Employee;
import cn.dxxy.lambda.baseLambda.FunGetValue;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

public class LambdaTest {

    List<Employee> list = Arrays.asList(
            new Employee("张三", 20, 5555),
            new Employee("李四", 20, 6666),
            new Employee("王五", 30, 7777),
            new Employee("赵六", 28, 3333),
            new Employee("田七", 40, 8888)
    );

    @Test
    public void test1() {
        list.sort((o1, o2) -> {
            if (o1.getAge() == o2.getAge()) {
                return o1.getName().compareTo(o2.getName());
            }
            return o1.getAge() - o2.getAge();
        });
        list.forEach(System.out::println);
        System.out.println("--------------");
        //并行流
        list.stream()
                .sorted((o1, o2) -> {
                    if (o1.getAge() == o2.getAge()) {
                        return o1.getName().compareTo(o2.getName());
                    }
                    return o1.getAge() > o2.getAge() ? 1 : -1;
                })
                .forEach(System.out::println);
    }

    public String getValue(String str, FunGetValue fv) {
        return fv.getValue(str);
    }

    @Test
    public void test2() {
        String abc = getValue("abc", (str) -> str.toUpperCase());
        String subStr = getValue("abcdefg", (str) -> str.substring(2, 4));
        System.out.println(abc);
        System.out.println(subStr);
    }

    public Long getValue(Long l, Long r, FunTR<Long, Long> funTR) {
        return funTR.rValue(l, r);
    }

    @Test
    public void test3() {
        Long value = getValue(10l, 103l, (t, t1) -> t + t1);
        System.out.println(value);
        Long value1 = getValue(100l, 200l, (t, t1) -> t1 * t);
        System.out.println(value1);
    }
}
