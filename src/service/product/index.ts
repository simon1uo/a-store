import api from "@/service"

/* 商品相关API */
const productAPI = {
    getProductDetail: "/product/", // + productId
    searchProduct: "/product"
}

export function getProductDetail(productId: number) {
    return api.get(productAPI.getProductDetail + productId)
}
