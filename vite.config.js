import { fileURLToPath, URL } from "url"

import { defineConfig, loadEnv } from "vite"
import vue from "@vitejs/plugin-vue"

import AutoImport from "unplugin-auto-import/vite"
import Components from "unplugin-vue-components/vite"
import { ElementPlusResolver } from "unplugin-vue-components/resolvers"
// https://vitejs.dev/config/
export default ({ mode }) => {
    // eslint-disable-next-line no-undef
    const env = loadEnv(mode, process.cwd())

    return defineConfig({
        base: "./",
        // 开发服务器选项 https://cn.vitejs.dev/config/#server-options
        server: {
            open: true,
            port: 9000,
            proxy: {
                "/proxy": {
                    target: env.VITE_APP_API_BASEURL,
                    changeOrigin: true,
                    rewrite: (path) => path.replace(/\/proxy/, "")
                }
            }
        },
        plugins: [
            vue(),
            AutoImport({
                resolvers: [ElementPlusResolver()]
            }),
            Components({
                resolvers: [ElementPlusResolver()]
            })
        ],
        resolve: {
            alias: {
                "@": fileURLToPath(new URL("./src", import.meta.url))
            }
        }
    })
}
