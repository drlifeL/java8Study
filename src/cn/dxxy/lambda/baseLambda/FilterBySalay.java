package cn.dxxy.lambda.baseLambda;

import cn.dxxy.entity.Employee;

public class FilterBySalay implements MyRule<Employee> {
    @Override
    public boolean isOK(Employee obj) {
        return obj.getSalary() >= 5000;
    }
}
