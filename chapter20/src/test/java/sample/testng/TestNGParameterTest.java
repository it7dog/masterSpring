package sample.testng;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import static org.testng.Assert.*;

public class TestNGParameterTest {
    private SimpleDateFormat simpleDateFormat;

    @DataProvider(name="testParam")
    public static Object[][] getParamters(){
        String [][] params = {
                {"2016-02-01 00:30:59","yyyyMMdd","20160201"},
                {"2016-02-01 00:30:59","yyyy年MM月dd日","2016年02月01日"},
                {"2016-02-01 00:30:59","HH时mm分ss秒","00时30分59秒"},

        };
        return  params;
    }

    @Test(dataProvider = "testParam")
    public void testSimpleDateFormat(String date,String dateformat,String expectedDate) throws ParseException {
        SimpleDateFormat df =new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date d = df.parse(date);
        simpleDateFormat = new SimpleDateFormat(dateformat);
        String result = simpleDateFormat.format(d);
        assertEquals(result,expectedDate);
    }
}
