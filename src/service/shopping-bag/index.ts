import api from "@/service"
import type { IShopingBagItem } from "@/service/shopping-bag/type"

const shoppingBagAPI = {
    getBag: "/bag",
    getBagItemByIds: "/bag/settle",
    addToBag: "/bag",
    modifyBag: "/bag",
    deleteBag: "/bag/" // + id
}

export function getCart(param: IShopingBagItem) {
    return api.get(shoppingBagAPI.getBag, { params: param })
}

export function addToBag(param: IShopingBagItem) {
    return api.post(shoppingBagAPI.addToBag, param)
}

export function modifyCart(param: IShopingBagItem) {
    return api.put(shoppingBagAPI.modifyBag, param)
}

export function deleteCartItem(id: string) {
    return api.delete(shoppingBagAPI.deleteBag + id)
}

export function getByCartItemIds(params: IShopingBagItem) {
    return api.get(shoppingBagAPI.getBagItemByIds, { params })
}
