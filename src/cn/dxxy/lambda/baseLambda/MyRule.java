package cn.dxxy.lambda.baseLambda;

import cn.dxxy.entity.Employee;

@FunctionalInterface
public interface MyRule<T> {
    boolean isOK(T obj);
}
