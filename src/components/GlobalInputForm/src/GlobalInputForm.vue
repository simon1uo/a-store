<script lang="ts" setup>
import type { PropType } from "vue"
import type { InputFormItem } from "@/components/GlobalInputForm/config/InputFormItem"
import CascaderAddressSelector from "@/components/CascaderAddressSelector/src/CascaderAddressSelector.vue"

const props = defineProps({
    title: {
        type: String,
        required: true
    },
    formItems: {
        type: Array as PropType<InputFormItem[]>,
        default: () => []
    },
    itemStyle: {
        type: Object,
        default: () => ({ padding: "10px 40px" })
    },
    modelValue: {
        type: Object,
        required: true
    },
    submitType: {
        type: String,
        default: "edit"
    }
})

const emits = defineEmits([
    "update:modelValue",
    "handleConfirmClick",
    "handleAddConfirmClick",
    "handleCancelClick"
])

const handleValueChange = (value: any, field: string) => {
    emits("update:modelValue", { ...props.modelValue, [field]: value })
}

const handleCancelClick = () => {
    emits("handleCancelClick")
}

const handleConfirmClick = () => {
    emits("handleConfirmClick")
}

const handleAddConfirmClick = () => {
    emits("handleAddConfirmClick")
}
</script>

<template>
    <div id="global-input-form">
        <div class="header">
            <h1>{{ title }}</h1>
        </div>
        <div class="input-form">
            <el-form label-width="120px" ref="inputFormRef">
                <el-row>
                    <template v-for="item in formItems" :key="item.label">
                        <el-col>
                            <el-form-item
                                :label="item.label"
                                :rules="item.rules"
                                :style="itemStyle"
                            >
                                <template v-if="item.type === 'input'">
                                    <el-input
                                        size="large"
                                        :placeholder="item.placeholder"
                                        :model-value="
                                            modelValue[`${item.field}`]
                                        "
                                        v-bind="item.otherOptions"
                                        @update:modelValue="
                                            handleValueChange(
                                                $event,
                                                item.field
                                            )
                                        "
                                    />
                                </template>
                                <template v-if="item.type === 'cascader'">
                                    <CascaderAddressSelector
                                        :model-value="
                                            modelValue[`${item.field}`]
                                        "
                                        @handleAddressChange="
                                            handleValueChange(
                                                $event,
                                                item.field
                                            )
                                        "
                                    />
                                </template>
                            </el-form-item>
                        </el-col>
                    </template>
                </el-row>
            </el-form>
        </div>
        <div class="footer">
            <el-button
                color="#06c"
                size="large"
                plain
                @click="handleCancelClick"
                >取消</el-button
            >
            <el-button
                v-if="submitType === 'edit'"
                color="#06c"
                size="large"
                @click="handleConfirmClick"
                >确认编辑提交</el-button
            >
            <el-button
                v-if="submitType === 'add'"
                color="#06c"
                size="large"
                @click="handleAddConfirmClick"
                >确认添加提交</el-button
            >
        </div>
    </div>
</template>

<style lang="less" scoped>
#global-input-form {
    .footer {
        display: flex;
        justify-content: center;
    }
}
</style>
