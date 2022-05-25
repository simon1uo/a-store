import { defineStore } from "pinia"
import {
    getUserAddressList,
    getUserDefaultAddress
} from "@/service/user-address"

export const useUserAddressStore = defineStore({
    id: "userAddress",
    state: () => ({
        userDefaultAddress: {},
        userAddressList: []
    }),
    actions: {
        async getUserDefaultAddress() {
            const result = await getUserDefaultAddress()
            this.userDefaultAddress = result.data
        },
        async getUserAddressListAction() {
            const result = await getUserAddressList()
            this.userAddressList = result.data
        }
    }
})
