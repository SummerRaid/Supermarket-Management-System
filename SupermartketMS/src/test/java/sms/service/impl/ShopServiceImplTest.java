package sms.service.impl;

import junit.framework.TestCase;
import myssm.trans.TransactionManager;
import myssm.util.CalcUtil;
import sms.pojo.Shop;

public class ShopServiceImplTest extends TestCase {

    ShopServiceImpl shopService;

    public void setUp() throws Exception {
        super.setUp();
        TransactionManager.beginTrans();
        shopService = new ShopServiceImpl();
    }

    public void tearDown() throws Exception {
        TransactionManager.rollback();
        shopService = null;
    }

    public void testGetShopById() {
        Shop shopById = shopService.getShopById(1);
        System.out.println("shopById = " + shopById);
        assertEquals(Integer.valueOf(1), shopById.getId());
    }

    public void testAddIncome() {
        Shop shop = shopService.getShopById(1);
        System.out.println("shop = " + shop);
        shopService.addIncome(1, 100.0);
        Shop afterUpdate = shopService.getShopById(1);
        assertEquals(CalcUtil.addDoubles(shop.getIncome(), 100.0), afterUpdate.getIncome());
    }

    public void testAddOutcome() {
        Shop shop = shopService.getShopById(1);
        System.out.println("shop = " + shop);
        shopService.addOutcome(1, 100.0);
        Shop afterUpdate = shopService.getShopById(1);
        assertEquals(CalcUtil.addDoubles(shop.getOutcome(), 100.0), afterUpdate.getOutcome());
    }
}