package sms.service.impl;

import junit.framework.TestCase;
import myssm.trans.TransactionManager;
import sms.pojo.Shop;
import sms.pojo.Supplier;

import java.util.List;

public class SupplierServiceImplTest extends TestCase {

    SupplierServiceImpl supplierService;

    public void setUp() throws Exception {
        super.setUp();
        TransactionManager.beginTrans();
        ShopServiceImpl shopService = new ShopServiceImpl();
        OrderServiceImpl orderService = new OrderServiceImpl();
        orderService.setShopService(shopService);
        supplierService = new SupplierServiceImpl();
        supplierService.setOrderService(orderService);
    }

    public void tearDown() throws Exception {
        TransactionManager.rollback();
        supplierService = null;
    }

    public void testGetSupplier() {
        Supplier supplier = supplierService.getSupplier(1);
        System.out.println("supplier = " + supplier);
        assertEquals(Integer.valueOf(1), supplier.getId());
    }

    public void testAddSupplier() {
        Supplier supplier = new Supplier("testAddress", "testName", "联系人", "联系方式", "备注", new Shop(2));
        supplierService.addSupplier(supplier);
        Supplier supplier1 = supplierService.getSupplier(supplier.getId());
        assertEquals(supplier1.getId(), supplier.getId());
        assertEquals(supplier1.getName(), supplier.getName());
        assertEquals(supplier1.getAddress(), supplier.getAddress());
        assertEquals(supplier1.getContact(), supplier.getContact());
        assertEquals(supplier1.getContactPerson(), supplier.getContactPerson());
        assertEquals(supplier1.getRemark(), supplier.getRemark());
        assertEquals(supplier1.getShop().getId(), supplier.getShop().getId());
    }

    public void testGetAllSuppliers() {
        List<Supplier> allSuppliers = supplierService.getAllSuppliers(1);
        allSuppliers.forEach(s -> {
            System.out.println(s);
            assertEquals(Integer.valueOf(1), s.getShop().getId());
        });
    }

    public void testGetSupplierByName() {
        Supplier supplier = new Supplier("testAddress", "testName", "联系人", "联系方式", "备注", new Shop(2));
        supplierService.addSupplier(supplier);
        List<Supplier> supplierByName = supplierService.getSupplierByName(supplier.getName(), 2);
        supplierByName.forEach(s -> {
            System.out.println(s);
            assertEquals(supplier.getName(), s.getName());
        });
    }

    public void testUpdateSupplier() {
        Supplier supplier = supplierService.getSupplier(1);
        supplier.setContact("asdfgh");
        supplierService.updateSupplier(supplier);
        Supplier supplier1 = supplierService.getSupplier(1);
        System.out.println("supplier1 = " + supplier1);
        assertEquals("asdfgh", supplier1.getContact());
    }

    public void testDelSupplier() {
        Supplier supplier = supplierService.getSupplier(1);
        System.out.println("supplier = " + supplier);
        supplierService.delSupplier(1);
        Supplier supplier1 = supplierService.getSupplier(1);
        System.out.println("supplier = " + supplier1);
        assertNull(supplier1);
    }
}