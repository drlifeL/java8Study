package cn.dxxy.lambda.methodReference;

import cn.dxxy.entity.Employee;
import org.junit.Test;

import java.io.PrintStream;
import java.util.function.*;

/**
 * 方法引用
 * 实例：：实例方法
 * 类：：静态方法
 * // 如果方法中的一个参数作为调用者，第二个参数作为方法的参数列表就可以使用类名：：实例方法
 * 类：：实例方法
 */
public class LambdaReferance {

    @Test
    public void test1() {
        //注意：其方法参数和返回值类型必须一致
        PrintStream ps = System.out;
        //对象：：实例方法
        Consumer<String> consumer = ps::println;
        consumer.accept("absbdad");
    }

    @Test
    public void test2() {
        Employee employee = new Employee();
        Supplier<String> sp1 = () -> employee.getName();
        System.out.println(sp1.get());
        Supplier<Integer> sp2 = employee::getAge;
        System.out.println(sp2.get());
    }


    @Test
    public void test3() {
        //类名。静态方法
        Function<String, Integer> function = Integer::parseInt;
        Integer apply = function.apply("100");
        System.out.println(apply);
    }

    @Test
    public void test4() {
        //类名：：实例方法
        BiPredicate<String, String> bi = String::equals;
        boolean test = bi.test("abc", "abc");
        System.out.println(test);
    }

    @Test
    public void test5() {
        //构造器引用
        Supplier<Employee> supplier = Employee::new;
        Employee employee = supplier.get();
        System.out.println(employee);
        //带一个参数
        Function<Integer, Employee> fn = Employee::new;
        Employee apply = fn.apply(3);
        System.out.println(apply);
        //带两个参数
        BiFunction<String, Integer, Employee> bifun = Employee::new;
        Employee emp = bifun.apply("张三", 20);
        System.out.println(emp);
        //数组引用：：其实就是构造器引用
        Function<Integer,String[]> fun = String[]::new;
        String[] apply1 = fun.apply(10);
        System.out.println(apply1.length);
    }
}
