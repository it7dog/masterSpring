package sample.testng;


import org.testng.annotations.Test;
import java.io.IOException;

// 方法exceptionTestTwo 返回的异常类与期望的异常类不一致，方法exceptionTestThree返回的异常信息与期望的异常信息不一致所有这两个方法会执行报错
public class ExceptionTest {
    @Test(expectedExceptions = {IOException.class})
    public void exceptionTestOne() throws Exception {
        throw new IOException();
    }

    @Test(expectedExceptions = {IOException.class, NullPointerException.class})
    public void exceptionTestTwo() throws Exception {
        throw new Exception();
    }

    @Test(expectedExceptions = {IOException.class},expectedExceptionsMessageRegExp = "Pass Message test")
    public void exceptionTestThree() throws Exception {
        throw new IOException("Pass Message test1");

    }

    @Test(expectedExceptions = {IOException.class}, expectedExceptionsMessageRegExp = ".*")
    public void exceptionTestFoure() throws Exception {
        throw new IOException("Pass Message test1");
    }
}
