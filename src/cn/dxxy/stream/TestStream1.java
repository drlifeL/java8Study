package cn.dxxy.stream;

import cn.dxxy.entity.Employee;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

/**
 * 1.创建Stream
 * 2.中间操作
 * 3.终止操作
 */
public class TestStream1 {

    //创建流
    @Test
    public void test1(){
        //1.通过Collection系列集合提供的Stream方法
        List<String> list = new ArrayList<>();
        Stream<String> stream = list.stream();

        //2.通过Arrays的静态方法Stream来生成数组的流
        Employee[] arr = new Employee[10];
        Stream<Employee> stream1 = Arrays.stream(arr);

        //3.通过Stream中的静态方法of
        Stream<String> abc = Stream.of("abc", "dev", "cdd");
        abc.forEach(System.out::println);

        //4.创建无限流,  迭代   如果不加过滤，将会一直运行下去
        // UnaryOperator
        Stream.iterate(0.1,Math::ceil)
                .limit(10)
                .forEach(System.out::println);

        //无限流--->生成-->Supplier
        Stream.generate(Math::random)
                .limit(5)
                .forEach(System.out::println);
    }

}
