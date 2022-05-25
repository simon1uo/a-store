<script lang="ts" setup>
import { computed, reactive, ref } from "vue"
import GlobalFooter from "@/components/GlobalFooter/GlobalFooter.vue"
import { ArrowRightBold } from "@element-plus/icons-vue"

import type { FormInstance } from "element-plus"
import { useUserStore } from "@/stores/user"

import { signinRuleConfig } from "@/views/StoreSignIn/config/signin-rule.config"
import { signupRuleConfig } from "@/views/StoreSignIn/config/signup-rule.config"
import { getCache, removeCache, setCache } from "@/utils/local-cache"
import { ElNotification } from "element-plus"

const userStore = useUserStore()

let formType = ref("signin")

const toggleFormType = (type: string) => {
    formType.value = type
}

const signinModel = reactive({
    userAccount: getCache("userAccount") ?? "",
    userPassword: getCache("userPassword") ?? ""
})

const signupModel = reactive({
    userAccount: "",
    userPassword: "",
    checkPassword: ""
})

const signInFromRef = ref<FormInstance>()
const signUpFormRef = ref<FormInstance>()

const isRememberMe = ref(false)

const handleSignInAction = () => {
    signInFromRef.value?.validate((valid) => {
        if (valid) {
            if (isRememberMe.value) {
                setCache("userAccount", signinModel.userAccount)
                setCache("userPassword", signinModel.userPassword)
            } else {
                removeCache("userAccount")
                removeCache("userPassword")
            }
            userStore.userSignInAction({ ...signinModel })
        } else {
            ElNotification.error("验证有误")
        }
    })
}

const validateCheckPassword = (rule: any, value: any, callback: any) => {
    if (value !== signupModel.userPassword) {
        callback(new Error("两次密码输入不一致"))
    } else {
        callback()
    }
}

const handleSignUpAction = () => {
    signUpFormRef.value?.validate((valid) => {
        if (valid) {
            userStore.userSignUpAction({ ...signupModel })
            signUpFormRef.value?.resetFields()
        } else {
            ElNotification.error("验证有误")
        }
    })
}
</script>
<template>
    <div id="store-sign-in">
        <div class="sign-in-container">
            <div class="sign-in-header">
                <h1>现在登录，结账更快捷。</h1>
            </div>

            <div v-if="formType === 'signin'" class="sign-in-main">
                <div class="sign-in-content">
                    <div class="sign-in-subtitle">
                        <h2>登录</h2>
                    </div>

                    <div class="sign-in-form">
                        <el-form
                            :rules="signinRuleConfig"
                            :model="signinModel"
                            ref="signInFromRef"
                        >
                            <el-form-item size="large" prop="userAccount">
                                <el-input
                                    placeholder="Account"
                                    v-model="signinModel.userAccount"
                                />
                            </el-form-item>
                            <el-form-item size="large" prop="userPassword">
                                <el-input
                                    placeholder="Password"
                                    v-model="signinModel.userPassword"
                                    show-password
                                />
                            </el-form-item>
                        </el-form>
                        <el-button
                            :icon="ArrowRightBold"
                            size="large"
                            type="primary"
                            color="#06c"
                            circle
                            plain
                            @click="handleSignInAction"
                        />

                        <div class="account-control">
                            <el-checkbox color="#06c" v-model="isRememberMe"
                                >记住我的 ID</el-checkbox
                            >
                        </div>
                    </div>

                    <div class="separator"></div>
                    <div class="sign-in-subbody">
                        <div class="sign-in-forgot-password">
                            <a class="sign-in-link" href="#">
                                忘记了 Apple&nbsp;ID 或 密码？
                            </a>
                        </div>

                        <div>
                            <span class="fat"> 没有 Apple&nbsp;ID？</span>
                            <a
                                class="sign-in-link"
                                @click="toggleFormType('signup')"
                            >
                                立即创建您的 ID。
                            </a>
                        </div>
                    </div>
                </div>
            </div>
            <div v-if="formType === 'signup'" class="sign-in-main">
                <div class="sign-in-content">
                    <div class="sign-in-subtitle">
                        <h2>创建账号</h2>
                    </div>

                    <div class="sign-in-form">
                        <el-form
                            :rules="signupRuleConfig"
                            :model="signupModel"
                            ref="signUpFormRef"
                        >
                            <el-form-item size="large" prop="userAccount">
                                <el-input
                                    placeholder="Input Account"
                                    v-model="signupModel.userAccount"
                                />
                            </el-form-item>
                            <el-form-item size="large" prop="userPassword">
                                <el-input
                                    placeholder="Input Password"
                                    v-model="signupModel.userPassword"
                                    show-password
                                />
                            </el-form-item>
                            <el-form-item
                                size="large"
                                prop="checkPassword"
                                :rules="{ validator: validateCheckPassword }"
                            >
                                <el-input
                                    placeholder="Input Password Again"
                                    v-model="signupModel.checkPassword"
                                    show-password
                                />
                            </el-form-item>
                        </el-form>
                        <el-button
                            :icon="ArrowRightBold"
                            size="large"
                            type="primary"
                            color="#06c"
                            circle
                            plain
                            @click="handleSignUpAction"
                        />
                    </div>

                    <div class="separator"></div>
                    <div class="sign-in-subbody">
                        <span class="fat"> 已经有账号？</span>
                        <a
                            class="sign-in-link"
                            @click="toggleFormType('signin')"
                        >
                            立即登录您的账号。
                        </a>
                    </div>
                </div>
            </div>

            <GlobalFooter />
        </div>
    </div>
</template>

<style lang="less" scoped>
@media only screen and (min-width: 1441px) {
    .sign-in-container {
        margin-left: auto;
        margin-right: auto;
        width: 980px;
    }
}

.sign-in-container {
    padding: 10px;
    margin-top: 44px;
    .sign-in-header {
        padding-top: 34px;
        h1 {
            font-size: 35px;
        }
    }

    .sign-in-main {
        display: flex;
        flex-wrap: wrap;
        flex-direction: row;
        .sign-in-content {
            background: #fff;
            border-radius: 18px;
            border: 1px solid #d2d2d7;
            margin: 0 auto;
            .sign-in-subtitle {
                margin: 20px 130px 13px;
                h2 {
                    font-weight: 400;
                    font-size: 25px;
                    color: #494949;
                }
            }

            .sign-in-form {
                .el-form-item {
                    padding: 0 10px 0 10px;
                }
                text-align: center;
                padding-bottom: 5px;
            }
            .separator {
                width: 75%;
                border-bottom: 1px solid #fafafa;
            }

            .sign-in-subbody {
                font-size: 13px;
                text-align: center;
                margin-top: 17px;
                padding-bottom: 9px;

                .sign-in-forgot-password {
                    padding-bottom: 9px;
                }
                .fat {
                    font-weight: 400;
                }
                .sign-in-link {
                    cursor: pointer;
                    text-decoration: none;
                    margin: 0;
                    display: inline;
                    color: #0070c9;
                }
            }
        }
    }
}
</style>
