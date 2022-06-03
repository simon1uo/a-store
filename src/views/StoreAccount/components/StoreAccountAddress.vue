<script lang="ts" setup>
import { computed, onMounted, ref } from "vue"
import { useUserAddressStore } from "@/stores/user-address"

import type { IUserAddressData } from "@/service/user-address/type"
import GlobalInputForm from "@/components/GlobalInputForm/src/GlobalInputForm.vue"
import GlobalTable from "@/components/GlobalTable/src/GlobalTable.vue"

import { addressEditFormConfig } from "@/views/StoreAccount/config/addressForm.config"
import { addressTableConfig } from "../config/addressTable.config"

const userAddressStore = useUserAddressStore()

onMounted(() => {
    userAddressStore.getUserAddressListAction()
    userAddressStore.getUserDefaultAddress()
})

const defaultAddressData = computed(() => userAddressStore.defaultAddress)

const addressListData = computed(() => {
    return userAddressStore.userAddressList.map((item: IUserAddressData) => {
        return {
            addressId: item.addressId,
            userName: item.userName,
            userPhone: item.userPhone,
            address: `${item.provinceName} ${item.cityName} ${item.regionName} ${item.detailAddress}`,
            pcrAddress: [item.provinceName, item.cityName, item.regionName],
            detailAddress: item.detailAddress,
            defaultFlag: !!item.defaultFlag
        }
    })
})

const inputFormDialogRef = ref<InstanceType<any>>(null)

const inputFormTitle = ref("")

const inputFormData = ref({
    addressId: "",
    userName: "",
    userPhone: "",
    pcrAddress: [""],
    detailAddress: ""
})

const inputFormSubmitType = ref("")

const resetInputForm = () => {
    inputFormData.value = {
        addressId: "",
        userName: "",
        userPhone: "",
        pcrAddress: [""],
        detailAddress: ""
    }
}

const handleEditDefaultAddressClick = () => {
    inputFormSubmitType.value = "edit"
    inputFormTitle.value = "编辑默认地址"
    inputFormData.value = defaultAddressData.value
    inputFormDialogRef.value.handleOpen()
}

const handleAddAddressClick = () => {
    inputFormSubmitType.value = "add"
    resetInputForm()
    inputFormTitle.value = "添加地址信息"
    inputFormDialogRef.value.handleOpen()
}

const handleCancelClick = () => {
    resetInputForm()
    inputFormDialogRef.value.handleClose()
}

const handleEditConfirmClick = () => {
    userAddressStore.editUserAddressAction({
        addressId: inputFormData.value.addressId,
        userName: inputFormData.value.userName,
        userPhone: inputFormData.value.userPhone,
        provinceName: inputFormData.value.pcrAddress[0],
        cityName: inputFormData.value.pcrAddress[1],
        regionName: inputFormData.value.pcrAddress[2],
        detailAddress: inputFormData.value.detailAddress
    })
    inputFormDialogRef.value.handleClose()
}

const handleAddConfirmClick = () => {
    userAddressStore.addUserAddressAction({
        userName: inputFormData.value.userName,
        userPhone: inputFormData.value.userPhone,
        provinceName: inputFormData.value.pcrAddress[0],
        cityName: inputFormData.value.pcrAddress[1],
        regionName: inputFormData.value.pcrAddress[2],
        detailAddress: inputFormData.value.detailAddress
    })
    inputFormDialogRef.value.handleClose()
}

const handleDeleteClick = (addressId: any) => {
    userAddressStore.deleteUserAddressAction(addressId)
}

const handleEditClick = (addressItem: any) => {
    inputFormSubmitType.value = "edit"
    inputFormTitle.value = "编辑地址"
    inputFormData.value = addressItem
    inputFormDialogRef.value.handleOpen()
}

const handleSetDefault = (addressId: any) => {
    userAddressStore.setDefaultUserAddressAction(addressId)
}
</script>

