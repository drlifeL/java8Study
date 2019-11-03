package cn.dxxy.time;

import org.junit.Test;

import java.time.*;
import java.time.format.DateTimeFormatter;


//  LocalDate 、LocalTime、 LocalDateTime
public class TestDateTime {

    // LocalDateTime
    @Test
    public void test1() {
        //获取当前的时间
        LocalDateTime now = LocalDateTime.now();
        System.out.println(now);
        //根据指定时间创建LocalDateTime
        LocalDateTime createByInput = LocalDateTime.of(2018, 11, 20, 0, 0);
        System.out.println(createByInput);

        //获取当前年、月、日
        int year = now.getYear();
        int monthValue = now.getMonthValue();
        int dayOfMonth = now.getDayOfMonth();
        System.out.println(year + "::" + monthValue + "::" + dayOfMonth);
    }

    //Instant 时间戳 ，从1970 1 1 0 0 0
    @Test
    public void test2() {
        //这个获取的是UTC时间，也就是格林威治时间
        Instant now = Instant.now();
        //获取当前的时间戳，并转换成毫秒
        System.out.println(now.toEpochMilli());

        System.out.println(now);
        //转换到北京时间需要偏移8个时区，我们可以设置其偏移量
        OffsetDateTime offsetDateTime = now.atOffset(ZoneOffset.ofHours(8));
        System.out.println(offsetDateTime);
    }

    //Duration 持续时间  用于计算两个"时间"间隔
    // Period 周期   用于计算两个"日期"间隔
    @Test
    public void test3() {
        // period 用法一致
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime createByInput = LocalDateTime.of(2018, 11, 20, 0, 0);
        Duration between = Duration.between(now, createByInput);
        System.out.println(between);
        long l = between.toDays();//相差多少天
        System.out.println(l);
        System.out.println(between.toHours());//相差多少小时
        System.out.println(between.toMinutes());//相差的分钟
        System.out.println(between.toMillis());//相差的毫米数
    }

    //DateTimeFormatter
    @Test
    public void test4(){
        LocalDate ld = LocalDate.now();
        System.out.println(ld);
        //指定格式的格式化
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy年MM月dd日");
        //将其格式化成字符串
        String format = ld.format(dtf);
        System.out.println(format);

        //将字符串反格式化
        LocalDate parse = ld.parse(format, dtf);
        System.out.println(parse);
    }


}
