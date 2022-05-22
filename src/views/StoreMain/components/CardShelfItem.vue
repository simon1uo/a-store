<script lang="ts" setup>
defineProps({
    itemImageUrl: String,
    itemInfo: String
})
</script>

<template>
    <div id="carousel-card-item">
        <div class="card-content">
            <div class="card-item-image">
                <img :src="itemImageUrl" :alt="itemInfo" />
            </div>
            <div class="card-info">
                {{ itemInfo }}
            </div>
        </div>
    </div>
</template>

<style lang="less" scoped>
#carousel-card-item {
    margin-right: 10px;
    height: 148px;
    .card-content {
        overflow: hidden;
        border-radius: 18px;
        height: 148px;
        min-width: 136px;
        padding: 18px 8px 16px;
        box-sizing: border-box;
        cursor: pointer;

        .card-item-image {
            padding-bottom: 16px;
            img {
                width: 120px;
                height: 78px;
            }
        }
        .card-info {
            font-weight: 600;
            display: block;
            color: #1d1d1f;
            text-align: center;
            white-space: nowrap;
        }

        .card-info:hover {
            text-decoration: underline #1d1d1f;
        }
    }
}
</style>
