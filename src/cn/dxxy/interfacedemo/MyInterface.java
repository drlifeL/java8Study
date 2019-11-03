package cn.dxxy.interfacedemo;

/***
 * java 8 中允许有默认的接口方法实现
 * 而且如果有类实现了有默认方法的接口，接口中的默认方法不强制类进行实现
 *  java8中接口中允许存在静态方法
 */
public interface MyInterface {

    default String getName() {
        return "MyInterface";
    }

    static void myPrint(){
        System.out.println("接口中的静态方法！");
    }

}