package cn.dxxy.optional;

import cn.dxxy.entity.Employee;
import org.junit.Test;

import java.util.Optional;

/**
 * Optional类常用方法的demo
 * 该类是为了尽可能的避免空指针异常
 */
public class OptionalDemo {

    /*
    	Optional.of(T t) :创建一个Optional实例
        Optional.empty():创建一个空的Optional实例
        Optional.ofNullable(T t): 若 t 不为Null,创建Optional实例，否则创建空实例
        isPresent():是否包含值
        orElse（T t）：如果调用对象包含值，返回该值，否则返回t
        orElseGet(Suplier s):如果调用对象包含值，返回该值，否则返回s获取的值
        map(Funciton f):如果有值对其处理，并返回处理后的Optional，否则返回Optional.empty()
        flatMap(Function mapper):与map 类似，要求返回值必须是Optional
     */
    @Test
    public void test1() {
        //of 方法如果传入的是一个Null则会直接报异常
        //Optional<Employee> op1 = Optional.of(null);

        Optional<Employee> op = Optional.of(new Employee());
        Employee employee = op.get();
        System.out.println(employee);

        //创建一个空的optional实例
        Optional<Object> empty = Optional.empty();
        //System.out.println(empty.get());

        //ofNullable(T t)  如果t ==null?Optional.empty()：t
        Optional<Employee> op1 = Optional.of(new Employee());
        System.out.println(op1.get());


    }

    @Test
    public void test2() {
        //有时我们在使用的时候想知道这个op到底有没有值
        Optional<Employee> op = Optional.ofNullable(new Employee());
        //可以使用isPresent()方法来进行判断
        System.out.println(op.isPresent());

        //如果op对象有值则返回该值，没有则返回传入的值
        Optional<Employee> o = Optional.empty();
        Employee employee = o.orElse(new Employee(18));
        System.out.println(employee);

        // orElseGet 方法效果同上一个相同，但是要求的参数是一个供给型接口，而上一个方法是一个Object
        Optional<Employee> op1 = Optional.empty();
        Employee employee1 = op1.orElseGet(Employee::new);
        System.out.println(employee1);

    }

    @Test
    public void test3() {
        // 演示Map  FlatMap
        Optional<Employee> op = Optional.of(new Employee("张三", 18));

        Optional<String> s = op.map(Employee::getName);

        System.out.println(s.get());

        //flatMap  flatMap同Map的不同点是，FlatMap的返回值必须要使用Optional 来进行包装。
        Optional<String> s1 = op.flatMap((x) -> Optional.of(x.getName()));
        System.out.println(s1.get());

    }


}
