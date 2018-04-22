package sample.testng;

import org.testng.annotations.Test;

//因方法testMethodTwo中enabled=false所以此方法在执行的时候会被忽略
public class DisableTestClass {
    @Test(enabled = true)
    public void testMethodOne() {
        System.out.println("Test method one.");
    }

    @Test(enabled = false)
    public void testMethodTwo() {
        System.out.println("Test method two.");
    }

    @Test
    public void testMethodThree() {
        System.out.println("Test method three.");
    }
}
