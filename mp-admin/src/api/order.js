import request from '@/router/axios';
export const getOrderList = (page) => request({
    url: '/api/order/getOrderList',
    method: 'get',
    params: page
})
export const getOrderDetail = (id) => request({
    url: '/api/order/getOrderDetail',
    method: 'get',
    params: {id:id}
})
