<script lang="ts" setup>
import { computed } from "vue"
import CardShelfItem from "@/views/StoreMain/components/CardShelfItem.vue"

const props = defineProps({
    categoryCardItems: {
        type: Array,
        required: true
    }
})

const cardShelfData = computed(() => props.categoryCardItems)
</script>
<template>
    <div id="card-shelf">
        <div class="card-shelf-container">
            <CardShelfItem
                v-for="(item, index) in cardShelfData"
                :key="index"
                :item-info="item.itemInfo"
                :item-image-url="item.itemImageUrl"
            />
        </div>
    </div>
</template>

<style lang="less" scoped>
.card-shelf-container {
    display: flex;
    flex-direction: row;
    justify-content: start;
    padding-bottom: 62px;
}
</style>
