package cn.dxxy.demo;

@FunctionalInterface
public interface FunTR<T, R> {
    R rValue(T t,T t1);
}
