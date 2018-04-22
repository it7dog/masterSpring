package sample.testng;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import  static org.testng.Assert.*;

public class MoneyTest {
    private double f12CHF;
    private double f14CHF;
    private double f28USD;

    @BeforeMethod
    protected void setUp(){
        System.out.println("测试方法执行之前");
        f12CHF = 12;
        f14CHF=14;
        f28USD=28;

    }
    @Test
    public void moneyBag(){
        assertEquals(f28USD,f12CHF+f14CHF);
    }
    @AfterMethod
    protected void tearDown(){
        System.out.print("测试方法执行之后");
    }


}
