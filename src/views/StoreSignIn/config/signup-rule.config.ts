import type { FormRules } from "element-plus"

export const signupRuleConfig: FormRules = {
    userAccount: [
        {
            required: true,
            message: "请输入您的账号",
            trigger: "blur"
        },
        {
            pattern: /^[a-z0-9]{5,10}$/,
            message: "账号账号不合法",
            trigger: "blur"
        }
    ],
    userPassword: [
        {
            required: true,
            message: "请输入账号密码",
            trigger: "blur"
        },
        {
            min: 8,
            message: "账号密码不合法",
            trigger: "blur"
        }
    ],
    checkPassword: [
        {
            required: true,
            message: "请再次输入账号密码",
            trigger: "blur"
        },
        {
            min: 8,
            pattern: /^[a-z0-9]{3,}$/,
            message: "账号密码不合法",
            trigger: "blur"
        }
    ]
}
