import type { App } from "vue"
import { ElMessage, ElMessageBox } from "element-plus"
import "element-plus/theme-chalk/el-message.css"
import "element-plus/theme-chalk/el-message-box.css"

export default function (app: App): void {
    // 全局配置
    app.config.globalProperties.$ELEMENT = {}
    app.use(ElMessage)
    app.use(ElMessageBox)
}
