<script lang="ts" setup>
import { ref } from "vue"
import CardShelfItem from "@/views/StoreMain/components/CardShelfItem.vue"

const cardShelfData = ref([
    {
        itemImageUrl:
            "https://store.storeimages.cdn-apple.com/8756/as-images.apple.com/is/store-card-13-mac-nav-202203?wid=400&hei=260&fmt=png-alpha&.v=1645051958490",
        itemInfo: "Mac"
    },
    {
        itemImageUrl:
            "https://store.storeimages.cdn-apple.com/8756/as-images.apple.com/is/store-card-13-iphone-nav-202109?wid=400&hei=260&fmt=png-alpha&.v=1630706116000",
        itemInfo: "iPhone"
    },
    {
        itemImageUrl:
            "https://store.storeimages.cdn-apple.com/8756/as-images.apple.com/is/store-card-13-ipad-nav-202108?wid=400&hei=260&fmt=png-alpha&.v=1625783381000",
        itemInfo: "iPad"
    },
    {
        itemImageUrl:
            "https://store.storeimages.cdn-apple.com/8756/as-images.apple.com/is/store-card-13-watch-nav-202203_GEO_CN?wid=400&hei=260&fmt=png-alpha&.v=1645725726855",
        itemInfo: "Apple Watch"
    },
    {
        itemImageUrl:
            "https://store.storeimages.cdn-apple.com/8756/as-images.apple.com/is/store-card-13-airpods-nav-202110?wid=400&hei=260&fmt=png-alpha&.v=1633718741000",
        itemInfo: "AirPods"
    },
    {
        itemImageUrl:
            "https://store.storeimages.cdn-apple.com/8756/as-images.apple.com/is/store-card-13-airtags-nav-202108?wid=400&hei=260&fmt=png-alpha&.v=1625783380000",
        itemInfo: "AirTag"
    },
    {
        itemImageUrl:
            "https://store.storeimages.cdn-apple.com/8756/as-images.apple.com/is/store-card-13-homepod-nav-202110?wid=400&hei=260&fmt=png-alpha&.v=1633355783000",
        itemInfo: "HomePod mini"
    },
    {
        itemImageUrl:
            "https://store.storeimages.cdn-apple.com/8756/as-images.apple.com/is/store-card-13-accessories-nav-202203?wid=400&hei=260&fmt=png-alpha&.v=1645051958489",
        itemInfo: "配件"
    }
])
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
