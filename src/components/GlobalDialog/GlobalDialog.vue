<script lang="ts" setup>
import { ref } from "vue"

const isShowSymBol = ref(false)

const handleOpen = () => {
    isShowSymBol.value = true
}

const handleClose = () => {
    isShowSymBol.value = false
}

defineExpose({
    handleOpen,
    handleClose
})
</script>

<template>
    <div class="dialog-container" :class="{ show: isShowSymBol }">
        <div class="dialog-close-btn" @click="handleClose">
            <span class="rc-overlay-close-svg"
                ><svg
                    width="21"
                    height="21"
                    class="svgicon-close"
                    role="img"
                    aria-hidden="true"
                >
                    <path fill="none" d="M0 0h21v21H0z"></path>
                    <path
                        d="M12.12 10l4.07-4.06a1.5 1.5 0 10-2.11-2.12L10 7.88 5.94 3.81a1.5 1.5 0 10-2.12 2.12L7.88 10l-4.07 4.06a1.5 1.5 0 000 2.12 1.51 1.51 0 002.13 0L10 12.12l4.06 4.07a1.45 1.45 0 001.06.44 1.5 1.5 0 001.06-2.56z"
                    ></path></svg
            ></span>
        </div>
        <div class="dialog-content">
            <slot></slot>
        </div>
    </div>
    <div class="overlay" :class="{ show: isShowSymBol }" />
</template>

<style lang="less" scoped>
@media only screen and (min-width: 1441px) {
    .dialog-container {
        width: 980px;
    }
}

.dialog-container,
.overlay {
    display: none;
}

.dialog-container.show {
    display: block;
    position: fixed;
    top: 0;
    z-index: 1000;
    .dialog-content {
        margin-top: 120px;
        padding: 40px;
        position: relative;
        width: 100%;
        border-radius: 18px;
        background: #fff;
    }
    .dialog-close-btn {
        cursor: pointer;
        position: absolute;
        top: 44px;
        .rc-overlay-close-svg {
            background: #e8e8ed;
            border-radius: 50%;
            color: #6e6e73;
            display: flex;
            align-items: center;
            height: 36px;
            width: 36px;
            outline: none;
            position: relative;
            .svgicon-close {
                position: absolute;
                left: 50%;
                transform: translateX(-50%);
                height: 20px;
                width: 20px;
            }
        }
    }
}

.overlay.show {
    display: block;
    position: fixed;
    background: rgba(0, 0, 0, 0.85);
    width: 100%;
    height: 100vh;
    left: 0;
    top: 0;
    z-index: 300;
}
</style>
