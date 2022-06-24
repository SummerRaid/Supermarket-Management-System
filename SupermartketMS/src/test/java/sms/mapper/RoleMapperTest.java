package sms.mapper;

import junit.framework.TestCase;
import myssm.basedao.MybatisSingleton;
import myssm.util.MapperUtil;
import sms.pojo.Role;
import sms.pojo.Shop;
import sms.pojo.User;

import java.util.List;

public class RoleMapperTest extends TestCase {
    private RoleMapper roleMapper;
    private UserMapper userMapper;

    public void setUp() throws Exception {
        super.setUp();
        roleMapper = MapperUtil.getProxy(RoleMapper.class);
        userMapper = MapperUtil.getProxy(UserMapper.class);
    }

    public void tearDown() throws Exception {
        MybatisSingleton.getConn().rollback();
        MybatisSingleton.closeConn();
    }

    public void testSelectAll() {
        List<Role> roles = roleMapper.selectAll(1);
        roles.forEach(r -> {
            System.out.println(r);
            assertEquals(Integer.valueOf(1), r.getShop().getId());
        });
    }

    public void testSelectById() {
        Role role = roleMapper.selectById(1);
        System.out.println("role = " + role);
        assertEquals(Integer.valueOf(1), role.getId());
    }

    public void testAdd() {
        Role role = new Role("名称", "备注", "000000000", new Shop(1));
        Integer id = roleMapper.add(role);
        role = roleMapper.selectById(role.getId());
        System.out.println("role = " + role);
        assertEquals("名称", role.getName());
        assertEquals("备注", role.getRemark());
        assertEquals("000000000", role.getAuthority());
        assertEquals(Integer.valueOf(1), role.getShop().getId());
    }

    public void testDel() {
        Role role = roleMapper.selectById(1);
        System.out.println("before deleted: role = " + role);
        userMapper.delByRoleId(1);
        List<User> users = userMapper.selectAll(1);
        users.forEach(System.out::println);
        roleMapper.del(1);
        role = roleMapper.selectById(1);
        System.out.println("after deleted: role = " + role);
        assertNull(role);
    }

    public void testUpdate() {
        Role role = roleMapper.selectById(1);
        System.out.println("before update: role = " + role);
        Role role1 = new Role("名称", "备注", "000000000", new Shop(1));
        role1.setId(1);
        roleMapper.update(role1);
        role = roleMapper.selectById(1);
        System.out.println("after update: role = " + role);
        assertEquals(Integer.valueOf(1), role.getId());
        assertEquals("名称", role.getName());
        assertEquals("备注", role.getRemark());
        assertEquals("000000000", role.getAuthority());
        assertEquals(Integer.valueOf(1), role.getShop().getId());
    }
}