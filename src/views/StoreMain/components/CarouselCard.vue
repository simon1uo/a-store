<script lang="ts" setup>
import { defineEmits } from "vue"
import CarouselCardItem from "@/views/StoreMain/components/CarouselCardItem.vue"

defineProps({
    title: String,
    subtitle: String,
    carouselCardItems: Array
})

const emits = defineEmits(["handleItemClick"])

const handleItemClick = (item: any) => {
    // console.log("handle click", item)
    emits("handleItemClick", item)
}
</script>

<template>
    <div id="carousel-card">
        <div class="card-title-container">
            <div class="card-title">{{ title }}</div>
            <div class="card-subtitle">{{ subtitle }}</div>
        </div>

        <div class="card-item-list">
            <CarouselCardItem
                v-for="(item, index) in carouselCardItems"
                :key="index"
                v-bind="item"
                @click="handleItemClick(item)"
            />
        </div>
    </div>
</template>

<style lang="less" scoped>
#carousel-card {
    .card-title-container {
        padding-bottom: 20px;
    }

    .card-title,
    .card-subtitle {
        font-size: 28px;
        line-height: 1.14286;
        font-weight: 600;
        color: #1d1d1f;
        display: inline;
    }
    .card-subtitle {
        color: #6e6e73;
    }
    .card-item-list {
        display: flex;
        flex-direction: row;
        padding: 10px 0 40px;
    }
}
</style>
