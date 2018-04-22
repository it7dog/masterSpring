package sample.testng;

import org.testng.annotations.Test;
//timeTestOne因未在配置时间内完成，所以会执行不通过
public class TimeSuite {
    @Test
    public void timeTestOne() throws InterruptedException{
        Thread.sleep(1000);
        System.out.println("Time test method one");
    }

    @Test
    public void timeTestTwo() throws InterruptedException{
        Thread.sleep(400);
        ;

    }
}
