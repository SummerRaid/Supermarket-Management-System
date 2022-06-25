package sms.service.impl;

import junit.framework.TestCase;

public class OrderServiceImplTest extends TestCase {

    OrderServiceImpl orderService;

    public void setUp() throws Exception {
        super.setUp();
        ShopServiceImpl shopService = new ShopServiceImpl();
        orderService = new OrderServiceImpl();
        orderService.setShopService(shopService);
    }

    public void tearDown() throws Exception {
        orderService = null;
    }


    public void testGetAllOrders() {
    }

    public void testGetOrdersByUser() {
    }

    public void testGetOrdersBySupplier() {
    }

    public void testGetOrderByProduct() {
    }

    public void testGetOrderByOrder() {
    }

    public void testGetOrder() {
    }

    public void testAddOrder() {
    }

    public void testPayOrder() {
    }

    public void testCancelOrder() {
    }

    public void testSetOrderProduct() {
    }

    public void testSetOrderUser() {
    }

    public void testSetOrderSupplier() {
    }
}