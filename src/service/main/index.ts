import api from "@/service"

const mainAPI = {
    categoryCardItems: "/index/category-card"
}

export function getCategoryCardItems() {
    return api.get(mainAPI.categoryCardItems)
}
