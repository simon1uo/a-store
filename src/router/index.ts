import { createRouter, createWebHistory } from "vue-router"
import type { RouteRecordRaw } from "vue-router"

import storeAccount from "./modules/store-account"

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
    storeAccount
]

const router = createRouter({
    history: createWebHistory(import.meta.env.BASE_URL),
    routes: constantRoute
})

export default router
