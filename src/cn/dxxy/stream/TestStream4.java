package cn.dxxy.stream;

import org.junit.Test;

import java.time.Instant;
import java.util.stream.LongStream;

public class TestStream4 {
    @Test
    public void test1() {
        //Java 8 的新时间API
        Instant start = Instant.now();
        //计算从0 加到100000000000L
        long reduce = LongStream.rangeClosed(0, 100000000000L)
                //并行流和顺序流
                .parallel()
                .reduce(0, Long::sum);
        Instant end = Instant.now();

    }
}
