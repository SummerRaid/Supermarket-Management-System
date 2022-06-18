package sms.mapper;

import sms.pojo.OrderDetail;
import sms.pojo.Stock;

/**
 * Copyright (c) 2008-2024: Zirui Qiao
 * Project: SupermartketMS
 *
 * @className: StockMapper
 * @Description: TODO
 * @version: v1.8.0
 * @author: ZIRUI QIAO
 * @date: 2022/6/17 18:48
 */
public interface StockMapper {


    /**
     * @Description:  根据id查询库存
     * @param id 商品id
     * @return: sms.pojo.Stock
     * @Author: Zirui Qiao
     * @Date: 2022/6/17 18:54
     */
    Stock selectById(int id);

    /**
     * @Description: 添加库存返回id
     * @param stock 库存详情(包含详细信息)
     * @return: java.lang.Integer
     * @Author: Zirui Qiao
     * @Date: 2022/6/17 18:54
     */
    Integer add(Stock stock);

    /**
     * @Description: 删除库存
     * @param id 库存id
     * @return: void
     * @Author: Zirui Qiao
     * @Date: 2022/6/17 18:55
     */
    void del(int id);

    /**
     * @Description: 更新库存
     * @param stock  库存详情(包含详细信息)
     * @return: java.lang.Integer
     * @Author: Zirui Qiao
     * @Date: 2022/6/17 18:57
     */
    Integer update(Stock stock);
}
