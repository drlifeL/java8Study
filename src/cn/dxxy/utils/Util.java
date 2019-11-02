package cn.dxxy.utils;

import cn.dxxy.entity.Employee;

import java.util.Arrays;
import java.util.List;

/**
 * 工具类
 */
public class Util {
    public static List<Employee> empList = Arrays.asList(
            new Employee("张三", 20, 5555, Employee.Status.BUSY),
            new Employee("王五", 30, 7777, Employee.Status.VOCATION),
            new Employee("李四", 20, 6666, Employee.Status.FREE),
            new Employee("赵六", 28, 3333, Employee.Status.BUSY),
            new Employee("田七", 40, 8888, Employee.Status.FREE),
            new Employee("周八", 55, 8888, Employee.Status.FREE)
    );
}
