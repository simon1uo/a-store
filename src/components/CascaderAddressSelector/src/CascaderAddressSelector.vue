<template>
    <el-cascader
        class="long"
        placeholder="选择地址"
        size="large"
        :options="regionData"
        :model-value="selectedOptions"
        @change="handleChange"
    />
</template>

<script>
import { computed, defineComponent, reactive, ref, watch } from "vue"
import { CodeToText, regionData, TextToCode } from "element-china-area-data"

export default defineComponent({
    name: "CascaderAddressSelector",
    props: {
        modelValue: {
            required: true,
            default: () => [],
            type: Array
        }
    },
    emits: ["handleAddressChange"],
    setup(props, { emit }) {
        const handleTextToCode = (arr) => {
            let code = []
            if (arr[0] && TextToCode[`${arr[0]}`]) {
                const province = TextToCode[`${arr[0]}`]
                code.push(province.code)
                if (arr[1] && province[`${arr[1]}`]) {
                    const city = province[`${arr[1]}`]
                    code.push(city.code)
                    if (arr[2] && city[`${arr[2]}`]) {
                        const region = city[`${arr[2]}`]
                        code.push(region.code)
                    }
                }
            }
            return code
        }

        const handleCodeToText = (arr) => {
            let text = []
            if (arr[0] && CodeToText[`${arr[0]}`]) {
                const province = CodeToText[`${arr[0]}`]
                text.push(province)
                if (arr[1] && CodeToText[`${arr[1]}`]) {
                    const city = CodeToText[`${arr[1]}`]
                    text.push(city)
                    if (arr[2] && CodeToText[`${arr[2]}`]) {
                        const region = CodeToText[`${arr[2]}`]
                        text.push(region)
                    }
                }
            }
            return text
        }

        const addressValue = computed(() => handleTextToCode(props.modelValue))

        const selectedOptions = computed(() => addressValue.value)

        const handleChange = (value) => {
            emit("handleAddressChange", handleCodeToText(value))
        }

        return {
            selectedOptions,
            regionData,
            handleChange
        }
    }
})
</script>

<style lang="less" scoped>
.long {
    width: 300px;
}
</style>
