package sms.service.impl;

import junit.framework.TestCase;
import myssm.trans.TransactionManager;
import myssm.util.StringUtil;
import sms.pojo.*;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public class OrderServiceImplTest extends TestCase {

    OrderServiceImpl orderService;

    public void setUp() throws Exception {
        super.setUp();
        TransactionManager.beginTrans();
        ShopServiceImpl shopService = new ShopServiceImpl();
        orderService = new OrderServiceImpl();
        orderService.setShopService(shopService);
    }

    public void tearDown() throws Exception {
        TransactionManager.rollback();
        orderService = null;
    }

    public void testGetAllOrders() {
        List<Order> allOrders = orderService.getAllOrders(1);
        allOrders.forEach(o -> {
            System.out.println(o);
            assertEquals(Integer.valueOf(1), o.getSupplier().getShop().getId());
        });
    }

    public void testGetOrdersByUser() {
        List<Order> ordersByUser = orderService.getOrdersByUser(1);
        ordersByUser.forEach(o -> {
            System.out.println(o);
            assertEquals(Integer.valueOf(1), o.getUser().getId());
        });
    }

    public void testGetOrdersBySupplier() {
        List<Order> ordersBySupplier = orderService.getOrdersBySupplier(1);
        ordersBySupplier.forEach(o -> {
            System.out.println(o);
            assertEquals(Integer.valueOf(1), o.getSupplier().getId());
        });
    }

    public void testGetOrderByProduct() {
        List<Order> orderByProduct = orderService.getOrderByProduct(1);
        orderByProduct.forEach(o -> {
            System.out.println(o);
            assertEquals(Integer.valueOf(1), o.getProduct().getId());
        });
    }

    public void testGetOrderByOrder() {
        List<Order> orders = orderService.getOrderByOrder(new Order("苹果"), 1);
        orders.forEach(o -> {
            System.out.println(o);
            assertEquals("苹果", o.getProductName());
        });
    }

    public void testGetOrder() {
        Order order = orderService.getOrder(1);
        System.out.println("order = " + order);
        assertEquals(Integer.valueOf(1), order.getId());
    }

    public void testAddOrder() {
        LocalDateTime time = LocalDateTime.now();
        Date now = StringUtil.localDateTimeToDate(time);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        String orderNO = UUID.randomUUID() + "_" + sdf.format(now);
        Order order = new Order("测试名称", orderNO, new Supplier(1), new User(1), new Product(1));

        orderService.addOrder(order);
        List<Order> orders = orderService.getOrderByOrder(new Order("测试名称"), 1);
        orders.forEach(o -> {
            System.out.println(o);
            assertEquals(o.getProductName(), order.getProductName());
            assertEquals(o.getOrderNO(), order.getOrderNO());
        });
    }

    public void testPayOrder() {
        LocalDateTime time = LocalDateTime.now();
        Date now = StringUtil.localDateTimeToDate(time);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        String orderNO = UUID.randomUUID() + "_" + sdf.format(now);
        Order order = new Order("测试名称", orderNO, new Supplier(1), new User(1), new Product(1));
        OrderDetail orderDetail = new OrderDetail(1000.0, 0, now, "remark", 100, 10.0);
        order.setOrderDetail(orderDetail);

        orderService.addOrder(order);

        Order order1 = orderService.getOrder(order.getId());
        System.out.println("order1 = " + order1);
        orderService.payOrder(order.getId());
        Order order2 = orderService.getOrder(order.getId());
        System.out.println("order2 = " + order2);
        assertEquals(Integer.valueOf(1), order2.getOrderDetail().getStatus());
    }

    public void testCancelOrder() {
        LocalDateTime time = LocalDateTime.now();
        Date now = StringUtil.localDateTimeToDate(time);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        String orderNO = UUID.randomUUID() + "_" + sdf.format(now);
        Order order = new Order("测试名称", orderNO, new Supplier(1), new User(1), new Product(1));
        OrderDetail orderDetail = new OrderDetail(1000.0, 0, now, "remark", 100, 10.0);
        order.setOrderDetail(orderDetail);

        orderService.addOrder(order);

        orderService.cancelOrder(order.getId());

        Order order1 = orderService.getOrder(order.getId());
        System.out.println("order1 = " + order1);
        assertNull(order1);
    }

    public void testSetOrderProduct() {
        LocalDateTime time = LocalDateTime.now();
        Date now = StringUtil.localDateTimeToDate(time);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        String orderNO = UUID.randomUUID() + "_" + sdf.format(now);
        Order order = new Order("测试名称", orderNO, new Supplier(1), new User(1), new Product(1));
        OrderDetail orderDetail = new OrderDetail(1000.0, 0, now, "remark", 100, 10.0);
        order.setOrderDetail(orderDetail);

        orderService.addOrder(order);

        Product product = new Product(100);
        product.setName("testName");

        orderService.setOrderProduct(order.getId(), product);
    }

    public void testSetOrderUser() {
    }

    public void testSetOrderSupplier() {
    }
}