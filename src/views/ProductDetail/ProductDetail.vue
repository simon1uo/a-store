<script lang="ts" setup>
import { useProductStore } from "@/stores/product"
import { computed } from "vue"

const productStore = useProductStore()

const handleGetProductDetail = (item: any) => {
    productStore.getProductDetailAction(item.itemProductId)
}

const productDetailInfo = computed(() => productStore.productDetail)
const productCarouselImages = computed(() => productStore.productCarouselImages)
const productDetailContentInfos = computed(
    () => productStore.productDetailInfos
)
defineExpose({
    handleGetProductDetail
})
</script>

<template>
    <div id="product-detail">
        <div class="product-detail-content">
            <el-row :gutter="5">
                <el-col :lg="12">
                    <el-carousel height="400px">
                        <el-carousel-item
                            v-for="(image, index) in productCarouselImages"
                            :key="index"
                        >
                            <div class="product-carousel">
                                <img
                                    class="product-carousel-image"
                                    :src="image"
                                />
                            </div>
                        </el-carousel-item>
                    </el-carousel>
                </el-col>
                <el-col :lg="12">
                    <div class="product-text-info">
                        <div class="tag">全新</div>
                        <div class="product-name">
                            {{ productDetailInfo.productName }}
                        </div>
                        <div class="selling-content">
                            <div class="product-selling-price">
                                CNY¥ {{ productDetailInfo.sellingPrice }} 起
                            </div>
                            <div class="product-btn">
                                <el-button color="#06c" round size="large"
                                    >购买</el-button
                                >
                            </div>
                        </div>
                        <div class="product-detail-description">
                            <ul>
                                <li
                                    class="product-detail-item"
                                    v-for="(
                                        item, index
                                    ) in productDetailContentInfos"
                                    :key="index"
                                >
                                    {{ item }}
                                </li>
                            </ul>
                            <div class="further-link">
                                进一步了解 {{ productDetailInfo.productName }}
                            </div>
                        </div>
                    </div>
                </el-col>
            </el-row>
        </div>
    </div>
</template>

<style lang="less" scoped>
#product-detail {
    .product-detail-content {
        .product-carousel {
            padding-top: 24px;
            padding-bottom: 36px;
            display: flex;
            justify-content: center;
            .product-carousel-image {
                width: 364px;
            }
        }

        .product-text-info {
            .tag {
                color: #bf4800;
                font-size: 5px;
                font-weight: 200;
            }
            .product-name {
                padding-top: 5px;
                padding-bottom: 10px;
                font-size: 32px;
                font-weight: 600;
                letter-spacing: 0.001em;
            }

            .selling-content {
                display: flex;
                flex-direction: row;
                justify-content: space-between;
                .product-selling-price {
                    font-size: 13px;
                    font-weight: 400;
                }
            }

            .product-detail-description {
                .product-detail-item {
                    padding: 15px 0;
                    font-weight: 500;
                    font-size: 15px;
                    border-bottom: 1px solid #d2d2d7;
                }

                .product-detail-item:last-child {
                    border-bottom: none;
                }

                .further-link {
                    padding-top: 30px;
                    cursor: pointer;
                    color: #06c;
                }

                .further-link::after {
                    font-size: 20px;
                    padding: 2px;
                    content: ">";
                }
            }
        }
    }
}
.product-detail-footer {
    height: 10px;
    background: #f5f5f7;
}
</style>
