import api from "@/service"
import type {
    IUserSignInData,
    IUserSignUpData,
    IUserUpdateData
} from "@/service/user/type"

/*用户信息相关API*/
const userAPI = {
    userSignIn: "/user/signin",
    userSignOut: "/user/signout",
    userSignUp: "/user/signup",
    userInfo: "/user/info",
    userStatus: "/user/status"
}

export function userSignIn(signInData: IUserSignInData) {
    return api.post(userAPI.userSignIn, {
        userAccount: signInData.userAccount,
        userPassword: signInData.userPassword
    })
}

export function userSignOut() {
    return api.get(userAPI.userSignOut)
}

export function userSignUp(signUpData: IUserSignUpData) {
    return api.post(userAPI.userSignUp, {
        userAccount: signUpData.userAccount,
        userPassword: signUpData.userPassword,
        checkPassword: signUpData.checkPassword
    })
}

export function getUserSignInStatus() {
    return api.get(userAPI.userStatus)
}

export function getUserInfo() {
    return api.get(userAPI.userInfo)
}

export function updateUserInfo(updateInfoData: IUserUpdateData) {
    return api.post(userAPI.userInfo, { ...updateInfoData })
}
