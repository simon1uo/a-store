import type { InputForm } from "@/components/GlobalInputForm/config/InputFormItem"

export const addressEditFormConfig: InputForm = {
    formItems: [
        {
            field: "userName",
            type: "input",
            label: "姓名 Name",
            placeholder: "请输入你的姓名"
        },
        {
            field: "userPhone",
            type: "input",
            label: "联系手机 Phone",
            placeholder: "请输入你的联系电话"
        },
        {
            field: "pcrAddress",
            type: "cascader",
            label: "地址"
        },
        {
            field: "detailAddress",
            type: "input",
            label: "详细地址",
            placeholder: "请输入详细地址"
        }
    ]
}
