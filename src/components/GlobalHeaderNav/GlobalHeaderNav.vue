<script lang="ts" setup>
import { ref } from "vue"

// for desktop version
let isSearchHide = ref(true)
let isOverlayShow = ref(false)

const handleSearchBtn = () => {
    isSearchHide.value = false
    isOverlayShow.value = true
}

const handleCloseBtn = () => {
    isSearchHide.value = true
    isOverlayShow.value = false
}

const handleOverlay = () => {
    isSearchHide.value = true
    isOverlayShow.value = false
    isMobileBagShow.value = false
}

let isBagShow = ref(false)
let isBagMaskShow = ref(false)

const handleBagBtn = () => {
    isBagShow.value = !isBagShow.value
    isBagMaskShow.value = true
}

const handleBagMask = () => {
    isBagShow.value = false
    isBagMaskShow.value = false
}

// for mobile version
let isToggleActive = ref(false)

const handleMenuIconBtn = () => {
    isToggleActive.value = !isToggleActive.value
}

let isSearchBarActive = ref(false)

const handleSearchInputClick = () => {
    isSearchBarActive.value = true
}

const handleCancelBtn = () => {
    isSearchBarActive.value = false
}

let isMobileBagShow = ref(false)
const handleMobileBagBtn = () => {
    isMobileBagShow.value = !isMobileBagShow.value
    isOverlayShow.value = !isOverlayShow.value
}
</script>

