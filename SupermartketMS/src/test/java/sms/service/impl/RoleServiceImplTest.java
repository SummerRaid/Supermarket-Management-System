package sms.service.impl;

import junit.framework.TestCase;
import myssm.trans.TransactionManager;
import sms.pojo.Role;
import sms.pojo.Shop;

import java.util.List;

public class RoleServiceImplTest extends TestCase {

    RoleServiceImpl roleService;

    public void setUp() throws Exception {
        super.setUp();
        TransactionManager.beginTrans();
        ShopServiceImpl shopService = new ShopServiceImpl();
        OrderServiceImpl orderService = new OrderServiceImpl();
        orderService.setShopService(shopService);
        UserServiceImpl userService = new UserServiceImpl();
        userService.setOrderService(orderService);
        roleService = new RoleServiceImpl();
        roleService.setUserService(userService);
    }

    public void tearDown() throws Exception {
        TransactionManager.rollback();
        roleService = null;
    }

    public void testGetAllRoles() {
        List<Role> allRoles = roleService.getAllRoles(1);
        allRoles.forEach(r -> {
            System.out.println(r);
            assertEquals(Integer.valueOf(1), r.getShop().getId());
        });
    }

    public void testGetRole() {
        Role role = roleService.getRole(1);
        System.out.println("role = " + role);
        assertEquals(Integer.valueOf(1), role.getId());
    }

    public void testAddRole() {
        Role role = new Role("testRole", "lala", "111111111", new Shop(2));
        roleService.addRole(role);
        Role role1 = roleService.getRole(role.getId());
        assertEquals(role1.getId(), role.getId());
        assertEquals(role1.getAuthority(), role.getAuthority());
        assertEquals(role1.getName(), role.getName());
        assertEquals(role1.getRemark(), role1.getRemark());
        assertEquals(role1.getShop().getId(), role.getShop().getId());
    }

    public void testDelRole() {
        Role role = roleService.getRole(1);
        System.out.println("role = " + role);
        roleService.delRole(1);
        Role role1 = roleService.getRole(1);
        assertNull(role1);
    }

    public void testAuthorize() {

    }
}