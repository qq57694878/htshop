import request from '@/router/axios';
export const getHomePage = () => request({
    url: '/api/notice/getHomePage',
    method: 'get'
})
export const saveHomePage = (data) => request({
    url: '/api/notice/saveHomePage',
    method: 'post',
    data: data
})