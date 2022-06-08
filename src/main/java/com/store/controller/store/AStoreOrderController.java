package com.store.controller.store;

import com.store.common.BaseResponseCommon;
import com.store.config.annotation.TokenToUser;
import com.store.constant.ErrorCodeEnum;
import com.store.controller.store.param.AStoreOrderCreateParam;
import com.store.controller.store.vo.AStoreOrderDetailVO;
import com.store.controller.store.vo.AStoreOrderListVO;
import com.store.controller.store.vo.AStoreShoppingBagItemVO;
import com.store.domain.AStoreUser;
import com.store.domain.AStoreUserAddress;
import com.store.exception.BusinessException;
import com.store.service.AStoreOrderService;
import com.store.service.AStoreShoppingBagItemService;
import com.store.service.AStoreUserAddressService;
import com.store.util.ResponseResultGenerateUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/order")
public class AStoreOrderController {
    @Resource
    private AStoreOrderService aStoreOrderService;

    @Resource
    private AStoreShoppingBagItemService aStoreShoppingBagItemService;

    @Resource
    private AStoreUserAddressService aStoreUserAddressService;

    @GetMapping()
    public BaseResponseCommon<List<AStoreOrderListVO>> getOrderList(@RequestParam int orderStatus, @TokenToUser AStoreUser loginUser) {
        List<AStoreOrderListVO> userOrderList = aStoreOrderService.getUserOrderList(orderStatus, loginUser.getUserId());
        return ResponseResultGenerateUtil.success(userOrderList);
    }

    @GetMapping("/{orderNo}")
    public BaseResponseCommon<AStoreOrderDetailVO> getOrderDetailByNo(@PathVariable String orderNo, @TokenToUser AStoreUser loginUser) {
        return ResponseResultGenerateUtil.success(aStoreOrderService.getUserOrderDetailByOrderNo(orderNo, loginUser.getUserId()));
    }

    @PostMapping()
    public BaseResponseCommon createOrder(@RequestBody AStoreOrderCreateParam aStoreOrderCreateParam, @TokenToUser AStoreUser loginUser) {
        if (aStoreOrderCreateParam == null
                || aStoreOrderCreateParam.getAddressId() == null
                || aStoreOrderCreateParam.getBagItemIds() == null
                || aStoreOrderCreateParam.getBagItemIds().length < 1) {
            throw new BusinessException(ErrorCodeEnum.PARAMS_ERROR);
        }

        List<AStoreShoppingBagItemVO> bagItemForCreate = aStoreShoppingBagItemService.getShoppingBagItemsForSettle(Arrays.asList(aStoreOrderCreateParam.getBagItemIds()), loginUser.getUserId());
        if (CollectionUtils.isEmpty(bagItemForCreate)) throw new BusinessException(ErrorCodeEnum.PARAMS_ERROR);
        else {
            AStoreUserAddress addressForCreate = aStoreUserAddressService.getUserAddressById(aStoreOrderCreateParam.getAddressId());
            if (!addressForCreate.getUserId().equals(loginUser.getUserId()))
                throw new BusinessException(ErrorCodeEnum.NO_AUTH);

            return ResponseResultGenerateUtil.success(aStoreOrderService.createOrder(loginUser, addressForCreate, bagItemForCreate));
        }
    }

    @PutMapping("/cancel/{orderNo}")
    public BaseResponseCommon cancelOrder(@PathVariable String orderNo, @TokenToUser AStoreUser loginUser) {
        Boolean result = aStoreOrderService.cancelOrder(orderNo, loginUser.getUserId());
        if (result) return ResponseResultGenerateUtil.success("取消订单成功");
        else throw new BusinessException(ErrorCodeEnum.PARAMS_ERROR);
    }

    @PutMapping("/pay")
    public BaseResponseCommon payOrder(@RequestParam String orderNo,
                                       @RequestParam int payType,
                                       @TokenToUser AStoreUser loginUser) {
        Boolean result = aStoreOrderService.payOrder(orderNo, payType);
        if (result) return ResponseResultGenerateUtil.success("支付订单成功");
        else throw new BusinessException(ErrorCodeEnum.PARAMS_ERROR);
    }

    @PutMapping("/confirm/{orderNo}")
    public BaseResponseCommon payOrder(@PathVariable String orderNo,
                                       @TokenToUser AStoreUser loginUser) {
        Boolean result = aStoreOrderService.confirmOrder(orderNo, loginUser.getUserId());
        if (result) return ResponseResultGenerateUtil.success("确认收货成功");
        else throw new BusinessException(ErrorCodeEnum.PARAMS_ERROR);
    }
}
