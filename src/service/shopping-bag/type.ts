export interface IShoppingBagItem {
    bagItemId: number
    productId: number
    productAmount: number
}

export interface IShoppingBagAddItem {
    productId: number
    productAmount: number
}

export interface IShoppingBagUpdateItem {
    bagItemId: number
    productAmount: number
}
