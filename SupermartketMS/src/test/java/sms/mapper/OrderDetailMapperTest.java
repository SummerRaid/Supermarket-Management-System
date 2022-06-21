package sms.mapper;

import junit.framework.TestCase;
import myssm.basedao.MybatisSingleton;
import myssm.util.MapperUtil;
import sms.pojo.OrderDetail;

import java.util.Date;
import java.util.Optional;

public class OrderDetailMapperTest extends TestCase {

    private OrderDetailMapper orderDetailMapper;

    public void setUp() throws Exception {
        super.setUp();
        orderDetailMapper = MapperUtil.getProxy(OrderDetailMapper.class);
    }

    public void tearDown() throws Exception {
        MybatisSingleton.getConn().rollback();
        MybatisSingleton.closeConn();
    }

    public void testSelectById() {
        OrderDetail order = orderDetailMapper.selectById(1);
        System.out.println("orderDetail = " + order);
        assertEquals(Integer.valueOf(1), order.getId());
    }

    public void testAdd() {
        Date now = new Date();
        Integer id = orderDetailMapper.add(new OrderDetail(100.0, 2, now, "lala", 100, 20.0));
        OrderDetail orderDetail = orderDetailMapper.selectById(id);
        System.out.println("orderDetail = " + orderDetail);
        assertEquals(100.0, orderDetail.getPayMoney());
        assertEquals(Integer.valueOf(2), orderDetail.getStatus());
        assertEquals(now, orderDetail.getCreateDate());
        assertEquals("lala", orderDetail.getRemark());
        assertEquals(Integer.valueOf(100), orderDetail.getAmount());
        assertEquals(20.0, orderDetail.getPrice());
    }

    public void testDel() {
        OrderDetail order = orderDetailMapper.selectById(1);
        System.out.println("before delete: orderDetail = " + order);
        orderDetailMapper.del(1);
        order = orderDetailMapper.selectById(1);
        System.out.println("after delete: orderDetail = " + order);
        assertNull(order);
    }

    public void testUpdate() {
        Date now = new Date();
        OrderDetail order = orderDetailMapper.selectById(1);
        System.out.println("before update: orderDetail = " + order);
        OrderDetail orderDetail = new OrderDetail(100.0, 2, now, "lala", 100, 20.0);
        orderDetail.setId(1);
        orderDetailMapper.update(orderDetail);
        order = orderDetailMapper.selectById(1);
        System.out.println("after update: orderDetail = " + order);
        assertEquals(Integer.valueOf(1), order.getId());
        assertEquals(100.0, order.getPayMoney());
        assertEquals(Integer.valueOf(2), order.getStatus());
        assertEquals(now, order.getCreateDate());
        assertEquals("lala", order.getRemark());
        assertEquals(Integer.valueOf(100), order.getAmount());
        assertEquals(20.0, order.getPrice());
    }
}