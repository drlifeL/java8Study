package cn.dxxy.demo;

import cn.dxxy.entity.Employee;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

/**
 * 使用java给我们定义好的部分函数式接口
 * Consumer<T></> 消费型接口
 * <p>
 * Supplier<T></> 供给型接口
 * <p>
 * Function<T,R></>  函数型接口
 * <p>
 * Predicate  断言型接口
 */
public class LambdaTestFunction1 {


    List<Employee> list = Arrays.asList(
            new Employee("张三", 20, 5555),
            new Employee("李四", 20, 6666),
            new Employee("王五", 30, 7777),
            new Employee("赵六", 28, 3333),
            new Employee("田七", 40, 8888)
    );

    //消费型
    @Test
    public void test1() {
        getValue(50, (c) -> System.out.println("这边传递过来的数值是" + c));

    }

    public void getValue(Integer num, Consumer<Integer> cs) {
        cs.accept(num);

    }

    //供给型
    @Test
    public void test2() {
        List<Integer> list = getRandomNum(5, () -> (int) (Math.random() * 100));
        list.forEach(System.out::println);

    }


    public List<Integer> getRandomNum(int num, Supplier<Integer> sp) {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < num; i++) {
            list.add(sp.get());
        }
        return list;
    }


    //函数型接口
    @Test
    public void test3() {
        List<String> list = getAllNameForList(this.list, (t) -> t.getName());
        list.stream().forEach(System.out::println);
    }

    public List<String> getAllNameForList(List<Employee> list, Function<Employee, String> function) {
        List<String> lists = new ArrayList<>();
        for (Employee employee : list) {
            lists.add(function.apply(employee));
        }
        return lists;
    }

    //断言型接口
    @Test
    public void test4() {
        List<Employee> emps = getEmpsBySalary(list, (t) -> t.getSalary() > 6000);
        emps.forEach(System.out::println);
    }

    public List<Employee> getEmpsBySalary(List<Employee> list, Predicate<Employee> pc) {
        List<Employee> lists = new ArrayList<>();
        for (Employee employee : list) {
            if (pc.test(employee))
                lists.add(employee);
        }
        return lists;
    }


}
