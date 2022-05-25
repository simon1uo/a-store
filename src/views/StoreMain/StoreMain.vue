<script lang="ts" setup>
import ContentHeader from "@/views/StoreMain/components/ContentHeader.vue"
import CardShelf from "@/views/StoreMain/components/CardShelf.vue"
import ContentFooter from "@/views/StoreMain/components/ContentFooter.vue"
import CarouselCard from "@/views/StoreMain/components/CarouselCard.vue"

// config
import { carouselCardConfig } from "@/views/StoreMain/config/carouselCard.config"
import { computed, ref } from "vue"
import { useMainStore } from "@/stores/main"

import GlobalDialog from "@/components/GlobalDialog/GlobalDialog.vue"
import ProductDetail from "@/views/ProductDetail/ProductDetail.vue"

const mainStore = useMainStore()

const categoryCardItems = computed(() => mainStore.categoryCardItems)

const productDialogRef = ref<InstanceType<any>>(null)

const productDetailRef = ref<InstanceType<any>>(null)

const handleProductDetailClick = (item: any) => {
    productDialogRef.value.handleOpen()
    productDetailRef.value.handleGetProductDetail(item)
}
</script>

<template>
    <div id="store-main">
        <div class="store-main-container">
            <ContentHeader />

            <CardShelf :category-card-items="categoryCardItems" />

            <CarouselCard
                v-bind="carouselCardConfig[0]"
                @handleItemClick="handleProductDetailClick"
            />

            <ContentFooter />

            <GlobalDialog ref="productDialogRef">
                <ProductDetail ref="productDetailRef" />
            </GlobalDialog>
        </div>
    </div>
</template>

<style lang="less" scoped>
@media only screen and (min-width: 1441px) {
    .store-main-container {
        margin-left: auto;
        margin-right: auto;
        width: 980px;
    }
}

.store-main-container {
    margin-top: 44px;
}
</style>
