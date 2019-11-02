package cn.dxxy.stream;

import cn.dxxy.entity.Employee;
import cn.dxxy.utils.Util;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

/**
 * 1---4筛选和切片
 * 5-- 映射
 * 6 -- 排序
 */
public class TestSteam2 {
    //操作流
    List<Employee> emps = Util.empList;

    @Test
    public void test1() {
        //如果代码中只有中间操作，没有终止操作，那么该流将不会被执行。
        emps.stream().filter((x) -> {
            System.out.println("被处理！！");
            return x.getSalary() > 5000;
        });
        //加上终止操作,循环的方式是内循环。
        emps.stream()
                .filter((x) -> {
                    System.out.println("+终止操作  被处理！！");
                    return x.getSalary() > 5000;
                })
                .forEach(System.out::println);
    }

    @Test
    public void test2() {
        //limit  当找到了满足了所有的限制条件后，结束过滤。提高性能
        emps.stream()
                .filter((x) -> {
                    System.out.println("短路");
                    return x.getAge() > 20;
                })
                .limit(2)
                .forEach(System.out::println);
    }

    @Test
    public void test3() {
        //skip 跳过操作。和Limit 互补
        emps.stream()
                .filter((e) -> e.getSalary() > 5000)
                .skip(2)
                .forEach(System.out::println);
    }

    @Test
    public void test4() {
        // distinct 过滤操作，通过对象的hashCode 和equals 方法来过滤相同的
        emps.stream()
                .filter((e) -> e.getAge() > 30)
                .distinct()
                .forEach(System.out::println);
    }

    @Test
    public void test5() {
        //map  只能解决一维数据，如果Stream中存在stream 那么存在于其中的流会被直接会被打印出内存地址
        Stream<String> s = Stream.of("aa", "bb", "cc");
//        s.map(String::toUpperCase)
//                .forEach(System.out::println);
        //一个流只要进行了终止操作，那么即使有引用指向他，流一样会被关闭。
        System.out.println("-----------------------");
        s.map((e) -> getCharacter(e)).forEach((e) -> {
            e.forEach(System.out::println);
        });
        System.out.println("<-------------------->");
        //flatMap 专门用于合并多个流
        Stream.of("asdas", "casdas", "abcd")
                .flatMap(TestSteam2::getCharacter)
                .forEach(System.out::println);

    }


    public static Stream<Character> getCharacter(String str) {
        List<Character> list = new ArrayList<>();
        for (Character ch : str.toCharArray()) {
            list.add(ch);
        }
        return list.stream();
    }

    @Test
    public void test6() {
        //排序
        // sorted 字典序
        Stream.of("aa", "cc", "bb", "dd")
                .sorted()
                .forEach(System.out::println);
        System.out.println("<------------------------->");
        //sorted(Comparator ) 按照我们自定义的排序
        emps.stream().sorted((x, y) -> {
            if (x.getAge() == y.getAge()) {
                return x.getName().compareTo(y.getName());
            }
            return x.getAge() - y.getAge();
        }).forEach(System.out::println);

    }

}
