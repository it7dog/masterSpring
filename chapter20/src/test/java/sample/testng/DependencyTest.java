package sample.testng;

import org.testng.annotations.Test;

public class DependencyTest {
    //方法依赖
    @Test(dependsOnMethods={"testTwoMethod"})
    public void testOneMethod(){

        System.out.println("Test One Method");
    }
    //组依赖
    @Test(groups={"group-one"},dependsOnGroups={"group-two"})
    public void testTwoMethod(){
        System.out.println("Test Two Method");
    }

    @Test(groups={"group-two"})
    public void testThreeMethod(){
        System.out.println("Test Three Method");
    }
    //依赖组支持正则匹配
    @Test(dependsOnGroups={".*up-th.*"})
    public void testFourMethod(){
        System.out.println("Test Four Method");
    }

    @Test(groups={"group-three"})
    public void testFiveMethod(){
        System.out.println("Test Five Method");
    }
}
