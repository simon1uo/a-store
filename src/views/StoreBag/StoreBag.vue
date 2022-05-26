<script lang="ts" setup>
import GlobalFooter from "@/components/GlobalFooter/GlobalFooter.vue"
import StoreBagItem from "@/views/StoreBag/components/StoreBagItem.vue"
import { computed, onMounted } from "vue"
import { useShoppingBagStore } from "@/stores/shopping-bag"

const shoppingBagStore = useShoppingBagStore()

onMounted(() => {
    shoppingBagStore.getBagListAction()
})

const shoppingBagList = computed(() => shoppingBagStore.myBagList)

const total = computed(() => {
    let sum = 0
    let _list: any = shoppingBagList.value
    _list.forEach((item: any) => {
        sum += item.productAmount * item.productSellingPrice
    })
    return sum
})
</script>

<template>
    <div id="shop-bag">
        <div class="shop-bag-container">
            <div class="shop-bag-header">
                <div class="bag-title-info" v-if="shoppingBagList.length">
                    你的购物袋总计 RMB {{ total }}
                </div>
                <div class="sub-info">所有订单均可享受免费送货和退货服务。</div>
                <el-button size="large" color="#06c">结账</el-button>
            </div>

            <div class="shop-bag-content">
                <StoreBagItem
                    v-for="item in shoppingBagList"
                    :key="item.bagItemId"
                    :bag-item="item"
                />
            </div>

            <div class="shop-bag-total">
                <el-row v-if="shoppingBagList.length" :gutter="5">
                    <el-col :lg="8"> </el-col>
                    <el-col :lg="16">
                        <div class="option-list">
                            <div class="option-item">
                                <div class="option-title">小计</div>
                                <div class="option-item-content">
                                    RMB {{ total }}
                                </div>
                            </div>
                            <div class="option-item">
                                <div class="option-title">运费</div>
                                <div class="option-item-content">免费</div>
                            </div>
                        </div>

                        <div class="total-option-item">
                            <div class="total-option-title">总计</div>
                            <div class="total-option-item-content">
                                RMB {{ total }}
                            </div>
                        </div>

                        <el-button size="large" color="#06c">结账</el-button>
                    </el-col>
                </el-row>
                <div class="shop-bag-null" v-else>
                    <h1 class="title">你的购物袋中没有商品。</h1>
                    <div class="sub-title">
                        在线购物并享受免费的送货上门服务。
                    </div>
                </div>
            </div>
            <GlobalFooter />
        </div>
    </div>
</template>

<style lang="less">
@media only screen and (min-width: 1441px) {
    .shop-bag-container {
        margin-left: auto;
        margin-right: auto;
        width: 980px;
    }
}

.shop-bag-container {
    padding: 0 3px 0 3px;
    margin-top: 44px;
    .shop-bag-header {
        padding: 50px;
        display: flex;
        flex-direction: column;
        align-items: center;

        .bag-title-info {
            font-weight: 600;
            font-size: 40px;
            padding-bottom: 13px;
        }

        .sub-info {
            padding-bottom: 30px;
        }

        .el-button {
            font-size: 15px;
            font-weight: 300;
            display: block;
            width: 200px;
        }
    }

    .shop-bag-content {
        padding: 20px;
        border-radius: 18px 18px 0 0;
        background: #fff;
        border-top: 1px solid #d2d2d7;
    }

    .shop-bag-total {
        padding: 20px;
        border-radius: 0 0 18px 18px;
        background: #fff;
        border-top: 1px solid #d2d2d7;

        .option-list {
            padding-bottom: 10px;
            border-bottom: 1px solid #d2d2d7;
            .option-item {
                margin-bottom: 10px;
                display: flex;
                justify-content: space-between;
                font-weight: 400;
            }
        }

        .total-option-item {
            padding-top: 10px;
            display: flex;
            justify-content: space-between;
            font-size: 20px;
            font-weight: 500;
            margin-bottom: 60px;
        }

        .el-button {
            position: absolute;
            bottom: 0;
            right: 0;
            text-align: right;
            width: 400px;
            height: 50px;
        }

        .shop-bag-null {
            .sub-title {
                padding-top: 20px;
                font-weight: 200;
                font-size: 15px;
                margin-bottom: 10px;
            }
        }
    }
}

.common-link {
    cursor: pointer;
    color: #0066cc;
}
.common-link:hover {
    text-decoration: underline;
}
</style>
