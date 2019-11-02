package cn.dxxy.lambda.baseLambda;

import org.junit.Test;

import java.util.function.Consumer;

public class TestLambda2 {


    @Test
    public void test1() {
        Consumer<Integer> consumer = (t) -> System.out.println(t);
        consumer.accept(10);
    }

    @Test
    public void test2() {
        Integer value = getValue(100, t -> t + 100);
        System.out.println(value);
        Integer value1 = getValue(200, t -> t * t);
        System.out.println(value1);

    }

    public Integer getValue(Integer i, MyFun<Integer> mf) {
        return mf.operation(i);
    }



}
