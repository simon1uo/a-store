package com.store.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.store.constant.ErrorCodeEnum;
import com.store.constant.OrderPayTypeEnum;
import com.store.constant.OrderStatusEnum;
import com.store.constant.PayStatusEnum;
import com.store.controller.store.vo.AStoreOrderDetailVO;
import com.store.controller.store.vo.AStoreOrderItemVO;
import com.store.controller.store.vo.AStoreOrderListVO;
import com.store.controller.store.vo.AStoreShoppingBagItemVO;
import com.store.domain.*;
import com.store.exception.BusinessException;
import com.store.mapper.*;
import com.store.service.AStoreOrderService;
import com.store.util.BeanUtil;
import com.store.util.NumberUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.groupingBy;


/**
 * @author Simon
 * @description 针对表【a_store_order】的数据库操作Service实现
 * @createDate 2022-06-03 20:52:59
 */
@Slf4j
@Service
public class AStoreOrderServiceImpl extends ServiceImpl<AStoreOrderMapper, AStoreOrder>
        implements AStoreOrderService {

    @Resource
    private AStoreOrderMapper aStoreOrderMapper;

    @Resource
    private AStoreOrderItemMapper aStoreOrderItemMapper;

    @Resource
    private AStoreOrderAddressMapper aStoreOrderAddressMapper;

    @Resource
    private AStoreShoppingBagItemMapper aStoreShoppingBagItemMapper;

    @Resource
    private AStoreProductInfoMapper aStoreProductInfoMapper;

    @Override
    public List<AStoreOrderListVO> getUserOrderList(Integer orderStatus, Long userId) {
        QueryWrapper<AStoreOrder> totalQueryWrapper = new QueryWrapper<>();
        totalQueryWrapper.eq("user_id", userId);
        Long total = aStoreOrderMapper.selectCount(totalQueryWrapper);
        log.info(total.toString());

        List<AStoreOrder> aStoreOrderList = new ArrayList<>();
        List<AStoreOrderListVO> aStoreOrderListVOS = new ArrayList<>();

        if (total > 0) {
            QueryWrapper<AStoreOrder> orderQueryWrapper = new QueryWrapper<>();
            if (orderStatus >= 0) {
                orderQueryWrapper.eq("order_status", orderStatus);
            }
            orderQueryWrapper.eq("user_id", userId);
            aStoreOrderList = aStoreOrderMapper.selectList(orderQueryWrapper);
            log.info(aStoreOrderList.toString());

            aStoreOrderListVOS = BeanUtil.copyList(aStoreOrderList, AStoreOrderListVO.class);
            for (AStoreOrderListVO aStoreOrderListVO : aStoreOrderListVOS) {
                aStoreOrderListVO.setOrderStatusString(OrderStatusEnum.getOrderStatusEnumByStatus(aStoreOrderListVO.getOrderStatus()).getName());
            }

            List<Long> orderIds = aStoreOrderList.stream().map(AStoreOrder::getOrderId).collect(Collectors.toList());
            if (!CollectionUtils.isEmpty(orderIds)) {
                List<AStoreOrderItem> aStoreOrderItemList = new ArrayList<>();
                for (Long orderId : orderIds) {
                    QueryWrapper<AStoreOrderItem> itemQueryWrapper = new QueryWrapper<>();
                    itemQueryWrapper.eq("order_id", orderId);
                    aStoreOrderItemList.addAll(aStoreOrderItemMapper.selectList(itemQueryWrapper));
                }
                Map<Long, List<AStoreOrderItem>> itemByOrderIdMap = aStoreOrderItemList.stream().collect(groupingBy(AStoreOrderItem::getOrderId));
                for (AStoreOrderListVO aStoreOrderListVO : aStoreOrderListVOS) {
                    List<AStoreOrderItem> orderItemListTemp = itemByOrderIdMap.get(aStoreOrderListVO.getOrderId());
                    List<AStoreOrderItemVO> aStoreOrderItemVOList = BeanUtil.copyList(orderItemListTemp, AStoreOrderItemVO.class);
                    aStoreOrderListVO.setOrderItemList(aStoreOrderItemVOList);
                }
            }
        } else throw new BusinessException(ErrorCodeEnum.NULL_ERROR);
        return aStoreOrderListVOS;
    }

    @Override
    public AStoreOrderDetailVO getUserOrderDetailByOrderId(Long orderId) {
        AStoreOrder aStoreOrder = aStoreOrderMapper.selectById(orderId);
        if (aStoreOrder == null) throw new BusinessException(ErrorCodeEnum.NULL_ERROR);

        QueryWrapper<AStoreOrderItem> itemQueryWrapper = new QueryWrapper<>();
        itemQueryWrapper.eq("order_id", orderId);
        List<AStoreOrderItem> aStoreOrderItemList = aStoreOrderItemMapper.selectList(itemQueryWrapper);
        if (!CollectionUtils.isEmpty(aStoreOrderItemList)) {
            List<AStoreOrderItemVO> aStoreOrderItemVOList = BeanUtil.copyList(aStoreOrderItemList, AStoreOrderItemVO.class);
            AStoreOrderDetailVO aStoreOrderDetailVO = new AStoreOrderDetailVO();
            BeanUtil.copyProperties(aStoreOrder, aStoreOrderDetailVO);
            aStoreOrderDetailVO.setOrderStatusString(OrderStatusEnum.getOrderStatusEnumByStatus(aStoreOrderDetailVO.getOrderStatus()).getName());
            aStoreOrderDetailVO.setPayTypeString(OrderPayTypeEnum.getOrderPayEnumByType(aStoreOrderDetailVO.getPayType()).getName());
            aStoreOrderDetailVO.setAStoreOrderItemVOList(aStoreOrderItemVOList);
            return aStoreOrderDetailVO;
        } else {
            throw new BusinessException(ErrorCodeEnum.NULL_ERROR);
        }
    }

    @Override
    public AStoreOrderDetailVO getUserOrderDetailByOrderNo(String orderNo, Long userId) {
        QueryWrapper<AStoreOrder> orderQueryWrapper = new QueryWrapper<>();
        orderQueryWrapper.eq("order_no", orderNo);
        AStoreOrder aStoreOrder = aStoreOrderMapper.selectOne(orderQueryWrapper);
        if (aStoreOrder == null) throw new BusinessException(ErrorCodeEnum.NULL_ERROR);
        if (!aStoreOrder.getUserId().equals(userId)) throw new BusinessException(ErrorCodeEnum.NO_AUTH);

        QueryWrapper<AStoreOrderItem> itemQueryWrapper = new QueryWrapper<>();
        itemQueryWrapper.eq("order_id", aStoreOrder.getOrderId());
        List<AStoreOrderItem> aStoreOrderItemList = aStoreOrderItemMapper.selectList(itemQueryWrapper);
        if (!CollectionUtils.isEmpty(aStoreOrderItemList)) {
            List<AStoreOrderItemVO> aStoreOrderItemVOList = BeanUtil.copyList(aStoreOrderItemList, AStoreOrderItemVO.class);
            AStoreOrderDetailVO aStoreOrderDetailVO = new AStoreOrderDetailVO();
            BeanUtil.copyProperties(aStoreOrder, aStoreOrderDetailVO);
            aStoreOrderDetailVO.setOrderStatusString(OrderStatusEnum.getOrderStatusEnumByStatus(aStoreOrderDetailVO.getOrderStatus()).getName());
            aStoreOrderDetailVO.setPayTypeString(OrderPayTypeEnum.getOrderPayEnumByType(aStoreOrderDetailVO.getPayType()).getName());
            aStoreOrderDetailVO.setAStoreOrderItemVOList(aStoreOrderItemVOList);
            return aStoreOrderDetailVO;
        } else {
            throw new BusinessException(ErrorCodeEnum.NULL_ERROR);
        }
    }

    @Override
    public List<AStoreOrderItemVO> getOrderItems(Long orderId) {
        AStoreOrder aStoreOrder = aStoreOrderMapper.selectById(orderId);
        if (aStoreOrder != null) {
            QueryWrapper<AStoreOrderItem> itemQueryWrapper = new QueryWrapper<>();
            itemQueryWrapper.eq("order_id", aStoreOrder.getOrderId());
            List<AStoreOrderItem> aStoreOrderItemList = aStoreOrderItemMapper.selectList(itemQueryWrapper);
            if (!CollectionUtils.isEmpty(aStoreOrderItemList)) {
                return BeanUtil.copyList(aStoreOrderItemList, AStoreOrderItemVO.class);
            }
        }
        return null;
    }

    @Override
    @Transactional
    public String createOrder(AStoreUser aStoreUser, AStoreUserAddress address, List<AStoreShoppingBagItemVO> bagItemList) {
        List<Long> bagItemIdList = bagItemList.stream().map(AStoreShoppingBagItemVO::getBagItemId).collect(Collectors.toList());
        List<Long> productIdList = bagItemList.stream().map(AStoreShoppingBagItemVO::getProductId).collect(Collectors.toList());
        List<AStoreProductInfo> productInfoList = aStoreProductInfoMapper.selectBatchIds(productIdList);

        // check product status
        List<AStoreProductInfo> notSellingProductInfoList = productInfoList.stream().filter(aStoreProductInfo -> aStoreProductInfo.getProductStatus() != 0).collect(Collectors.toList());
        if (!CollectionUtils.isEmpty(notSellingProductInfoList)) {
            throw new BusinessException(ErrorCodeEnum.PARAMS_ERROR, notSellingProductInfoList.get(0).getProductName() + "商品已下架");
        }

        Map<Long, AStoreProductInfo> productsMap = productInfoList.stream().collect(Collectors.toMap(AStoreProductInfo::getProductId, Function.identity(), (entity1, entity2) -> entity1));

        // check product stocking amount
        for (AStoreShoppingBagItemVO aStoreShoppingBagItemVO : bagItemList) {
            if (!productsMap.containsKey(aStoreShoppingBagItemVO.getProductId())) {
                throw new BusinessException(ErrorCodeEnum.PARAMS_ERROR, "商品不存在");
            }

            if (aStoreShoppingBagItemVO.getProductAmount() > productsMap.get(aStoreShoppingBagItemVO.getProductId()).getStockNum()) {
                throw new BusinessException(ErrorCodeEnum.PARAMS_ERROR, "库存不足");
            }
        }

        if (!CollectionUtils.isEmpty(bagItemIdList) && !CollectionUtils.isEmpty(productIdList) && !CollectionUtils.isEmpty(productInfoList)) {
            if (aStoreShoppingBagItemMapper.deleteBatchIds(bagItemIdList) > 0) {
                List<AStoreStockNumDTO> stockNumDTOList = BeanUtil.copyList(bagItemList, AStoreStockNumDTO.class);
                for (AStoreStockNumDTO aStoreStockNumDTO : stockNumDTOList) {
                    UpdateWrapper<AStoreProductInfo> productInfoUpdateWrapper = new UpdateWrapper<>();
                    productInfoUpdateWrapper.eq("product_id", aStoreStockNumDTO.getProductId());
                    productInfoUpdateWrapper.set("stock_num", productsMap.get(aStoreStockNumDTO.getProductId()).getStockNum() - aStoreStockNumDTO.getProductAmount());
                    int updateResult = aStoreProductInfoMapper.update(null, productInfoUpdateWrapper);
                    if (updateResult < 1) throw new BusinessException(ErrorCodeEnum.SYSTEM_ERROR);
                }

                String orderNo = NumberUtil.generateOrderNo();
                int orderTotalPrice = 0;

                AStoreOrder aStoreOrder = new AStoreOrder();
                aStoreOrder.setOrderNo(orderNo);
                aStoreOrder.setUserId(aStoreUser.getUserId());

                for (AStoreShoppingBagItemVO bagItemVO : bagItemList) {
                    orderTotalPrice += bagItemVO.getProductAmount() * bagItemVO.getProductSellingPrice();
                }
                if (orderTotalPrice < 1) throw new BusinessException(ErrorCodeEnum.SYSTEM_ERROR);
                aStoreOrder.setTotalPrice(orderTotalPrice);

                aStoreOrder.setExtraInfo("");

                if (aStoreOrderMapper.insert(aStoreOrder) > 0) {
                    AStoreOrderAddress aStoreOrderAddress = new AStoreOrderAddress();
                    BeanUtil.copyProperties(address, aStoreOrderAddress);
                    aStoreOrderAddress.setOrderId(aStoreOrder.getOrderId());

                    List<AStoreOrderItem> aStoreOrderItemList = new ArrayList<>();
                    for (AStoreShoppingBagItemVO aStoreShoppingBagItemVO : bagItemList) {
                        AStoreOrderItem aStoreOrderItem = new AStoreOrderItem();
                        BeanUtil.copyProperties(aStoreShoppingBagItemVO, aStoreOrderItem);
                        aStoreOrderItem.setOrderId(aStoreOrder.getOrderId());
                        aStoreOrderItemList.add(aStoreOrderItem);
                    }

                    if (aStoreOrderAddressMapper.insert(aStoreOrderAddress) < 0)
                        throw new BusinessException(ErrorCodeEnum.SYSTEM_ERROR);

                    for (AStoreOrderItem aStoreOrderItem : aStoreOrderItemList) {
                        int result = aStoreOrderItemMapper.insert(aStoreOrderItem);
                        if (result < 0) throw new BusinessException(ErrorCodeEnum.SYSTEM_ERROR);
                    }
                    return orderNo;
                }
            }
        } else throw new BusinessException(ErrorCodeEnum.PARAMS_ERROR);
        throw new BusinessException(ErrorCodeEnum.SYSTEM_ERROR);
    }

    @Override
    @Transactional
    public Boolean updateOrder(AStoreOrder aStoreOrder) {
        AStoreOrder tempOrder = aStoreOrderMapper.selectById(aStoreOrder.getOrderId());
        if (tempOrder != null && tempOrder.getOrderStatus() >= 0 && tempOrder.getOrderStatus() < 3) {
            tempOrder.setTotalPrice(aStoreOrder.getTotalPrice());
            tempOrder.setUpdateTime(new Date());
            if (aStoreOrderMapper.insert(tempOrder) > 0) {
                return true;
            }
        }
        return null;
    }

    @Override
    @Transactional
    public Boolean cancelOrder(String orderNo, Long userId) {
        QueryWrapper<AStoreOrder> orderQueryWrapper = new QueryWrapper<>();
        orderQueryWrapper.eq("order_no", orderNo);
        AStoreOrder aStoreOrder = aStoreOrderMapper.selectOne(orderQueryWrapper);
        if (aStoreOrder != null) {
            if (!aStoreOrder.getUserId().equals(userId)) throw new BusinessException(ErrorCodeEnum.NO_AUTH);

            if (aStoreOrder.getOrderStatus() == OrderStatusEnum.ORDER_SUCCESS.getOrderStatus()
                    || aStoreOrder.getOrderStatus() == OrderStatusEnum.ORDER_CLOSED_BY_USER.getOrderStatus()
                    || aStoreOrder.getOrderStatus() == OrderStatusEnum.ORDER_CLOSED_BY_EXPIRED.getOrderStatus()
                    || aStoreOrder.getOrderStatus() == OrderStatusEnum.ORDER_CLOSED_BY_ADMIN.getOrderStatus()) {
                throw new BusinessException(ErrorCodeEnum.PARAMS_ERROR);
            }

            UpdateWrapper<AStoreOrder> orderUpdateWrapper = new UpdateWrapper<>();
            orderUpdateWrapper.eq("order_no", orderNo);
            orderUpdateWrapper.set("order_status", OrderStatusEnum.ORDER_CLOSED_BY_USER.getOrderStatus());
            if (aStoreOrderMapper.update(null, orderUpdateWrapper) > 0) return true;
            else throw new BusinessException(ErrorCodeEnum.SYSTEM_ERROR);
        }
        throw new BusinessException(ErrorCodeEnum.NULL_ERROR);
    }

    @Override
    @Transactional
    public Boolean payOrder(String orderNo, int payType) {
        QueryWrapper<AStoreOrder> orderQueryWrapper = new QueryWrapper<>();
        orderQueryWrapper.eq("order_no", orderNo);
        AStoreOrder aStoreOrder = aStoreOrderMapper.selectOne(orderQueryWrapper);

        if (aStoreOrder != null) {
            if (aStoreOrder.getOrderStatus() != OrderStatusEnum.ORDER_PRE_PAY.getOrderStatus()) {
                throw new BusinessException(ErrorCodeEnum.PARAMS_ERROR);
            }

            aStoreOrder.setOrderStatus(OrderStatusEnum.ORDER_PAID.getOrderStatus());
            aStoreOrder.setPayType(payType);
            aStoreOrder.setPayStatus(PayStatusEnum.PAY_SUCCESS.getPayStatus());
            aStoreOrder.setPayTime(new Date());
            aStoreOrder.setUpdateTime(new Date());

            if (aStoreOrderMapper.updateById(aStoreOrder) > 0) {
                return true;
            } else {
                throw new BusinessException(ErrorCodeEnum.SYSTEM_ERROR);
            }
        }
        throw new BusinessException(ErrorCodeEnum.PARAMS_ERROR);
    }

    @Override
    @Transactional
    public Boolean confirmOrder(String orderNo, Long userId) {
        QueryWrapper<AStoreOrder> orderQueryWrapper = new QueryWrapper<>();
        orderQueryWrapper.eq("order_no", orderNo);
        AStoreOrder aStoreOrder = aStoreOrderMapper.selectOne(orderQueryWrapper);

        if (aStoreOrder != null) {
            if (!aStoreOrder.getUserId().equals(userId)) {
                throw new BusinessException(ErrorCodeEnum.NO_AUTH);
            }

            if (aStoreOrder.getOrderStatus() != OrderStatusEnum.ORDER_EXPRESS.getOrderStatus()) {
                throw new BusinessException(ErrorCodeEnum.PARAMS_ERROR);
            }

            aStoreOrder.setOrderStatus(OrderStatusEnum.ORDER_SUCCESS.getOrderStatus());
            aStoreOrder.setUpdateTime(new Date());
            if (aStoreOrderMapper.updateById(aStoreOrder) > 0) return true;
            else throw new BusinessException(ErrorCodeEnum.SYSTEM_ERROR);
        }
        throw new BusinessException(ErrorCodeEnum.SYSTEM_ERROR);
    }

}




