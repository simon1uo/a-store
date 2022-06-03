<script lang="ts" setup>
import { computed, onMounted } from "vue"
import { useShoppingBagStore } from "@/stores/shopping-bag"
import { useUserAddressStore } from "@/stores/user-address"

const props = defineProps({
    bagItem: {
        type: Object,
        required: true
    }
})

const myBagItem = computed(() => props.bagItem)

const shoppingBagStore = useShoppingBagStore()

const userAddressStore = useUserAddressStore()

onMounted(() => {
    userAddressStore.getUserDefaultAddress()
})

const userDefaultAddress = computed(() => userAddressStore.userDefaultAddress)

const handleAmountChange = () => {
    shoppingBagStore.updateBagItemAction({
        bagItemId: myBagItem.value.bagItemId,
        productAmount: myBagItem.value.productAmount
    })
}

const handleRemoveLink = () => {
    shoppingBagStore.removeBagItemAction(myBagItem.value.bagItemId)
}
</script>

<template>
    <div id="bag-item">
        <el-row :gutter="5">
            <el-col :sm="8">
                <div class="product-image-container">
                    <img
                        class="product-image"
                        :src="myBagItem.productImageUrl"
                    />
                </div>
            </el-col>
            <el-col :sm="16">
                <div class="bag-product-info-container">
                    <h2 class="title">{{ myBagItem.productName }}</h2>
                    <div class="product-amount-price">
                        <div class="amount">
                            <el-input
                                type="number"
                                min="1"
                                v-model="myBagItem.productAmount"
                                @change="handleAmountChange"
                            ></el-input>
                        </div>
                        <div class="price">
                            RMB {{ myBagItem.productSellingPrice }}
                        </div>
                    </div>
                    <div
                        class="remove-product-link common-link"
                        @click="handleRemoveLink"
                    >
                        移除
                    </div>
                </div>
                <div class="bag-option">
                    <div class="option-content">
                        <div class="content-icon-svg">
                            <svg
                                viewBox="0 0 25 25"
                                class="as-svgicon as-svgicon-applestorepickup as-svgicon-reduced as-svgicon-applestorepickupreduced"
                                role="img"
                                aria-hidden="true"
                                width="25px"
                                height="25px"
                            >
                                <path fill="none" d="M0 0h25v25H0z"></path>
                                <path
                                    d="M18.5 5h-1.775a4.231 4.231 0 00-8.45 0H6.5A2.5 2.5 0 004 7.5v11A2.5 2.5 0 006.5 21h12a2.5 2.5 0 002.5-2.5v-11A2.5 2.5 0 0018.5 5zm-6-3a3.245 3.245 0 013.225 3h-6.45A3.245 3.245 0 0112.5 2zM20 18.5a1.5 1.5 0 01-1.5 1.5h-12A1.5 1.5 0 015 18.5v-11A1.5 1.5 0 016.5 6h12A1.5 1.5 0 0120 7.5z"
                                ></path>
                                <path
                                    d="M14.4 12.448a1.592 1.592 0 01.738-1.328 1.607 1.607 0 00-1.37-.687c-.52 0-.941.317-1.22.317s-.663-.3-1.129-.3a1.861 1.861 0 00-1.739 2.068 4.32 4.32 0 00.723 2.3c.346.491.648.883 1.084.883s.617-.287 1.144-.287c.55 0 .663.279 1.137.279s.791-.43 1.084-.853a3.24 3.24 0 00.474-.989 1.516 1.516 0 01-.926-1.403zM12.583 10.357a1.346 1.346 0 00.941-.5 1.594 1.594 0 00.361-.974.731.731 0 00-.008-.136 1.5 1.5 0 00-1.016.528 1.547 1.547 0 00-.384.943c0 .053.008.106.008.128.03.004.06.011.098.011z"
                                ></path>
                            </svg>
                        </div>
                        <div class="content-text">立即订购。取货 (店内）</div>
                    </div>
                    <div class="option-content">
                        <div class="content-icon-svg">
                            <svg
                                viewBox="0 0 25 25"
                                class="as-svgicon as-svgicon-shipping as-svgicon-reduced as-svgicon-shippingreduced"
                                role="img"
                                aria-hidden="true"
                                width="25px"
                                height="25px"
                            >
                                <path fill="none" d="M0 0h25v25H0z"></path>
                                <path
                                    d="M19.69 7.154l-6-3.245a2.5 2.5 0 00-2.38 0l-6 3.245A2.5 2.5 0 004 9.354v6.269a2.5 2.5 0 001.311 2.2l6 3.245a2.5 2.5 0 002.379 0l6-3.245a2.5 2.5 0 001.31-2.2V9.354a2.5 2.5 0 00-1.31-2.2zm-7.9-2.365a1.492 1.492 0 011.427 0l6 3.244a1.454 1.454 0 01.151.11l-2.931 1.665-6.743-3.886zM8.661 6.48l6.768 3.9-2.929 1.666-6.864-3.9a1.456 1.456 0 01.151-.11zM5.787 16.941A1.5 1.5 0 015 15.622V9.354a1.5 1.5 0 01.053-.39L12 12.912v7.358a1.463 1.463 0 01-.213-.083zM20 15.622a1.5 1.5 0 01-.786 1.319l-6 3.245a1.5 1.5 0 01-.214.084v-7.358l6.947-3.949a1.508 1.508 0 01.053.391z"
                                ></path>
                            </svg>
                        </div>
                        <div
                            class="content-text"
                            v-if="userDefaultAddress.addressId"
                        >
                            送货至
                            <div class="common-link">
                                <span>{{
                                    userDefaultAddress.provinceName
                                }}</span>
                                <span>{{ userDefaultAddress.cityName }}</span>
                                <span>{{ userDefaultAddress.regionName }}</span>
                            </div>
                        </div>
                    </div>
                </div>
            </el-col>
        </el-row>
    </div>
</template>

<style lang="less" scoped>
#bag-item {
    padding-bottom: 10px;
    .product-image-container {
        padding: 20px;
        text-align: center;
        .product-image {
            width: 120px;
        }
    }

    .bag-product-info-container {
        padding-bottom: 20px;
        .title {
            margin-top: 20px;
        }
        .product-amount-price {
            display: flex;
            justify-content: end;
            align-items: center;
            .amount {
                margin-right: 10px;
                width: 60px;
            }
        }
        .remove-product-link {
            text-align: right;
        }
    }

    .bag-option {
        padding-top: 20px;
        display: flex;
        border-top: 1px solid #d2d2d7;
        .option-content {
            display: flex;
            flex-direction: row;
            align-items: center;
            margin-right: 40px;
            .content-text {
                padding: 5px;
                font-size: 15px;
            }
        }
    }
}
</style>
