package sms.mapper;

import junit.framework.TestCase;
import myssm.basedao.MybatisSingleton;
import myssm.util.MapperUtil;
import sms.pojo.Order;
import sms.pojo.OrderDetail;

import java.util.List;

public class OrderMapperTest extends TestCase {

    private OrderMapper orderMapper;
    private OrderDetailMapper orderDetailMapper;
    public void setUp() throws Exception {
        super.setUp();
        orderMapper = MapperUtil.getProxy(OrderMapper.class);
        orderDetailMapper = MapperUtil.getProxy(OrderDetailMapper.class);
    }

    public void tearDown() throws Exception {
        MybatisSingleton.getConn().rollback();
        MybatisSingleton.closeConn();
    }

    public void testSelectAll() {
        List<Order> orders = orderMapper.selectAll(1);
        orders.forEach(o -> {
            System.out.println(o);
            assertEquals(Integer.valueOf(1), o.getSupplier().getShop().getId());
        });
    }

    public void testSelectByUser() {
        List<Order> orders = orderMapper.selectByUser(1);
        orders.forEach(o -> {
            System.out.println(o);
            assertEquals(Integer.valueOf(1), o.getUser().getId());
        });
    }

    public void testSelectByProduct() {
        List<Order> orders = orderMapper.selectByProduct(1);
        orders.forEach(o -> {
            System.out.println(o);
            assertEquals(Integer.valueOf(1), o.getProduct().getId());
        });
    }

    public void testSelectBySupplier() {
        List<Order> orders = orderMapper.selectBySupplier(1);
        orders.forEach(o -> {
            System.out.println(o);
            assertEquals(Integer.valueOf(1), o.getSupplier().getId());
        });
    }

    public void testSelectByOrder() {
        List<Order> orders = orderMapper.selectByOrder(new Order("苹果"), 1);
        orders.forEach( o -> {
            System.out.println(o);
            assertEquals("苹果", o.getProductName());
        });
    }

    public void testSelectById() {
        Order order = orderMapper.selectById(1);
        System.out.println("order = " + order);
        assertEquals(Integer.valueOf(1), order.getId());
    }

    public void testAdd() {
        Order order1 = new Order("垃圾");
        Integer id = orderMapper.add(order1);
        Order order = orderMapper.selectById(order1.getId());
        System.out.println("order = " + order);
        assertEquals("垃圾", order.getProductName());
    }

    public void testDel() {
        Order order = orderMapper.selectById(1);
        System.out.println("before delete: order = " + order);
        orderDetailMapper.del(1);
        orderMapper.del(1);
        order = orderMapper.selectById(1);
        System.out.println("after delete: order = " + order);
        assertNull(order);
    }

    public void testUpdate() {
        Integer id = orderMapper.add(new Order("垃圾"));
        Order order = orderMapper.selectById(id);
        System.out.println("before update: order = " + order);
        Order order1 = new Order("不是垃圾");
        order1.setId(id);
        orderMapper.update(order1);
        order1 = orderMapper.selectById(id);
        System.out.println("after update: order = " + order1);
        assertEquals(id, order1.getId());
        assertEquals("不是垃圾", order1.getProductName());
    }
}