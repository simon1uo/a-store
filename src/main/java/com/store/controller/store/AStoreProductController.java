package com.store.controller.store;

import com.store.common.BaseResponseCommon;
import com.store.constant.ErrorCodeEnum;
import com.store.controller.store.vo.AStoreProductDetailInfoVO;
import com.store.domain.AStoreProductInfo;
import com.store.exception.BusinessException;
import com.store.service.AStoreProductInfoService;
import com.store.util.BeanUtil;
import com.store.util.ResponseResultGenerateUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RequestMapping("/product")
@RestController
@Slf4j
public class AStoreProductController {
    @Resource
    private AStoreProductInfoService aStoreProductInfoService;

    @RequestMapping("{productId}")
    public BaseResponseCommon<AStoreProductDetailInfoVO> getProductDetailInfo(@PathVariable("productId") Long productId) {
        if (productId < 1) throw new BusinessException(ErrorCodeEnum.PARAMS_ERROR);

        AStoreProductInfo productInfo = aStoreProductInfoService.getProductInfoById(productId);

        AStoreProductDetailInfoVO aStoreProductDetailInfoVO = new AStoreProductDetailInfoVO();
        BeanUtil.copyProperties(productInfo, aStoreProductDetailInfoVO);

        return ResponseResultGenerateUtil.success(aStoreProductDetailInfoVO);
    }
}
