import { defineStore } from "pinia"
import {
    getUserInfo,
    getUserSignInStatus,
    updateUserInfo,
    userSignIn,
    userSignOut,
    userSignUp
} from "@/service/user"
import type { IUserSignInData, IUserSignUpData } from "@/service/user/type"
import { getCache, removeCache, setCache } from "@/utils/local-cache"
import router from "@/router"
import { ElNotification } from "element-plus"

export const useUserStore = defineStore({
    id: "user",
    state: () => ({
        userInfo: getCache("userInfo") ?? {},
        token: getCache("token") ?? "",
        userStatus: false
    }),
    getters: {
        isUserSignIn: (state) => {
            return state.token && state.userStatus
        },
        userName: (state) => {
            return state.userInfo.userName
        }
    },
    actions: {
        async userSignInAction(payload: IUserSignInData) {
            const signInResult = await userSignIn(payload)

            this.token = signInResult.data
            setCache("token", this.token)

            const getUserInfoResult = await getUserInfo()
            this.userInfo = getUserInfoResult.data
            setCache("userInfo", this.userInfo)
            ElNotification.success("登录成功")

            router.push("/")
        },

        async userSignUpAction(payload: IUserSignUpData) {
            const signUpResult = await userSignUp(payload)
            setCache("userAccount", payload.userAccount)
            removeCache("userPassword")
            ElNotification.success("注册成功")
            router.push("/signin")
        },

        async userSignOutAction() {
            router.push("/")
            ElNotification.success("退出登录成功")
            const result = await userSignOut()
            this.token = ""
            this.userInfo = ""
            removeCache("userInfo")
            removeCache("token")
        },

        async getUserSignInStatusAction() {
            const result = await getUserSignInStatus()
            this.userStatus = result.data
        },

        async updateUserInfoAction(payload: any) {
            const result = await updateUserInfo(payload)
            const getUserInfoResult = await getUserInfo()
            this.userInfo = getUserInfoResult.data
            setCache("userInfo", this.userInfo)
            ElNotification.success(result.data)
        }
    }
})
