package cn.dxxy.interfacedemo;

/**
 * 如果子类实现的接口中，有重复的接口方法，这个时候，编译器会强制子类实现该方法，并指定该方法是使用那个接口中的方法
 */
public class SubClass2 implements MyInterface, MyInterface2 {
    @Override
    public String getName() {
        return MyInterface2.super.getName();
        //return MyInterface.super.getName();
    }

    public static void main(String[] args) {
        SubClass2 subClass2 = new SubClass2();
        System.out.println(subClass2.getName());
        //接口中的静态方法
        MyInterface.myPrint();
    }
}
