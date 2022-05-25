export function setCache(key: string, value: any) {
    localStorage.setItem(key, JSON.stringify(value))
}

export function getCache(key: string) {
    const value = localStorage.getItem(key)
    if (value) {
        return JSON.parse(value)
    }
}

export function removeCache(key: string) {
    localStorage.removeItem(key)
}

export function clearCache() {
    localStorage.clear()
}
