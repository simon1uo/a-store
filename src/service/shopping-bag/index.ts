import api from "@/service"
import type {
    IShoppingBagAddItem,
    IShoppingBagUpdateItem
} from "@/service/shopping-bag/type"

const shoppingBagAPI = {
    getBag: "/bag",
    getBagItemByIds: "/bag/settle",
    addToBag: "/bag",
    updateBag: "/bag",
    deleteBag: "/bag/" // + id
}

export function getBagList() {
    return api.get(shoppingBagAPI.getBag)
}

export function addToBag(param: IShoppingBagAddItem) {
    return api.post(shoppingBagAPI.addToBag, param)
}

export function updateBagItem(param: IShoppingBagUpdateItem) {
    return api.put(shoppingBagAPI.updateBag, param)
}

export function removeBagItem(bagItemId: number) {
    return api.delete(shoppingBagAPI.deleteBag + bagItemId)
}
