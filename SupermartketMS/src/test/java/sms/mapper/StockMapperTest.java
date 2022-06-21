package sms.mapper;

import junit.framework.TestCase;
import myssm.basedao.MybatisSingleton;
import myssm.util.MapperUtil;
import sms.pojo.Shop;
import sms.pojo.Stock;

public class StockMapperTest extends TestCase {

    private StockMapper stockMapper;

    public void setUp() throws Exception {
        super.setUp();
        stockMapper = MapperUtil.getProxy(StockMapper.class);
    }

    public void tearDown() throws Exception {
        MybatisSingleton.getConn().rollback();
        MybatisSingleton.closeConn();
    }

    public void testSelectById() {
        Stock stock = stockMapper.selectById(1);
        System.out.println("stock = " + stock);
        assertEquals(Integer.valueOf(1), stock.getId());
    }

    public void testAdd() {
        Stock stock = new Stock(100.0, 100, new Shop(1), 200);
        Integer id = stockMapper.add(stock);
        Stock stock1 = stockMapper.selectById(id);
        assertEquals(stock.getStockAmount(), stock1.getStockAmount());
        assertEquals(stock.getSaleAmount(), stock1.getSaleAmount());
        assertEquals(stock.getSalePrice(), stock1.getSalePrice());
        assertEquals(stock.getXiaoji(), stock1.getXiaoji());
        assertEquals(stock.getShop().getId(), stock1.getShop().getId());
        assertEquals(stock.getId(), stock1.getId());
    }

    public void testDel() {
        Stock stock = stockMapper.selectById(1);
        System.out.println("before deleted: stock = " + stock);
        stockMapper.del(1);
        stock = stockMapper.selectById(1);
        System.out.println("after deleted: stock = " + stock);
        assertNull(stock);
    }

    public void testUpdate() {
        Stock stock = stockMapper.selectById(1);
        System.out.println("before update: stock = " + stock);
        Stock stock1 = new Stock(100.0, 100, new Shop(1), 200);
        stock1.setId(1);
        stockMapper.update(stock1);
        stock = stockMapper.selectById(1);
        System.out.println("after update: stock = " + stock);
        assertEquals(stock.getStockAmount(), stock1.getStockAmount());
        assertEquals(stock.getSaleAmount(), stock1.getSaleAmount());
        assertEquals(stock.getSalePrice(), stock1.getSalePrice());
        assertEquals(stock.getXiaoji(), stock1.getXiaoji());
        assertEquals(stock.getShop().getId(), stock1.getShop().getId());
        assertEquals(stock.getId(), stock1.getId());
    }
}