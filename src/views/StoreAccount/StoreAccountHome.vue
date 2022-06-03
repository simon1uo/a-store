<script lang="ts" setup>
import { computed } from "vue"
import { useUserStore } from "@/stores/user"
import router from "@/router"
import { useRoute } from "vue-router"
import GlobalFooter from "@/components/GlobalFooter"

const userStore = useUserStore()
const userName = computed(() => userStore.userName)

const handleGoto = (target: string) => {
    router.push("/store/account" + target)
}

const handleSignOut = () => {
    userStore.userSignOutAction()
}

const route = useRoute()
const activateRoute = computed(() => route.name)
</script>

<template>
    <div id="store-account-home">
        <div class="home-container">
            <div class="home-navigation">
                <div class="navigation-header">账户 Account</div>
                <div class="sign-out-link">
                    <a @click="handleSignOut"> 退出登录</a>
                </div>
            </div>
            <div class="home-header">
                <h1>{{ userName }}, 你好！</h1>
            </div>
            <div class="header-sub-navigation">
                <a
                    @click="handleGoto('/manage')"
                    :class="{ active: activateRoute === 'manage' }"
                    >个人账户管理</a
                >
                <a
                    @click="handleGoto('/address')"
                    :class="{ active: activateRoute === 'address' }"
                    >收货地址管理</a
                >
            </div>
            <div class="home-content">
                <router-view />
            </div>
            <GlobalFooter />
        </div>
    </div>
</template>

<style lang="less" scoped>
@media only screen and (min-width: 1441px) {
    .home-container {
        margin-left: auto;
        margin-right: auto;
        width: 980px;
    }
}

.home-container {
    padding: 0 3px 0 3px;
    margin-top: 44px;

    .home-navigation {
        height: 52px;
        display: flex;
        justify-content: space-between;
        align-items: center;
        border-bottom: 1px solid #cececf;

        .navigation-header {
            font-size: 22px;
            font-weight: 500;
        }

        .sign-out-link {
            margin-top: 0.8em;
            font-size: 15px;
            cursor: pointer;

            a {
                color: #06c;
            }

            a:hover {
                text-decoration: underline;
            }

            a::after {
                padding: 2px;
                font-size: 6px;
                content: ">";
            }
        }
    }

    .home-header {
        display: flex;
        justify-content: center;

        h1 {
            padding-top: 59px;
        }
    }

    .header-sub-navigation {
        text-align: center;
        padding: 5px 5px 15px 5px;

        a {
            cursor: pointer;
            color: #06c;
            margin-right: 5px;
        }

        a:hover {
            text-decoration: underline;
        }

        .active {
            font-weight: 600;
        }
    }

    .home-content {
        border-radius: 5px;
        padding: 30px;
        background: #fff;
    }
}
</style>
