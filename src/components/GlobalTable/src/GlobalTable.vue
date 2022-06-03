<script lang="ts" setup>
const props = defineProps({
    tableData: {
        required: true
    },
    propList: {
        required: true
    }
})
</script>

<template>
    <div>
        <el-table :data="tableData" style="width: 100%">
            <template v-for="propItem in propList" :key="propItem.prop">
                <el-table-column
                    v-bind="propItem"
                    align="center"
                    show-overflow-tooltip
                >
                    <template #default="scope">
                        <slot :name="propItem.slotName" :row="scope.row">
                            {{ scope.row[propItem.prop] }}
                        </slot>
                    </template>
                </el-table-column>
            </template>
            <!--            &lt;!&ndash;            <el-table-column prop="address" label="地址" />-->
            <!--            <el-table-column label="操作" width="150">-->
            <!--                <template #default="scope">-->
            <!--                    <el-button type="success" size="small">编辑</el-button>-->
            <!--                    <el-popconfirm-->
            <!--                        title="是否确认删除?"-->
            <!--                        @confirm="handleDeleteClick(scope.row.id)"-->
            <!--                    >-->
            <!--                        <template #reference>-->
            <!--                            <el-button type="warning" size="small"-->
            <!--                                >删除</el-button-->
            <!--                            >-->
            <!--                        </template>-->
            <!--                    </el-popconfirm>-->
            <!--                </template>-->
            <!--            </el-table-column>&ndash;&gt;-->
        </el-table>
    </div>
</template>

<style lang="less" scoped>
.el-table {
    margin-bottom: 10px;
}
</style>
