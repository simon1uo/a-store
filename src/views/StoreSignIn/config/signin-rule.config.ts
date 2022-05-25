import type { FormRules } from "element-plus"

export const signinRuleConfig: FormRules = {
    userAccount: [
        {
            required: true,
            message: "请输入您的账号",
            trigger: "blur"
        },
        {
            min: 4,
            message: "账号不合法",
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
    ]
}
