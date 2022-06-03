type IFormType = "input" | "password" | "select" | "datepicker" | "cascader"
export interface InputFormItem {
    field: string
    type: IFormType
    label: string
    rules?: any[]
    placeholder?: any
    options?: any[]
    otherOptions?: any
    isHidden?: boolean
}

export interface InputForm {
    formItems: InputFormItem[]
    labelWidth?: string
    colLayout?: any
    itemLayout?: any
    itemStyle?: any
}
