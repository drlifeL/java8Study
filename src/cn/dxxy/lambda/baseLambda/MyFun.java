package cn.dxxy.lambda.baseLambda;

@FunctionalInterface
public interface MyFun<T> {
    T operation(T t);
}