<template>
    <div id="user-account-address">
        <div class="manage-container">
            <h2 class="title">收货地址</h2>
            <div class="manage-content">
                <el-row :gutter="10">
                    <el-col :lg="6">
                        <h3>我的默认收货地址</h3>
                    </el-col>
                    <el-col :lg="18">
                        <el-row v-if="defaultAddressData.addressId">
                            <el-col :lg="12">
                                <div class="content-item">
                                    姓名
                                    <div class="info">
                                        {{ defaultAddressData.userName }}
                                    </div>
                                    手机
                                    <div class="info">
                                        {{ defaultAddressData.userPhone }}
                                    </div>
                                    <div
                                        class="info-link"
                                        @click="handleEditDefaultAddressClick"
                                    >
                                        编辑默认收货地址
                                    </div>
                                </div>
                            </el-col>
                            <el-col :lg="12">
                                <div class="content-item">
                                    送货地址
                                    <div
                                        class="info"
                                        v-for="(
                                            data, index
                                        ) in defaultAddressData.pcrAddress"
                                        :key="index"
                                    >
                                        {{ data }}
                                    </div>

                                    <div class="info">
                                        {{ defaultAddressData.detailAddress }}
                                    </div>
                                </div>
                            </el-col>
                        </el-row>
                        <div class="null-tip" v-else>默认地址为空</div>
                    </el-col>
                </el-row>
                <el-row :gutter="10">
                    <el-col :lg="6"><h3>我的收货地址列表</h3></el-col>
                    <el-col :lg="18">
                        <div class="content-item">
                            <GlobalTable
                                v-bind="addressTableConfig"
                                :table-data="addressListData"
                            >
                                <template #default="scope">
                                    <el-button
                                        v-if="scope.row.defaultFlag"
                                        size="small"
                                        type="success"
                                    >
                                        默认
                                    </el-button>
                                    <el-button
                                        v-if="!scope.row.defaultFlag"
                                        size="small"
                                        type="info"
                                        @click="
                                            handleSetDefault(
                                                scope.row.addressId
                                            )
                                        "
                                    >
                                        设为默认
                                    </el-button>
                                </template>
                                <template #handler="scope">
                                    <el-button
                                        type="success"
                                        size="small"
                                        @click="handleEditClick(scope.row)"
                                        >编辑</el-button
                                    >
                                    <el-popconfirm
                                        title="是否确认删除?"
                                        @confirm="
                                            handleDeleteClick(
                                                scope.row.addressId
                                            )
                                        "
                                    >
                                        <template #reference>
                                            <el-button
                                                type="warning"
                                                size="small"
                                                >删除</el-button
                                            >
                                        </template>
                                    </el-popconfirm></template
                                >
                            </GlobalTable>
                            <div
                                class="info-link"
                                @click="handleAddAddressClick"
                            >
                                添加收货地址
                            </div>
                        </div>
                    </el-col>
                </el-row>
            </div>
        </div>
    </div>
    <GlobalDialog ref="inputFormDialogRef">
        <GlobalInputForm
            :title="inputFormTitle"
            v-bind="addressEditFormConfig"
            v-model:model-value="inputFormData"
            :submit-type="inputFormSubmitType"
            @handleCancelClick="handleCancelClick"
            @handleConfirmClick="handleEditConfirmClick"
            @handleAddConfirmClick="handleAddConfirmClick"
        />
    </GlobalDialog>
</template>

<style lang="less" scoped>
#user-account-address {
    .manage-container {
        .title {
            margin-top: 5px;
            margin-bottom: 20px;
        }

        .manage-content {
            margin-bottom: 100px;
            .content-item {
                padding: 5px;
                font-size: 16px;
                font-weight: 400;
            }

            .info {
                font-weight: 300;
                margin: 10px 0 10px 0;
            }

            .info-link {
                cursor: pointer;
                color: #06c;
            }
            .info-link:hover {
                text-decoration: underline;
            }
        }
    }
}
</style>
