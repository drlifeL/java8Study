# java8Study
       java8 新特征学习 跟随 尚硅谷 李贺飞老师的视频敲打。
##  一、Lambda表达式
- 方法引用
- 构造器引用
- 数组引用

## 二、强大的StreamAPI
- 创建stream
- Stream的中间操作
- 终端(终止)操作
   
## 三、并行流和顺序流
- 原理
- 切换

## 四、Optional类 (用于避免空指针异常)
- 常用方法

## 五、接口中的默认方法与静态方法
- 接口中允许出现默认方法
   - 接口中的方法和类中的方法冲突时，**优先选择类方法**
   - 两个接口之间的方法冲突，要求类必须重写该方法，或者指定调用哪个接口中的方法。
- 静态方法

## 六、新时间日期API 
- java.time
   - LocalDate
   - LocalTime
   - LocalDateTime
- 工具类
   - Instant 时间戳
   - Duration 用于计算两个"时间"间隔
   - Period：用于计算两个"日期"间隔
   - TemporalAdjuster:时间校正器。（将日期调整到下个工作日）
   - TemporalAdjusters:该类通过静态方法提供了大量的常用TemporalAdjuster的实现
- 解析格式化 java.time.format.DateTimeFormatter
   - 预定义的标准格式
   - 语言环境相关的格式
   - 自定义的格式
- 时区处理
   - ZonedDate
   - ZonedTime
   - ZonedDateTime
## 七、重复注解与类型注解
- 可重复的注解
- 可用于类型的注解

