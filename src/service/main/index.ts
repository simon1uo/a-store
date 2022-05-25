import api from "@/service"

/* 主页相关API */
const mainAPI = {
    categoryCardItems: "/index/category-card"
}

export function getCategoryCardItems() {
    return api.get(mainAPI.categoryCardItems)
}
