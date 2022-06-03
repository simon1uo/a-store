<script lang="ts" setup>
import type { IShoppingBagItem } from "@/service/shopping-bag/type"
import { computed, onMounted, ref } from "vue"
import { useProductStore } from "@/stores/product"
import GlobalFooter from "@/components/GlobalFooter/src/GlobalFooter.vue"
import { useShoppingBagStore } from "@/stores/shopping-bag"
import { useRouter } from "vue-router"

const props = defineProps<{ id: any }>()

const itemId = computed(() => props.id)

const productStore = useProductStore()
onMounted(() => {
    productStore.getProductDetailAction(itemId.value)
})

const productDetail = computed(() => productStore.productDetail)

const itemAmount = ref(1)

const shoppingBagStore = useShoppingBagStore()
const router = useRouter()
const handleAddToShoppingBag = () => {
    const bagItem = {
        productId: itemId.value,
        productAmount: itemAmount.value
    }
    shoppingBagStore.addToBagAction(bagItem)
}
</script>

<template>
    <div id="store-product">
        <div class="store-product-container">
            <div class="shop-product-header">
                <h1 class="title">添加购物车商品</h1>
            </div>
            <div class="shop-product-item">
                <el-row :gutter="5">
                    <el-col :lg="18">
                        <div class="product-info">
                            <div class="product-image">
                                <el-image
                                    :src="productDetail.productCoverImageUrl"
                                />
                            </div>
                            <div class="product-title">
                                {{ productDetail.productName }}
                                <div class="product-sub-description"></div>
                            </div>
                        </div>
                    </el-col>
                    <el-col :lg="6">
                        <div class="amount-button">
                            <div class="amount">
                                数量：
                                <el-input
                                    type="number"
                                    min="1"
                                    v-model="itemAmount"
                                />
                            </div>
                            <el-button
                                color="#06c"
                                size="large"
                                @click="handleAddToShoppingBag"
                                >添加至购物车</el-button
                            >
                        </div>
                    </el-col>
                </el-row>
            </div>
            <GlobalFooter />
        </div>
    </div>
</template>

<style lang="less" scoped>
@media only screen and (min-width: 1441px) {
    .store-product-container {
        margin-left: auto;
        margin-right: auto;
        width: 980px;
    }
}

.store-product-container {
    margin-top: 44px;
    .shop-product-header {
        padding-top: 20px;
    }
    .shop-product-item {
        border-radius: 18px;
        padding: 20px;
        background: #fff;
        .product-info {
            display: flex;
            .el-image {
                width: 100px;
            }

            .product-title {
                padding: 20px;
                font-weight: 500;
                font-size: 20px;
            }

            .product-sub-description {
                width: 400px;
                font-weight: 200;
                font-size: 15px;
            }
        }

        .amount-button {
            display: flex;
            flex-direction: column;
            align-items: end;
            .amount {
                margin-top: 20px;
                display: flex;
                align-items: center;

                .el-input {
                    width: 50px;
                }
            }

            .el-button {
                margin-top: 20px;
            }
        }
    }
}
</style>
