
package sample.testng;
import com.smart.domain.User;
import org.testng.annotations.*;
import static org.testng.Assert.*;


public class TestNGDependsTest {

    @Test
    public void testMethod1() {
    }

    @Test
    public void testMethod2() {
        assertNotNull(new User());
    }

    //alwaysRun 如果testMethod1或者testMethod2的任何一个方法测试失败，则testMethod3将不会被执行。只有依赖的方法
    //测试全部通过时，当前方法才会被调用执行，这种依赖关系被成为依赖，也是TestNG默认的依赖关系。可以通过@Test提供的alwaysRun属性来改变这种强依赖，设置
    //alwaysRun=true之后，不管testMethod1或者testMethod2有没有功过测试，testMethod3总会被执行，这种关系被称为软依赖。
    @Test(dependsOnMethods = {"testMethod1","testMethod2"},alwaysRun=true)
    public void testMethod3() {

    }

}