<template>
    <div class="nav-container" :class="isToggleActive ? 'active' : ''">
        <nav :class="{ 'move-up': isSearchBarActive }">
            <ul class="mobile-nav">
                <li>
                    <div class="menu-icon-container" @click="handleMenuIconBtn">
                        <div class="menu-icon">
                            <span class="line-1"></span>
                            <span class="line-2"></span>
                        </div>
                    </div>
                </li>

                <li>
                    <router-link to="/" class="link-logo" />
                </li>

                <li>
                    <a href="#" class="link-bag" @click="handleMobileBagBtn" />
                </li>
            </ul>

            <ul
                class="desktop-nav"
                :class="{ 'move-down': isSearchBarActive, hide: !isSearchHide }"
            >
                <li>
                    <router-link to="/" class="link-logo"></router-link>
                </li>
                <li>
                    <router-link to="/">商店</router-link>
                </li>
                <li>
                    <a href="#">🖥 Mac</a>
                </li>
                <li>
                    <a href="#">📱 iPhone</a>
                </li>
                <li>
                    <a href="#">⌚️ Watch</a>
                </li>
                <li>
                    <a href="#">🎧 AirPods</a>
                </li>
                <li>
                    <a href="#">🏠 家居</a>
                </li>

                <li>
                    <a href="#">🕶 配件</a>
                </li>
                <li>
                    <a href="#">💡 技术支持</a>
                </li>
                <li>
                    <a
                        href="#"
                        class="link-search"
                        @click="handleSearchBtn"
                    ></a>
                </li>
                <li>
                    <a href="#" class="link-bag" @click="handleBagBtn"></a>
                </li>
            </ul>
        </nav>

        <!-- End of navigation menu items -->

        <div class="search-container" :class="isSearchHide ? 'hide' : ''">
            <div class="link-search"></div>
            <div class="search-bar">
                <form action="">
                    <input type="text" placeholder="搜索 apple.com" />
                </form>
            </div>
            <div class="link-close" @click="handleCloseBtn"></div>

            <div class="quick-links">
                <h2>快速链接</h2>
                <ul>
                    <li>
                        <a href="#">查找零售店</a>
                    </li>
                    <li>
                        <a href="#">配件</a>
                    </li>
                    <li>
                        <a href="#">AirPods</a>
                    </li>
                    <li>
                        <a href="#">AirTag</a>
                    </li>
                    <li>
                        <a href="#">AppleCare+ 服务计划</a>
                    </li>
                </ul>
            </div>
        </div>

        <div class="mobile-search-container">
            <div class="link-search"></div>
            <div class="search-bar" :class="isSearchBarActive ? 'active' : ''">
                <form action="">
                    <input
                        type="text"
                        placeholder="搜索 apple.com"
                        @click="handleSearchInputClick"
                    />
                </form>
            </div>
            <span class="cancel-btn" @click="handleCancelBtn">Cancel</span>

            <div class="quick-links">
                <h2>快速链接</h2>
                <ul>
                    <li>
                        <a href="#">查找零售店</a>
                    </li>
                    <li>
                        <a href="#">配件</a>
                    </li>
                    <li>
                        <a href="#">AirPods</a>
                    </li>
                    <li>
                        <a href="#">AirTag</a>
                    </li>
                    <li>
                        <a href="#">AppleCare+ 服务计划</a>
                    </li>
                </ul>
            </div>
        </div>

        <div class="bag-container" :class="{ show: isBagShow }">
            <div class="bag-scrim">
                <span class="bag-caret bag-caret-small"></span>
            </div>
            <div class="bag-content">
                <p class="bag-message">🛒 购物袋内容</p>
                <div class="bag-nav">
                    <ul class="bag-nav-list bag-nav-nobtn">
                        <li class="bag-nav-item bag-nav-item-bag">
                            <a href="#" class="bag-nav-link bag-nav-link-bag">
                                购物袋
                            </a>
                        </li>
                        <li class="bag-nav-item bag-nav-item-savedbyyou">
                            <a
                                href="#"
                                class="bag-nav-link bag-nav-link-savedbyyou"
                            >
                                收藏商品
                            </a>
                        </li>
                        <li class="bag-nav-item bag-nav-item-orders">
                            <a
                                href="#"
                                class="bag-nav-link bag-nav-link-orders"
                            >
                                订单
                            </a>
                        </li>
                        <li class="bag-nav-item bag-nav-item-account">
                            <a
                                href="#"
                                class="bag-nav-link bag-nav-link-account"
                            >
                                账户
                            </a>
                        </li>
                        <li class="bag-nav-item bag-nav-item-signIn">
                            <router-link
                                to="/login"
                                class="bag-nav-link bag-nav-link-signIn"
                            >
                                登录
                            </router-link>
                        </li>
                    </ul>
                </div>
            </div>
        </div>

        <div class="mobile-bag-container" :class="{ show: isMobileBagShow }">
            <div class="bag-content">
                <p class="bag-message">🛒 购物袋内容</p>
                <div class="bag-nav">
                    <ul class="bag-nav-list bag-nav-nobtn">
                        <li class="bag-nav-item bag-nav-item-bag">
                            <a href="#" class="bag-nav-link bag-nav-link-bag">
                                购物袋
                            </a>
                        </li>
                        <li class="bag-nav-item bag-nav-item-savedbyyou">
                            <a
                                href="#"
                                class="bag-nav-link bag-nav-link-savedbyyou"
                            >
                                收藏商品
                            </a>
                        </li>
                        <li class="bag-nav-item bag-nav-item-orders">
                            <a
                                href="#"
                                class="bag-nav-link bag-nav-link-orders"
                            >
                                订单
                            </a>
                        </li>
                        <li class="bag-nav-item bag-nav-item-account">
                            <a
                                href="#"
                                class="bag-nav-link bag-nav-link-account"
                            >
                                账户
                            </a>
                        </li>
                        <li class="bag-nav-item bag-nav-item-signIn">
                            <a
                                href="#"
                                class="bag-nav-link bag-nav-link-signIn"
                            >
                                登录
                            </a>
                        </li>
                    </ul>
                </div>
            </div>
        </div>
    </div>
    <div
        class="overlay"
        :class="{ show: isOverlayShow }"
        @click="handleOverlay"
    ></div>

    <div
        class="bag-mask"
        :class="{ show: isBagMaskShow }"
        @click="handleBagMask"
    ></div>
</template>

<style lang="less" scoped>
@import "static/css/style";
</style>
