package com.store.controller.store;

import com.store.common.BaseResponseCommon;
import com.store.constant.ErrorCodeEnum;
import com.store.controller.store.vo.AStoreProductCategoryCardVO;
import com.store.exception.BusinessException;
import com.store.service.AStoreProductCategoryCardService;
import com.store.util.ResponseResultGenerateUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RequestMapping("/index")
@RestController
@Slf4j
public class AStoreIndexController {
    @Resource
    private AStoreProductCategoryCardService aStoreProductCategoryCardService;

    @GetMapping("/category-card")
    public BaseResponseCommon<List<AStoreProductCategoryCardVO>> getCategoryCardItems() {
        List<AStoreProductCategoryCardVO> categoryCardItems = aStoreProductCategoryCardService.getCategoryCardItems();
        if (categoryCardItems != null) return ResponseResultGenerateUtil.success(categoryCardItems);
        throw new BusinessException(ErrorCodeEnum.SYSTEM_ERROR);
    }
}
