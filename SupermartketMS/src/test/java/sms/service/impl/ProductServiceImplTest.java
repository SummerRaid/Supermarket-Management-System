package sms.service.impl;

import junit.framework.TestCase;
import myssm.trans.TransactionManager;

public class ProductServiceImplTest extends TestCase {

    ProductServiceImpl productService;

    public void setUp() throws Exception {
        super.setUp();
        TransactionManager.beginTrans();
        ShopServiceImpl shopService = new ShopServiceImpl();
        OrderServiceImpl orderService = new OrderServiceImpl();
        orderService.setShopService(shopService);
        productService = new ProductServiceImpl();
        productService.setOrderService(orderService);
        productService.setShopService(shopService);
    }

    public void tearDown() throws Exception {
        TransactionManager.rollback();
        productService = null;
    }

    public void testAddProduct() {

    }

    public void testPutToRecycleBin() {
    }

    public void testRemoveProduct() {
    }

    public void testRecoverProduct() {
    }

    public void testSaleProduct() {
    }

    public void testUpdateProduct() {
    }

    public void testGetAllProducts() {
    }

    public void testGetAllDeleted() {
    }

    public void testGetProduct() {
    }

    public void testGetProductByName() {
    }

    public void testGetProductByType() {
    }

    public void testGetTypes() {
    }
}