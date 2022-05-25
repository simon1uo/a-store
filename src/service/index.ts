import axios from "axios"
import { useUserStore } from "../stores/user"
import { ElNotification } from "element-plus"

const api = axios.create({
    baseURL:
        import.meta.env.DEV && import.meta.env.VITE_OPEN_PROXY === "true"
            ? "/proxy/"
            : "",
    timeout: 10000,
    responseType: "json"
})

api.interceptors.request.use((request) => {
    const userStore = useUserStore()
    const token = userStore.token
    if (token) {
        request.headers = {
            ...request.headers,
            token: token,
            "Content-Type": "application/json"
        }
    }
    return request
})

api.interceptors.response.use(
    (response) => {
        if (response.status === 200) {
            if (response.data.code === 0) {
                return Promise.resolve(response.data)
            } else {
                ElNotification.error(response.data.message)
                return Promise.reject(response.data.message)
            }
        } else {
            if (response.data.error === "") {
                return Promise.resolve(response.data)
            } else {
                if (response.data.description !== "")
                    ElNotification.error(response.data.description)

                return Promise.reject(response.data.message)
            }
        }
    },
    (error) => {
        let message = error.message
        if (message == "Network Error") {
            message = "后端网络故障"
        } else if (message.includes("timeout")) {
            message = "接口请求超时"
        } else if (message.includes("Request failed with status code")) {
            message = "接口" + message.substr(message.length - 3) + "异常"
        }
        ElNotification.error(message)
        return Promise.reject(error)
    }
)

export default api
