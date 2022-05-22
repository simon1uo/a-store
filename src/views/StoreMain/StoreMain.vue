<script lang="ts" setup>
import GlobalHeaderNav from "@/components/GlobalHeaderNav/GlobalHeaderNav.vue"
import ContentHeader from "@/views/StoreMain/components/ContentHeader.vue"
import CardShelf from "@/views/StoreMain/components/CardShelf.vue"
import ContentFooter from "@/views/StoreMain/components/ContentFooter.vue"

// config
import { carouselCardConfig } from "@/views/StoreMain/config/carouselCard.config"
import CarouselCard from "@/views/StoreMain/components/CarouselCard.vue"
</script>

<template>
    <div id="store-main">
        <GlobalHeaderNav />
        <div class="store-main-container">
            <ContentHeader />

            <CardShelf />

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
