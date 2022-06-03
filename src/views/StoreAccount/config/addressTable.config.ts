export const addressTableConfig = {
    propList: [
        { prop: "addressId", label: "id", width: "40" },
        { prop: "userName", label: "收件人", width: "80" },
        { prop: "userPhone", label: "联系手机", width: "100" },
        { prop: "address", label: "地址", width: "180" },
        {
            prop: "defaultFlag",
            label: "默认状态",
            minWidth: "60",
            slotName: "default"
        },
        {
            label: "操作",
            minWidth: "100",
            slotName: "handler"
        }
    ]
}
