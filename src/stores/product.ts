import { defineStore } from "pinia"
import { getProductDetail } from "@/service/product"

export const useProductStore = defineStore({
    id: "product",
    state: () => ({
        productDetail: {
            productId: "",
            productCarouselImageUrl: "",
            productDetailContent: ""
        }
    }),
    getters: {
        productCarouselImages: (state) => {
            if (state.productDetail.productCarouselImageUrl) {
                return JSON.parse(state.productDetail.productCarouselImageUrl)
            }
        },
        productDetailInfos: (state) => {
            if (state.productDetail.productDetailContent) {
                return JSON.parse(state.productDetail.productDetailContent)
            }
        }
    },
    actions: {
        async getProductDetailAction(productId: number) {
            const result = await getProductDetail(productId)
            this.productDetail = result.data
        }
    }
})
