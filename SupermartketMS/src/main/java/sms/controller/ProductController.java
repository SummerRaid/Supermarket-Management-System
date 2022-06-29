package sms.controller;

import com.google.gson.Gson;
import myssm.util.StringUtil;
import sms.pojo.Product;
import sms.pojo.Shop;
import sms.pojo.Stock;
import sms.service.ProductService;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Copyright (c) 2008-2024: Zirui Qiao
 * Project: SupermartketMS
 *
 * @className: ProductController
 * @Description: 商品控制器
 * @version: v1.8.0
 * @author: ZIRUI QIAO
 * @date: 2022/6/20 13:13
 */
public class ProductController {

    private ProductService productService;

    /**
     * @Description: 商品主界面处理器
     * @return: java.lang.String 跳转到商品主界面
     * @Author: Zirui Qiao
     * @Date: 2022/6/20 13:46
     */
    public String index() {
        // 跳转到 productPage.html
        return "productPage";
    }

    public String deletedIndex() {
        return "";
    }

    /**
     * @Description: 根据商品名称查询商品
     * @param pName 商品名称
     * @param session session 用于获取超市id
     * @return: java.lang.String 返回json数据
     * @Author: Zirui Qiao
     * @Date: 2022/6/20 15:03
     */
    public String getProductsByName(String pName, HttpSession session) {
        Integer shopId = (Integer) session.getAttribute("shopId");
        List<Product> products = productService.getProductByName(pName, shopId);

        return "json:"+ StringUtil.toJsonString(products);
    }

    /**
     * @Description: 根据商品类型查询商品
     * @param type 商品类型
     * @param session session
     * @return: java.lang.String 返回json数据
     * @Author: Zirui Qiao
     * @Date: 2022/6/20 15:04
     */
    public String getProductsByType(String type, HttpSession session) {
        Integer shopId = (Integer) session.getAttribute("shopId");
        List<Product> products = productService.getProductByType(type, shopId);

        return "json:"+ StringUtil.toJsonString(products);
    }

    /**
     * @Description: 查询所有商品
     * @param session session
     * @return: java.lang.String 返回json数据
     * @Author: Zirui Qiao
     * @Date: 2022/6/20 15:05
     */
    public String getAllProducts(HttpSession session) {
        Integer shopId = (Integer) session.getAttribute("shopId");
        System.out.println("shopId = " + shopId);
        List<Product> products = productService.getAllProducts(shopId);

        return "json:"+ StringUtil.toJsonString(products);
    }

    /**
     * @Description: 获取当前超市的所有商品类型
     * @param session session
     * @return: java.lang.String 返回json数据
     * @Author: Zirui Qiao
     * @Date: 2022/6/20 15:07
     */
    public String getAllTypes(HttpSession session) {
        Integer shopId = (Integer) session.getAttribute("shopId");
        List<String> types = productService.getTypes(shopId);

        return "json:"+ StringUtil.toJsonString(types);
    }

    /**
     * @Description: 添加商品
     * @param type 商品类型
     * @param name 商品名称
     * @param unit 商品单位
     * @param remark 备注
     * @param salePrice 商品出售价格
     * @param session session
     * @return: java.lang.String 返回json数据
     * @Author: Zirui Qiao
     * @Date: 2022/6/20 15:07
     */

    public String addProduct(String type, String name, String unit, String remark, Double salePrice, HttpSession session) {
        Integer shopId = (Integer) session.getAttribute("shopId");
        Stock stock = new Stock();
        stock.setShop(new Shop(shopId));
        stock.setSalePrice(salePrice);
        Product product = new Product();
        product.setType(type);
        product.setName(name);
        product.setUnit(unit);
        product.setRemark(remark);
        product.setStock(stock);

        product = productService.addProduct(product);

        return "json:"+ StringUtil.toJsonString(product);
    }

    /**
     * @Description: 编辑商品
     * TODO
     * @param session session
     * @return: java.lang.String
     * @Author: Zirui Qiao
     * @Date: 2022/6/20 15:08
     */
    public String editProduct(HttpSession session) {
        return null;
    }

    /**
     * @Description: 将商品放入回收站
     * @param productId 商品id
     * @param session session
     * @return: java.lang.String
     * @Author: Zirui Qiao
     * @Date: 2022/6/20 15:09
     */
    public String putToBin(Integer productId, HttpSession session) {
        productService.putToRecycleBin(productId);

        return "";
    }

    /**
     * @Description: 将商品彻底删除
     * @param productId 商品id
     * @param session session
     * @return: java.lang.String
     * @Author: Zirui Qiao
     * @Date: 2022/6/20 15:09
     */
    public String delProduct(Integer productId, HttpSession session) {
        productService.removeProduct(productId);

        return "";
    }

    /**
     * @Description: 将商品从回收站中复原
     * @param productId 商品id
     * @param session session
     * @return: java.lang.String
     * @Author: Zirui Qiao
     * @Date: 2022/6/20 15:10
     */
    public String recoverProduct(Integer productId, HttpSession session) {
        productService.recoverProduct(productId);

        return "";
    }
}
