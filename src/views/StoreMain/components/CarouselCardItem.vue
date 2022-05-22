<script lang="ts" setup>
defineProps({
    itemTextColor: String,
    itemTitle: String,
    itemSubtitle: String,
    itemDescription: String,
    itemImageUrl: String,
    itemBackground: String,
    itemUrl: String
})
</script>

<template>
    <div id="carousel-card-item">
        <div
            class="item-container"
            :style="{ 'background-color': itemBackground }"
        >
            <div class="item-content-withImage">
                <div class="item-image-wrapper">
                    <img :src="itemImageUrl" :alt="itemSubtitle" />
                </div>
                <div
                    class="item-content-info"
                    :style="{ color: itemTextColor }"
                >
                    <h3 class="content-eyebrow">{{ itemSubtitle }}</h3>
                    <div class="content-title">{{ itemTitle }}</div>
                    <div class="content-description">{{ itemDescription }}</div>
                </div>
            </div>
        </div>
    </div>
</template>

<style lang="less" scoped>
#carousel-card-item {
    .item-container {
        position: relative;
        overflow: hidden;
        display: flex;
        flex-direction: column;
        justify-content: center;
        height: 500px;
        width: 400px;
        margin-right: 20px;
        cursor: pointer;
        border-radius: 18px;
        box-shadow: 2px 4px 12px rgb(0 0 0 / 8%);
        transition: all 0.3s cubic-bezier(0, 0, 0.5, 1);
        transition-property: all;
        transition-duration: 0.3s;
        transition-timing-function: cubic-bezier(0, 0, 0.5, 1);
        transition-delay: 0s;
        .item-content-withImage {
            flex-direction: row;
            justify-content: center;

            .item-image-wrapper + .item-content-info {
                position: absolute;
                left: 0;
                top: 0;
            }

            .item-image-wrapper {
                width: 100%;
                img {
                    width: 400px;
                    height: 500px;
                }
            }

            .item-content-info {
                padding: 30px;
                width: 400px;
                .content-eyebrow {
                    font-size: 12px;
                }
                .content-title {
                    font-weight: 400;
                    font-size: 28px;
                }
            }
        }
    }

    .item-container:hover {
        transform: scale(1.01);
        box-shadow: 2px 4px 12px rgb(0 0 0 / 20%);
    }
}
</style>
