import type { RouteRecordRaw } from "vue-router"
import { createRouter, createWebHistory } from "vue-router"

import storeAccount from "./modules/store-account"
import { useUserStore } from "@/stores/user"

const constantRoute: RouteRecordRaw[] = [
    {
        path: "/",
        redirect: "/store"
    },
    {
        path: "/store",
        name: "StoreMain",
        component: () => import("@/views/StoreMain/StoreMain.vue")
    },
    {
        path: "/signin/",
        name: "StoreSignIn",
        component: () => import("@/views/StoreSignIn/StoreSignIn.vue")
    },
    {
        path: "/store/bag",
        name: "StoreShoppingbag",
        component: () => import("@/views/StoreBag/StoreBag.vue")
    },
    {
        path: "/store/product/:id",
        props: true,
        name: "StoreProduct",
        component: () => import("@/views/StoreProduct/StoreProduct.vue")
    },
    storeAccount
]

const router = createRouter({
    history: createWebHistory(import.meta.env.BASE_URL),
    routes: constantRoute
})

router.beforeEach(async (to, from, next) => {
    const loginGuardNameList = [
        "/store/bag",
        "/store/order",
        "/store/account",
        "/store/product/"
    ]
    const userStore = useUserStore()
    await userStore.getUserSignInStatusAction()
    const isSignIn = userStore.isUserSignIn

    if (loginGuardNameList.includes(to.path)) {
        if (isSignIn) {
            next()
        } else {
            next({ name: "StoreSignIn" })
        }
    } else if (to.path === "/signin") {
        if (isSignIn) {
            next({ name: "StoreMain" })
        } else {
            next()
        }
    } else {
        next()
    }
})

router.afterEach((to, from) => {
    // 设置页面 title
})

export default router
