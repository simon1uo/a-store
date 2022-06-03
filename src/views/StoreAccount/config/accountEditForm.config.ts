import type { InputForm } from "@/components/GlobalInputForm/config/InputFormItem"

export const accountEditFormConfig: InputForm = {
    formItems: [
        {
            field: "userName",
            type: "input",
            label: "姓名 Name",
            placeholder: "请输入你的姓名"
        },
        {
            field: "userEmail",
            type: "input",
            label: "电子邮箱 Email",
            placeholder: "请输入你的电子邮箱"
        },
        {
            field: "userPhone",
            type: "input",
            label: "联系电话 Phone",
            placeholder: "请输入你的联系电话"
        }
    ]
}
