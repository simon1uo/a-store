import type { App } from "vue"
import { ElMessage, ElMessageBox } from "element-plus"

export default function (app: App): void {
    // 全局配置
    app.config.globalProperties.$ELEMENT = {}
    app.use(ElMessage)
    app.use(ElMessageBox)
}
