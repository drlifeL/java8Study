package cn.dxxy.interfacedemo;

//子类继承的父类中有getName()方法，接口中也有getName()方法，这个时候是使用类的方法
public class SubClass extends MyClass implements MyInterface {
    public static void main(String[] args) {
        SubClass subClass = new SubClass();
        String name = subClass.getName();
        System.out.println(name);

    }
}
