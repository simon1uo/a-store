<script lang="ts" setup>
import { useUserStore } from "@/stores/user"
import { computed, ref } from "vue"
import { useRoute, useRouter } from "vue-router"

const router = useRouter()
const route = useRoute()
const userStore = useUserStore()

const userInfo = computed(() => userStore.userInfo)
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
                            账号
                            <span class="info">{{ userInfo.userAccount }}</span>
                        </div>
                        <div class="content-item">
                            姓名
                            <span class="info">{{ userInfo.userName }}</span>
                        </div>
                        <div class="content-item">
                            联系信息
                            <span class="info"> ～ ～</span>
                        </div>
                        <div class="content-item">
                            <img
                                src="../static/images/stripofapps-2018-icon.jpeg"
                            />
                        </div>
                    </el-col>
                </el-row>
            </div>
        </div>
    </div>
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
                padding: 5px;
                margin-bottom: 10px;
                font-size: 16px;
                font-weight: 400;
                .info {
                    font-weight: 300;
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
