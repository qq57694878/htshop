import request from '@/router/axios';
export const getAllConfig = () => request({
    url: '/api/config/getAllConfig',
    method: 'get'
})
export const saveAllConfig = (data) => request({
    url: '/api/config/saveAllConfig',
    method: 'post',
    data: data
})