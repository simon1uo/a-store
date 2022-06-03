package com.store.controller.store;

import com.store.common.BaseResponseCommon;
import com.store.config.annotation.TokenToUser;
import com.store.constant.ErrorCodeEnum;
import com.store.controller.store.param.AStoreShoppingBagAddParam;
import com.store.controller.store.param.AStoreShoppingBagUpdateParam;
import com.store.controller.store.vo.AStoreShoppingBagItemVO;
import com.store.domain.AStoreShoppingBagItem;
import com.store.domain.AStoreUser;
import com.store.exception.BusinessException;
import com.store.service.AStoreShoppingBagItemService;
import com.store.util.ResponseResultGenerateUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;

@RequestMapping("/bag")
@RestController
@Slf4j
public class AStoreShoppingBagController {
    @Resource
    private AStoreShoppingBagItemService aStoreShoppingBagItemService;

    @GetMapping()
    public BaseResponseCommon<List<AStoreShoppingBagItemVO>> getShoppingBagList(@TokenToUser AStoreUser loginUser) {
        return ResponseResultGenerateUtil.success(aStoreShoppingBagItemService.getShoppingBagItemListByUserId(loginUser.getUserId()));
    }

    @GetMapping("/settle")
    public BaseResponseCommon<List<AStoreShoppingBagItemVO>> getSettleShoppingBagList(Long[] bagItemIds, @TokenToUser AStoreUser loginUser) {
        int totalPrice = 0;
        List<AStoreShoppingBagItemVO> shoppingBagItemsForSettle = aStoreShoppingBagItemService.getShoppingBagItemsForSettle(Arrays.asList(bagItemIds), loginUser.getUserId());
        if (CollectionUtils.isEmpty(shoppingBagItemsForSettle)) {
            throw new BusinessException(ErrorCodeEnum.PARAMS_ERROR);
        } else {
            //总价
            for (AStoreShoppingBagItemVO AStoreShoppingBagItemVO : shoppingBagItemsForSettle) {
                totalPrice += AStoreShoppingBagItemVO.getProductAmount() * AStoreShoppingBagItemVO.getProductSellingPrice();
            }
            if (totalPrice < 1) {
                throw new BusinessException(ErrorCodeEnum.PARAMS_ERROR, "总价异常");
            }
        }
        return ResponseResultGenerateUtil.success(shoppingBagItemsForSettle);
    }

    @PostMapping()
    public BaseResponseCommon addShoppingBagItem(@RequestBody AStoreShoppingBagAddParam aStoreShoppingBagAddParam, @TokenToUser AStoreUser loginUser) {
        String addResult = aStoreShoppingBagItemService.addShoppingBagItem(aStoreShoppingBagAddParam, loginUser.getUserId());
        return ResponseResultGenerateUtil.success("添加到购物车成功");
    }

    @PutMapping()
    public BaseResponseCommon updateShoppingBagItem(@RequestBody AStoreShoppingBagUpdateParam aStoreShoppingBagUpdateParam, @TokenToUser AStoreUser loginUser) {
        String updateResult = aStoreShoppingBagItemService.updateShoppingBagItem(aStoreShoppingBagUpdateParam, loginUser.getUserId());
        return ResponseResultGenerateUtil.success("更新购物车成功");
    }

    @DeleteMapping("/{bagItemId}")
    public BaseResponseCommon deleteShoppingBagItem(@PathVariable("bagItemId") Long bagItemId, @TokenToUser AStoreUser loginUser) {
        AStoreShoppingBagItem shoppingBagItemDetailById = aStoreShoppingBagItemService.getShoppingBagItemDetailById(bagItemId);
        if (!loginUser.getUserId().equals(shoppingBagItemDetailById.getUserId())) {
            throw new BusinessException(ErrorCodeEnum.NO_AUTH);
        }

        Boolean deleteResult = aStoreShoppingBagItemService.deleteShoppingBagItemById(bagItemId, loginUser.getUserId());
        return ResponseResultGenerateUtil.success("移除购物车成功");
    }

}
