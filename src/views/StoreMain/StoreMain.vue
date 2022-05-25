<script lang="ts" setup>
import ContentHeader from "@/views/StoreMain/components/ContentHeader.vue"
import CardShelf from "@/views/StoreMain/components/CardShelf.vue"
import ContentFooter from "@/views/StoreMain/components/ContentFooter.vue"
import CarouselCard from "@/views/StoreMain/components/CarouselCard.vue"

// config
import { carouselCardConfig } from "@/views/StoreMain/config/carouselCard.config"
import { computed, onMounted } from "vue"
import { useMainStore } from "@/stores/main"

const mainStore = useMainStore()
onMounted(() => {
    mainStore.getCategoryCardItemsAction()
})

const categoryCardItems = computed(() => mainStore.categoryCardItems)
</script>

<template>
    <div id="store-main">
        <div class="store-main-container">
            <ContentHeader />

            <CardShelf :category-card-items="categoryCardItems" />

            <CarouselCard v-bind="carouselCardConfig[0]" />

            <ContentFooter />
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
