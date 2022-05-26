import { defineStore } from "pinia"
import {
    addToBag,
    getBagList,
    removeBagItem,
    updateBagItem
} from "@/service/shopping-bag"
import type {
    IShoppingBagAddItem,
    IShoppingBagUpdateItem
} from "@/service/shopping-bag/type"
import { ElNotification } from "element-plus"
import router from "@/router"

export const useShoppingBagStore = defineStore({
    id: "shoppingBag",
    state: () => ({
        myBagList: []
    }),
    actions: {
        async getBagListAction() {
            const result = await getBagList()
            this.myBagList = result.data
        },
        async addToBagAction(payload: IShoppingBagAddItem) {
            const result = await addToBag(payload)
            ElNotification.success(result.data)

            router.push("/store/bag")
        },
        async updateBagItemAction(payload: IShoppingBagUpdateItem) {
            const result = await updateBagItem(payload)
            ElNotification.success(result.data)
            router.push("/store/bag")
        },
        async removeBagItemAction(bagItemId: number) {
            const result = await removeBagItem(bagItemId)
            ElNotification.success(result.data)
            router.push("/store/bag")
            this.getBagListAction()
        }
    }
})
