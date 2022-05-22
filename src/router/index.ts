import { createRouter, createWebHistory } from "vue-router"

import StoreMain from "@/views/StoreMain/StoreMain.vue"

const router = createRouter({
    history: createWebHistory(import.meta.env.BASE_URL),
    routes: [
        {
            path: "/store",
            name: "Store",
            component: StoreMain
        },
        {
            path: "/",
            redirect: "/store"
        }
    ]
})

export default router
