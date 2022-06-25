package sms.service.impl;

import junit.framework.TestCase;
import sms.pojo.Role;
import sms.pojo.User;

import java.util.List;

public class UserServiceImplTest extends TestCase {

    UserServiceImpl userService;

    public void setUp() throws Exception {
        super.setUp();
        ShopServiceImpl shopService = new ShopServiceImpl();
        OrderServiceImpl orderService = new OrderServiceImpl();
        orderService.setShopService(shopService);
        userService = new UserServiceImpl();
        userService.setOrderService(orderService);
    }

    public void tearDown() throws Exception {
        userService = null;
    }

    public void testLogin() {
        User user = userService.login("lina", "ok");
        System.out.println("user = " + user);
        assertEquals("lina", user.getUname());
        assertEquals("ok", user.getPwd());
    }

    public void testGetUser() {
        User tony = userService.getUser("tony");
        System.out.println("tony = " + tony);
        assertEquals("tony", tony.getUname());
        assertEquals("ok", tony.getPwd());
    }

    public void testRegister() {
        User testUser = new User("testUname", "1234", new Role(1));
        User user = userService.register(testUser);
        System.out.println("user = " + user);
        User testUname = userService.getUser("testUname");
        System.out.println("testUname = " + testUname);
        assertEquals(testUname.getUname(), user.getUname());
        assertEquals(testUname.getPwd(), user.getPwd());
        assertEquals(testUname.getRole().getId(), user.getRole().getId());
        assertEquals(testUname.getId(), user.getId());
    }

    public void testGetAllUsers() {
        List<User> allUsers = userService.getAllUsers(1);
        allUsers.forEach(u -> {
            System.out.println(u);
            assertEquals(Integer.valueOf(1), u.getRole().getShop().getId());
        });
    }

    public void testUpdateUser() {
        User tony = userService.getUser("tony");
        System.out.println("tony = " + tony);
        tony.setUname("Don Quixote");
        userService.updateUser(tony);
        User don_quixote = userService.getUser("Don Quixote");
        System.out.println(don_quixote);
        assertEquals(tony.getPwd(), don_quixote.getPwd());
        assertEquals(tony.getId(), don_quixote.getId());
    }

    public void testDelUser() {
        User tony = userService.getUser("tony");
        System.out.println("tony = " + tony);
        userService.delUser(tony.getId());
        User tony1 = userService.getUser("tony");
        assertNull(tony1);
    }

    public void testGetUserByRole() {
        List<User> user = userService.getUserByRole(1);
        user.forEach(u -> {
            System.out.println(u);
            assertEquals(u.getRole().getId(), Integer.valueOf(1));
        });
    }
}