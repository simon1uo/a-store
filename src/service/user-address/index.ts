import api from "@/service"
import type { IUserAddressData } from "@/service/user-address/type"

/*用户地址相关API*/
const userAddressAPI = {
    getUserAddressList: "/user/address",
    getUserDefaultAddress: "/user/address/default",
    getUserAddressById: "/user/address/", // +addressId
    addUserAddress: "/user/address",
    updateUserAddress: "/user/address",
    deleteUserAddressById: "/user/address/" // + addressId
}

export function getUserAddressList() {
    return api.get(userAddressAPI.getUserAddressList)
}

export function getUserDefaultAddress() {
    return api.get(userAddressAPI.getUserDefaultAddress)
}

export function getUserAddressById(addressId: string) {
    return api.get(userAddressAPI.getUserAddressById + addressId)
}

export function addUserAddress(userAddressData: IUserAddressData) {
    return api.post(userAddressAPI.addUserAddress, { userAddressData })
}

export function updateUserAddress(userAddressData: IUserAddressData) {
    return api.put(userAddressAPI.updateUserAddress, { userAddressData })
}

export function deleteUserAddress(addressId: string) {
    return api.delete(userAddressAPI.deleteUserAddressById, { data: addressId })
}
