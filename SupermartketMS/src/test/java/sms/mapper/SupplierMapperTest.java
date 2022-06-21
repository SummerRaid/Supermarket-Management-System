package sms.mapper;

import junit.framework.TestCase;
import myssm.basedao.MybatisSingleton;
import myssm.util.MapperUtil;
import sms.pojo.Shop;
import sms.pojo.Supplier;

import java.util.List;

public class SupplierMapperTest extends TestCase {

    private SupplierMapper supplierMapper;

    public void setUp() throws Exception {
        super.setUp();
        supplierMapper = MapperUtil.getProxy(SupplierMapper.class);
    }

    public void tearDown() throws Exception {
        MybatisSingleton.getConn().rollback();
        MybatisSingleton.closeConn();
    }

    public void testSelectAll() {
        List<Supplier> suppliers = supplierMapper.selectAll(1);
        suppliers.forEach(s -> {
            System.out.println(s);
            assertEquals(Integer.valueOf(1), s.getShop().getId());
        });
    }

    public void testSelectById() {
        Supplier supplier = supplierMapper.selectById(1);
        System.out.println("supplier = " + supplier);
        assertEquals(Integer.valueOf(1), supplier.getId());
    }

    public void testAdd() {
        Supplier supplier = new Supplier("地址", "名字", "联系人", "联系电话", "备注", new Shop(1));
        Integer id = supplierMapper.add(supplier);
        Supplier supplier1 = supplierMapper.selectById(id);
        System.out.println("supplier1 = " + supplier1);
        assertEquals("地址", supplier1.getAddress());
        assertEquals("名字", supplier1.getName());
        assertEquals("联系人", supplier1.getContactPerson());
        assertEquals("联系电话", supplier1.getContact());
        assertEquals("备注", supplier1.getRemark());
        assertEquals(Integer.valueOf(1), supplier1.getShop().getId());
    }

    public void testDel() {
        Supplier supplier = supplierMapper.selectById(1);
        System.out.println("before deleted: supplier = " + supplier);
        supplierMapper.del(1);
        supplier = supplierMapper.selectById(1);
        System.out.println("after deleted: supplier = " + supplier);
        assertNull(supplier);
    }

    public void testUpdate() {
        Supplier supplier = supplierMapper.selectById(1);
        System.out.println("before update: supplier = " + supplier);
        Supplier supplier1 = new Supplier("地址", "名字", "联系人", "联系电话", "备注", new Shop(1));
        supplier1.setId(1);
        supplierMapper.update(supplier1);
        supplier = supplierMapper.selectById(1);
        System.out.println("after update: supplier = " + supplier);
        assertEquals(Integer.valueOf(1), supplier.getId());
        assertEquals("地址", supplier.getAddress());
        assertEquals("名字", supplier.getName());
        assertEquals("联系人", supplier.getContactPerson());
        assertEquals("联系电话", supplier.getContact());
        assertEquals("备注", supplier.getRemark());
        assertEquals(Integer.valueOf(1), supplier.getShop().getId());
    }

    public void testSelectByName() {
        Supplier supplier = new Supplier("地址", "名字", "联系人", "联系电话", "备注", new Shop(1));
        supplierMapper.add(supplier);
        List<Supplier> suppliers = supplierMapper.selectByName("名字", 1);
        suppliers.forEach(s -> {
            System.out.println(s);
            assertTrue(s.getName().contains("名字"));
        });
    }
}