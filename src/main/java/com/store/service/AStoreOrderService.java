package com.store.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.store.controller.store.vo.AStoreOrderDetailVO;
import com.store.controller.store.vo.AStoreOrderItemVO;
import com.store.controller.store.vo.AStoreOrderListVO;
import com.store.controller.store.vo.AStoreShoppingBagItemVO;
import com.store.domain.AStoreOrder;
import com.store.domain.AStoreUser;
import com.store.domain.AStoreUserAddress;

import java.util.List;

/**
 * @author Simon
 * @description 针对表【a_store_order】的数据库操作Service
 * @createDate 2022-06-03 20:52:59
 */
public interface AStoreOrderService extends IService<AStoreOrder> {
    /**
     * 根据订单状态/用户id获取用户订单列表
     *
     * @param orderStatus 订单状态
     * @param userId      用户id
     * @return 用户订单列表
     */
    List<AStoreOrderListVO> getUserOrderList(Integer orderStatus, Long userId);

    /**
     * 根据订单id获取订单详细信息
     *
     * @param orderId 订单id
     * @return 订单详细信息
     */
    AStoreOrderDetailVO getUserOrderDetailByOrderId(Long orderId);

    /**
     * 根据用户id和订单编号No获取订单详细信息
     *
     * @param orderNo 订单No
     * @param userId  用户id
     * @return 订单详细信息
     */
    AStoreOrderDetailVO getUserOrderDetailByOrderNo(String orderNo, Long userId);

    /**
     * 根据订单id获取订单详细信息
     *
     * @param orderId 订单id
     * @return 订单详细信息
     */
    List<AStoreOrderItemVO> getOrderItems(Long orderId);

    /**
     * 创建订单信息
     *
     * @param aStoreUser 用户信息
     * @param address    用户地址信息
     * @param bagItem    购物车项列表
     * @return 操作结果
     */
    String createOrder(AStoreUser aStoreUser, AStoreUserAddress address, List<AStoreShoppingBagItemVO> bagItem);

    /**
     * 更新订单信息
     *
     * @param aStoreOrder 订单
     * @return 操作结果
     */
    Boolean updateOrder(AStoreOrder aStoreOrder);

    /**
     * 取消订单
     *
     * @param orderNo 订单编号
     * @param userId  用户id
     * @return 操作结果
     */
    Boolean cancelOrder(String orderNo, Long userId);

    /**
     * 支付订单
     *
     * @param orderNo 订单编号
     * @param payType 支付方式
     * @return 操作结果
     */
    Boolean payOrder(String orderNo, int payType);

    /**
     * 确认订单
     *
     * @param orderNo 订单编号
     * @param userId  用户id
     * @return 操作结果
     */
    Boolean confirmOrder(String orderNo, Long userId);
}
