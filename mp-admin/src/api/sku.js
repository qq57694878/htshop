import request from '@/router/axios';
export const getSkuList = (page) => request({
    url: '/api/sku/getSkuList',
    method: 'get',
    params: page
})
export const getSku = (id) => request({
    url: '/api/sku/getSku',
    method: 'get',
    params: {id:id}
})
export const deleteSku = (id) => request({
    url: '/api/sku/deleteSku',
    method: 'delete',
    params: {id:id}
})
export const updateSku = (sku) => request({
    url: '/api/sku/updateSku',
    method: 'put',
    data: sku
})
export const insertSku = (sku) => request({
    url: '/api/sku/insertSku',
    method: 'post',
    data: sku
})