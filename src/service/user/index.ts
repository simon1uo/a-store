import api from "@/service"
import type { IUserSignInData, IUserSignUpData } from "@/service/user/type"

/*用户信息相关API*/
const userAPI = {
    userSignIn: "/user/signin",
    userSignOut: "/user/signout",
    userSignUp: "/user/signup",
    userInfo: "/user/info"
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

export function getUserInfo() {
    return api.get(userAPI.userInfo)
}
