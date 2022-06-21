package sms.mapper;

import junit.framework.TestCase;
import myssm.basedao.MybatisSingleton;
import myssm.util.MapperUtil;
import sms.pojo.Shop;

public class ShopMapperTest extends TestCase {

    private ShopMapper shopMapper;

    public void setUp() throws Exception {
        super.setUp();
        shopMapper = MapperUtil.getProxy(ShopMapper.class);
    }

    public void tearDown() throws Exception {
        MybatisSingleton.getConn().rollback();
        MybatisSingleton.closeConn();
    }

    public void testSelectById() {
        Shop shop = shopMapper.selectById(1);
        System.out.println("shop = " + shop);
        assertEquals(Integer.valueOf(1), shop.getId());
    }

    public void testUpdateIncome() {
        shopMapper.updateIncome(1, 999.8);
        Shop shop = shopMapper.selectById(1);
        System.out.println("shop = " + shop);
        assertEquals(999.8, shop.getIncome());
    }

    public void testUpdateOutcome() {
        shopMapper.updateOutcome(1, 998.9);
        Shop shop = shopMapper.selectById(1);
        System.out.println("shop = " + shop);
        assertEquals(998.9, shop.getOutcome());
    }
}