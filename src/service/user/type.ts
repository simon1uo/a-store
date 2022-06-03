export interface IUserSignInData {
    userAccount: string
    userPassword: string
}

export interface IUserSignUpData {
    userAccount: string
    userPassword: string
    checkPassword: string
}

export interface IUserUpdateData {
    userName: string
    userEmail: string
    userPhone: string
}
