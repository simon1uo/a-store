<script lang="ts" setup>
import { useUserStore } from "@/stores/user"
import { computed, ref, watch } from "vue"
import { useRoute, useRouter } from "vue-router"
import { accountEditFormConfig } from "@/views/StoreAccount/config/accountEditForm.config"

import GlobalDialog from "@/components/GlobalDialog/src/GlobalDialog.vue"
import GlobalInputForm from "@/components/GlobalInputForm/src/GlobalInputForm.vue"

const router = useRouter()
const route = useRoute()
const userStore = useUserStore()

const userInfo = computed(() => userStore.userInfo)

const inputFormDialogRef = ref<InstanceType<any>>(null)

const editUserInfo = ref({})

watch(
    () => editUserInfo,
    (newValue: any) => {
        // console.log(newValue)
        // for (const item of accountEditFormConfig.formItems) {
        //     defaultValue.value[`${item.field}`] = newValue[`${item.field}`]
        // }
    }
)

const handleEditUserInfoClick = () => {
    editUserInfo.value = userInfo.value
    inputFormDialogRef.value.handleOpen()
}

const handleEditCancel = () => {
    inputFormDialogRef.value.handleClose()
    editUserInfo.value = userInfo.value
}

const handleEditConfirm = () => {
    inputFormDialogRef.value.handleClose()
    userStore.updateUserInfoAction(editUserInfo.value)
}
</script>

<template>
    <div id="user-account-manage">
        <div class="manage-container">
            <h2 class="title">账户设置</h2>
            <div class="manage-content">
                <el-row :gutter="10">
                    <el-col :lg="6"><h3>个人信息</h3></el-col>
                    <el-col :lg="18">
                        <div class="content-item">
                            <div class="info-title">头像</div>
                            <div class="info-avatar">
                                <img :src="userInfo.userAvatarUrl" alt="" />
                            </div>
                        </div>
                        <div class="content-item">
                            <div class="info-title">账号</div>
                            <span class="info">{{ userInfo.userAccount }}</span>
                        </div>
                        <div class="content-item">
                            <div class="info-title">姓名</div>
                            <span class="info">{{ userInfo.userName }}</span>
                        </div>
                        <div class="content-item">
                            <div class="info-title">电子邮箱 Email</div>
                            <span class="info">{{ userInfo.userEmail }}</span>
                        </div>
                        <div class="content-item">
                            <div class="info-title">联系电话 Phone</div>
                            <span class="info">{{ userInfo.userPhone }}</span>
                        </div>
                        <div class="content-item">
                            <div
                                class="info-link"
                                @click="handleEditUserInfoClick"
                            >
                                编辑个人信息
                            </div>
                            <img
                                src="../static/images/stripofapps-2018-icon.jpeg"
                            />
                        </div>
                    </el-col>
                </el-row>
            </div>
        </div>
    </div>
    <GlobalDialog ref="inputFormDialogRef">
        <GlobalInputForm
            title="编辑用户信息"
            v-bind="accountEditFormConfig"
            v-model:model-value="editUserInfo"
            @handleConfirmClick="handleEditConfirm"
            @handleCancelClick="handleEditCancel"
        />
    </GlobalDialog>
</template>

<style lang="less" scoped>
#user-account-manage {
    .manage-container {
        .title {
            margin-top: 5px;
            margin-bottom: 20px;
        }

        .manage-content {
            .content-item {
                display: flex;
                justify-content: space-between;
                align-items: start;
                margin-bottom: 10px;
                font-size: 16px;
                padding: 5px;
                .info-title {
                    font-weight: 400;
                }
                .info {
                    font-weight: 300;
                }
                .info-avatar {
                    img {
                        width: 100px;
                        border-radius: 50%;
                    }
                }
                .info-link {
                    cursor: pointer;
                    color: #06c;
                }
                .info-link:hover {
                    text-decoration: underline;
                }
                img {
                    max-width: 300px;
                }
            }

            margin-bottom: 100px;
        }
    }
}
</style>
