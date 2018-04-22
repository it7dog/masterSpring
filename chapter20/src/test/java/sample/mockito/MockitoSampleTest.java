package sample.mockito;

import com.smart.domain.User;
import com.smart.service.UserService;
import com.smart.service.UserServiceImpl;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.mockito.Mockito.*;
import static org.testng.Assert.*;

public class MockitoSampleTest {
    //对接口进行模拟
    UserService mockUserService = mock(UserService.class);
    //对类进行模拟
    UserServiceImpl mockServiceImpl = mock(UserServiceImpl.class);
    //基于注解模拟类
    @Mock
    User mockUser;

    @BeforeClass
    public void initMocks() {
        //初始化当前测试类所有@Mock注解模拟对象
        MockitoAnnotations.initMocks(this);
    }

    //模拟接口UserService测试
    @Test
    public void testMockInterface() {
        //对方法设定返回值
        when(mockUserService.findUserByUserName("tom")).thenReturn(new User("tom", "1234"));
        //对方法设定返回值
        doReturn(true).when(mockUserService).hasMatchUser("tom", "1234");

        // 对void方法进行方法预期值设定
        User u = new User("John", "1234");
        doNothing().when(mockUserService).registerUser(u);

        // 执行方法调用
        User user = mockUserService.findUserByUserName("tom");
        boolean isMatch = mockUserService.hasMatchUser("tom","1234");
        mockUserService.registerUser(u);

        assertNotNull(user);
        assertEquals(user.getUserName(), "tom");
        assertEquals(isMatch, true);

        // 验证交互行为
        verify(mockUserService).findUserByUserName("tom");
        // 验证方法至少调用一次
        verify(mockUserService, times(1)).findUserByUserName("tom");
        verify(mockUserService, atLeastOnce()).findUserByUserName("tom");
        verify(mockUserService, atLeast(1)).findUserByUserName("tom");
        //验证方法至多调用一次
        verify(mockUserService, atMost(1)).findUserByUserName("tom");

        verify(mockUserService).hasMatchUser("tom", "1234");
        verify(mockUserService).registerUser(u);
    }

    //模拟实现类UserServiceImpl测试
    @Test
    public void testMockClass(){

        // 对方法设定返回值
        when(mockServiceImpl.findUserByUserName("tom")).thenReturn(new User("tom", "1234"));
        doReturn(true).when(mockServiceImpl).hasMatchUser("tom", "1234");

        User user = mockServiceImpl.findUserByUserName("tom");
        boolean isMatch = mockServiceImpl.hasMatchUser("tom","1234");
        assertNotNull(user);
        assertEquals(user.getUserName(), "tom");
        assertEquals(isMatch, true);

        verify(mockServiceImpl).findUserByUserName("tom");
        verify(mockServiceImpl).hasMatchUser("tom", "1234");
    }
    //模拟User类测试
    @Test
    public void testMockUser(){
        MockitoAnnotations.initMocks(mockUser);
        when(mockUser.getUserId()).thenReturn(1);
        when(mockUser.getUserName()).thenReturn("tom");
        assertEquals(mockUser.getUserId(),1);
        assertEquals(mockUser.getUserName(), "tom");
    }
}
