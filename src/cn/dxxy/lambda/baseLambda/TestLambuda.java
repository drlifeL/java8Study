package cn.dxxy.lambda.baseLambda;

import cn.dxxy.entity.Employee;
import org.junit.Test;

import java.util.*;

public class TestLambuda {

    @Test
    public void Test1() {
        //使用匿名内部类
        Comparator<Integer> com = new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1.compareTo(o2);
            }
        };
        TreeSet<Integer> treeSet = new TreeSet<>(com);
    }

    @Test
    public void test2() {
        //使用lambda表达式
        TreeSet<Integer> treeSet = new TreeSet<>((x, y) -> x.compareTo(y));

    }

    List<Employee> list = Arrays.asList(
            new Employee("张三", 18, 5555),
            new Employee("李四", 20, 6666),
            new Employee("王五", 30, 7777),
            new Employee("赵六", 28, 3333),
            new Employee("田七", 40, 8888)
    );

    //需要拿到员工中工资大于5000的员工

    public List<Employee> getEmployeeBySalay(List<Employee> emps) {
        List<Employee> list = new ArrayList<>();
        for (Employee emp : emps) {
            if (emp.getSalary() >= 5000) {
                list.add(emp);
            }
        }
        return list;
    }

    @Test
    public void test3() {
        //最容易想到的方法，但是如果这个时候我们要变更一下需求，我们就还需要重新写一个过滤方法，这样比较麻烦，于是我们想到了设计模式
        List<Employee> employees = getEmployeeBySalay(list);
        for (Employee employee : employees) {
            System.out.println(employee);
        }
    }


    //解决方法一：设计模式  使用策略模式
    public List<Employee> getEmployeeByRule(List<Employee> emps, MyRule<Employee> rule) {
        List<Employee> list = new ArrayList<>();
        for (Employee emp : emps) {
            if (rule.isOK(emp)) {
                list.add(emp);
            }
        }
        return list;
    }

    @Test
    public void test4() {
        //按照工资过滤
        List<Employee> employeeByRule = getEmployeeByRule(list, new FilterBySalay());
        for (Employee employee : employeeByRule) {
            System.out.println(employee);
        }
        //按照年龄过滤
        System.out.println("--------------------");
        List<Employee> rule = getEmployeeByRule(list, new FilterByAge());
        for (Employee employee : rule) {
            System.out.println(employee);
        }
    }

    //这个时候我们发现，每次都是要写一个实现类来实现过滤的策略，因为其只有只有一个方法，我们想到了匿名内部类

    @Test
    public void test5() {
        List<Employee> employeeByRule = getEmployeeByRule(list, new MyRule<Employee>() {
            @Override
            public boolean isOK(Employee obj) {
                return obj.getAge() <= 18;
            }
        });

        for (Employee employee : employeeByRule) {
            System.out.println(employee);
        }
        System.out.println("--------------------");
        //由匿名内部类，又想到了Lambda表达式

        List<Employee> rule = getEmployeeByRule(list, (e) -> e.getSalary() >= 7000);
        for (Employee employee : rule) {
            System.out.println(employee);
        }

        System.out.println("------------简便-------------");
        //还有更简便的
        List<Employee> employeeByRule1 = getEmployeeByRule(list, (e) -> e.getAge() >= 20);
        employeeByRule1.forEach(System.out::println);

        System.out.println("------------更简便-----------");
        //还有更简便的，串行流
        getEmployeeByRule(list, (e) -> e.getSalary() >= 6000)
                .stream()
                .forEach(System.out::println);
        System.out.println("--------------------------");
        //或者
        list.stream()
                .filter(e -> e.getAge() > 20)
                .forEach(System.out::println);

        System.out.println("---------还需要对结果进行过滤---------------");
        //只显示前面两条，并且只显示名字
//        getEmployeeByRule(list, (e) -> e.getAge() >= 25).stream()
//                .limit(2)
//                .map(employee -> employee.getName())
//                .forEach(System.out::println);
        //steam流操作
        list.stream()
                .filter(e->e.getAge()>=25)
                .map(e->e.getName())
                .limit(2)
                .forEach(System.out::println);

    }
}
