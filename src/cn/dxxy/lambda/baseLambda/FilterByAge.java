package cn.dxxy.lambda.baseLambda;

import cn.dxxy.entity.Employee;

public class FilterByAge implements MyRule<Employee> {
    @Override
    public boolean isOK(Employee obj) {
        return obj.getAge() >= 20;
    }
}
