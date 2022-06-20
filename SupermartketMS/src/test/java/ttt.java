import com.google.gson.Gson;
import org.junit.Test;
import sms.pojo.Order;
import sms.pojo.Product;
import sms.pojo.Shop;
import sms.pojo.Stock;

import java.util.ArrayList;
import java.util.List;

/**
 * Copyright (c) 2008-2024: Zirui Qiao
 * Project: SupermartketMS
 *
 * @className: ttt
 * @Description: TODO
 * @version: v1.8.0
 * @author: ZIRUI QIAO
 * @date: 2022/6/20 14:03
 */
public class ttt {
    @Test
    public void testGson() {
        Product product1 = new Product(1);
        product1.setType("test");
        product1.setName("test too");
        product1.setUnit("ge");
        product1.setDeleted(0);
        Stock stock = new Stock(1);
        stock.setSalePrice(1000.0);
        stock.setSaleAmount(1000);
        stock.setStockAmount(1000);
        Shop shop = new Shop(1);
        shop.setIncome(100000.0);
        shop.setOutcome(100000.0);
        stock.setShop(shop);
        product1.setStock(stock);
        Product product2 = new Product(2);
        product2.setType("test2");
        product2.setName("test2 too");
        product2.setUnit("ge2");
        product2.setDeleted(1);
        product2.setStock(stock);

        List<Product> productList = new ArrayList<>();
        productList.add(product1);
        productList.add(product2);

        Gson gson = new Gson();
        String products = gson.toJson(productList);

        System.out.println(products);
    }
}
