import { defineStore } from "pinia"
import {
    addUserAddress,
    deleteUserAddress,
    getUserAddressList,
    getUserDefaultAddress,
    setDefaultUserAddress,
    updateUserAddress
} from "@/service/user-address"
import type { IUserAddressData } from "@/service/user-address/type"
import { ElNotification } from "element-plus"

export const useUserAddressStore = defineStore({
    id: "userAddress",
    state: () => ({
        userDefaultAddress: {
            addressId: "",
            userName: "",
            userPhone: "",
            provinceName: "",
            cityName: "",
            regionName: "",
            detailAddress: ""
        },
        userAddressList: []
    }),
    getters: {
        defaultAddress: (state) => {
            return {
                addressId: state.userDefaultAddress.addressId,
                userName: state.userDefaultAddress.userName,
                userPhone: state.userDefaultAddress.userPhone,
                pcrAddress: [
                    state.userDefaultAddress.provinceName,
                    state.userDefaultAddress.cityName,
                    state.userDefaultAddress.regionName
                ],
                detailAddress: state.userDefaultAddress.detailAddress
            }
        },
        addressList: (state) => {
            return state.userAddressList.map((item: any) => {
                return {
                    addressId: item.addressId,
                    userName: item.userName,
                    userPhone: item.userPhone,
                    pcrAddress: [
                        item.provinceName,
                        item.cityName,
                        item.regionName
                    ],
                    detailAddress: item.detailAddress
                }
            })
        }
    },
    actions: {
        async getUserDefaultAddress() {
            const result = await getUserDefaultAddress()
            this.userDefaultAddress = result.data
        },
        async getUserAddressListAction() {
            const result = await getUserAddressList()
            this.userAddressList = result.data
        },
        async editUserAddressAction(payload: IUserAddressData) {
            const result = await updateUserAddress(payload)
            this.getUserDefaultAddress()
            this.getUserAddressListAction()
            ElNotification.success(result.data)
        },
        async addUserAddressAction(payload: IUserAddressData) {
            const result = await addUserAddress(payload)
            this.getUserAddressListAction()
            ElNotification.success(result.data)
        },
        async deleteUserAddressAction(addressId: number) {
            const result = await deleteUserAddress(addressId)
            this.getUserAddressListAction()
            ElNotification.success(result.data)
        },
        async setDefaultUserAddressAction(addressId: number) {
            const result = await setDefaultUserAddress(addressId)
            this.getUserDefaultAddress()
            this.getUserAddressListAction()
            ElNotification.success(result.data)
        }
    }
})
