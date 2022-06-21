package sms.mapper;

import junit.framework.TestCase;
import myssm.basedao.MybatisSingleton;
import myssm.util.MapperUtil;
import sms.pojo.Role;
import sms.pojo.User;

import java.util.List;

public class UserMapperTest extends TestCase {

    private UserMapper userMapper;

    public void setUp() throws Exception {
        super.setUp();
        userMapper = MapperUtil.getProxy(UserMapper.class);
    }

    public void tearDown() throws Exception {
        MybatisSingleton.getConn().rollback();
        MybatisSingleton.closeConn();
    }

    public void testSelectAll() {
        List<User> users = userMapper.selectAll(1);
        users.forEach(u -> {
            System.out.println(u);
            assertEquals(Integer.valueOf(1), u.getRole().getShop().getId());
        });
    }

    public void testSelectByRole() {
        List<User> users = userMapper.selectByRole(1);
        users.forEach(u -> {
            System.out.println(u);
            assertEquals(Integer.valueOf(1), u.getRole().getId());
        });
    }

    public void testSelectById() {
        User user = userMapper.selectById(1);
        System.out.println("user = " + user);
        assertEquals(Integer.valueOf(1), user.getId());
    }

    public void testAdd() {
        User user = new User(new Role(1), "测试名称", "测试密码", "测试姓名", "测试电话", "地址");
        Integer id = userMapper.add(user);
        User user1 = userMapper.selectById(id);
        System.out.println("user = " + user1);
        assertEquals(user.getUname(), user1.getUname());
        assertEquals(user.getRole().getId(), user1.getRole().getId());
        assertEquals(user.getPwd(), user1.getPwd());
        assertEquals(user.getTname(), user1.getTname());
        assertEquals(user.getAddress(), user1.getAddress());
        assertEquals(user.getTel(), user1.getTel());
        assertEquals(id, user1.getId());

    }

    public void testDel() {
        User user = userMapper.selectById(1);
        System.out.println("before deleted: user = " + user);
        userMapper.del(1);
        user = userMapper.selectById(1);
        System.out.println("after deleted: user = " + user);
    }

    public void testUpdate() {
        User user = userMapper.selectById(1);
        System.out.println("before update: user = " + user);
        User user1 = new User(new Role(1), "测试名称", "测试密码", "测试姓名", "测试电话", "地址");
        user1.setId(1);
        userMapper.update(user1);
        user = userMapper.selectById(1);
        System.out.println("after update: user = " + user);
        assertEquals(user.getUname(), user1.getUname());
        assertEquals(user.getRole().getId(), user1.getRole().getId());
        assertEquals(user.getPwd(), user1.getPwd());
        assertEquals(user.getTname(), user1.getTname());
        assertEquals(user.getAddress(), user1.getAddress());
        assertEquals(user.getTel(), user1.getTel());
        assertEquals(Integer.valueOf(1), user.getId());
    }

    public void testSelectByName() {
        User user = new User(new Role(1), "测试名称", "测试密码", "测试姓名", "测试电话", "地址");
        Integer id = userMapper.add(user);
        User user1 = userMapper.selectByName("测试名称");
        User user2 = userMapper.selectById(id);
        assertEquals(user2.getUname(), user1.getUname());
        assertEquals(user2.getRole().getId(), user1.getRole().getId());
        assertEquals(user2.getPwd(), user1.getPwd());
        assertEquals(user2.getAddress(), user1.getAddress());
        assertEquals(user2.getTname(), user1.getTname());
        assertEquals(user2.getTel(), user1.getTel());
        assertEquals(user2.getId(), user1.getId());
    }
}