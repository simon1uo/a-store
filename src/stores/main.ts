import { defineStore } from "pinia"
import { getCategoryCardItems } from "@/service/main"

export const useMainStore = defineStore({
    id: "main",
    state: () => ({
        categoryCardItems: []
    }),
    getters: {},
    actions: {
        async getCategoryCardItemsAction() {
            const result = await getCategoryCardItems()
            this.categoryCardItems = result.data
        }
    }
})
