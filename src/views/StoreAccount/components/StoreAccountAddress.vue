<script lang="ts" setup>
import { computed, onMounted } from "vue"
import { useUserAddressStore } from "@/stores/user-address"
import type { IUserAddressData } from "@/service/user-address/type"

const userAddressStore = useUserAddressStore()
onMounted(() => {
    userAddressStore.getUserAddressListAction()
    userAddressStore.getUserDefaultAddress()
})

const defaultAddressData = computed(() => userAddressStore.userDefaultAddress)

const addressListData = computed(() => {
    return userAddressStore.userAddressList.map((item: IUserAddressData) => {
        return {
            id: item.addressId,
            name: item.userName,
            phone: item.userPhone,
            address: `${item.provinceName} ${item.cityName} ${item.regionName} ${item.detailAddress}`,
            isDefault: !!item.defaultFlag
        }
    })
})
</script>

<template>
    <div id="user-account-address">
        <div class="manage-container">
            <h2 class="title">收货地址</h2>
            <div class="manage-content">
                <el-row :gutter="10">
                    <el-col :lg="6"><h3>我的默认收货地址</h3></el-col>
                    <el-col :lg="18">
                        <el-row>
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
                                </div>
                            </el-col>
                            <el-col :lg="12">
                                <div class="content-item">
                                    送货地址
                                    <div class="info">
                                        {{ defaultAddressData.provinceName }}
                                    </div>
                                    <div class="info">
                                        {{ defaultAddressData.cityName }}
                                    </div>
                                    <div class="info">
                                        {{ defaultAddressData.regionName }}
                                    </div>
                                    <div class="info">
                                        {{ defaultAddressData.detailAddress }}
                                    </div>
                                </div>
                            </el-col>
                        </el-row>
                    </el-col>
                </el-row>
                <el-row :gutter="10">
                    <el-col :lg="6"><h3>我的收货地址列表</h3></el-col>
                    <el-col :lg="18">
                        <div class="content-item">
                            <el-table
                                :data="addressListData"
                                stripe
                                style="width: 100%"
                            >
                                <el-table-column
                                    prop="id"
                                    label="id"
                                    width="50"
                                />
                                <el-table-column
                                    prop="name"
                                    label="收件人"
                                    width="80"
                                />
                                <el-table-column
                                    prop="phone"
                                    label="联系方式"
                                    width="120"
                                />
                                <el-table-column prop="address" label="地址" />
                                <el-table-column
                                    prop="isDefault"
                                    label="是否为默认"
                                    width="80"
                                />
                                <el-table-column label="操作" width="80" />
                            </el-table>
                        </div>
                    </el-col>
                </el-row>
            </div>
        </div>
    </div>
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
        }
    }
}
</style>
