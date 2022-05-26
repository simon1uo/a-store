import api from "@/service"
import type {
    IShoppingBagAddItem,
    IShoppingBagUpdateItem
} from "@/service/shopping-bag/type"

const shoppingBagAPI = {
    getBag: "/bag",
    getBagItemByIds: "/bag/settle",
    addToBag: "/bag",
    modifyBag: "/bag",
    deleteBag: "/bag/" // + id
}

export function getBagList() {
    return api.get(shoppingBagAPI.getBag)
}

export function addToBag(param: IShoppingBagAddItem) {
    return api.post(shoppingBagAPI.addToBag, param)
}

export function updateBagItem(param: IShoppingBagUpdateItem) {
    return api.put(shoppingBagAPI.modifyBag, param)
}

export function removeBagItem(bagItemId: number) {
    return api.delete(shoppingBagAPI.deleteBag + bagItemId)
}

/*export function getByCartItemIds(params: IShoppingBagAddItem) {
    return api.get(shoppingBagAPI.getBagItemByIds, { params })
}*/
