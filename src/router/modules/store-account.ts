export default {
    path: "/store/account",
    component: () => import("@/views/StoreAccount/StoreAccountHome.vue"),
    children: [
        {
            path: "manage",
            component: () =>
                import("@/views/StoreAccount/components/StoreAccountManage.vue")
        },
        {
            path: "address",
            component: () =>
                import(
                    "@/views/StoreAccount/components/StoreAccountAddress.vue"
                )
        }
    ]
}
