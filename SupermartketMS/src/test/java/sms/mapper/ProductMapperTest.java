package sms.mapper;

import junit.framework.TestCase;
import myssm.basedao.MybatisSingleton;
import myssm.util.MapperUtil;
import sms.pojo.Product;
import sms.pojo.Shop;
import sms.pojo.Stock;

import java.util.List;

public class ProductMapperTest extends TestCase {

    private ProductMapper productMapper;
    private StockMapper stockMapper;

    public void setUp() throws Exception {
        super.setUp();
        productMapper = MapperUtil.getProxy(ProductMapper.class);
        stockMapper = MapperUtil.getProxy(StockMapper.class);
    }

    public void tearDown() throws Exception {
        MybatisSingleton.getConn().rollback();
        MybatisSingleton.closeConn();
    }

    public void testSelectAll() {
        List<Product> products = productMapper.selectAll(1, false);
        products.forEach(p -> {
            System.out.println(p);
            assertEquals(Integer.valueOf(1), p.getStock().getShop().getId());
            assertEquals(Integer.valueOf(0), p.getDeleted());
        });
        products = productMapper.selectAll(1, true);
        products.forEach(p -> {
            System.out.println(p);
            assertEquals(Integer.valueOf(1), p.getStock().getShop().getId());
            assertEquals(Integer.valueOf(1), p.getDeleted());
        });
    }

    public void testSelectById() {
        Product product = productMapper.selectById(1);
        System.out.println("product = " + product);
        assertEquals(Integer.valueOf(1), product.getId());
    }

    public void testAdd() {
        Product product = new Product("类型", "商品名称", "单位", "备注");
        Stock stock = new Stock(100.0, 100, new Shop(1), 200);
        product.setStock(stock);
        Integer add = productMapper.add(product);
        Product product1 = productMapper.selectById(product.getId());
        System.out.println("product1 = " + product1);
        assertEquals(product.getName(), product1.getName());
        assertEquals(product.getUnit(), product1.getUnit());
        assertEquals(product.getType(), product1.getType());
        assertEquals(product.getRemark(), product1.getRemark());
        assertEquals(product.getDeleted(), product1.getDeleted());
        assertEquals(Integer.valueOf(0), product.getDeleted());
        assertEquals(product.getId(), product1.getId());
    }

    public void testSelectByName() {
        Product product = new Product("类型", "商品名称", "单位", "备注");
        Stock stock = new Stock(100.0, 100, new Shop(1), 200);
        product.setStock(stock);
        Integer id = productMapper.add(product);
        List<Product> products = productMapper.selectByName("商品名称", 1);
        products.forEach(p -> {
            System.out.println(p);
            assertTrue(p.getName().contains("商品名称"));
        });
    }

    public void testSelectByType() {
        Product product = new Product("类型", "商品名称", "单位", "备注");
        Stock stock = new Stock(100.0, 100, new Shop(1), 200);
        product.setStock(stock);
        Integer id = productMapper.add(product);
        List<Product> products = productMapper.selectByType("类型", 1);
        products.forEach(p -> {
            System.out.println(p);
            assertEquals("类型", p.getType());
        });
    }

    public void testSelectAllType() {
        List<String> strings = productMapper.selectAllType(1);
        strings.forEach(System.out::println);
    }

    public void testDel() {
        Product product = productMapper.selectById(1);
        System.out.println("before deleted: product = " + product);
        stockMapper.del(1);
        productMapper.del(1);
        product = productMapper.selectById(1);
        System.out.println("after deleted: product = " + product);
        assertNull(product);
    }

    public void testUpdate() {
        Product product = productMapper.selectById(1);
        System.out.println("before update: product = " + product);
        Product product1 = new Product("类型", "商品名称", "单位", "备注");
        Stock stock = new Stock(100.0, 100, new Shop(1), 200);
        product1.setStock(stock);
        product1.setId(1);
        productMapper.update(product1);
        product = productMapper.selectById(1);
        System.out.println("after update: product = " + product);
        assertEquals(product.getName(), product1.getName());
        assertEquals(product.getUnit(), product1.getUnit());
        assertEquals(product.getType(), product1.getType());
        assertEquals(product.getRemark(), product1.getRemark());
        assertEquals(product.getDeleted(), product1.getDeleted());
        assertEquals(Integer.valueOf(0), product.getDeleted());
        assertEquals(product.getId(), product1.getId());
    }
}