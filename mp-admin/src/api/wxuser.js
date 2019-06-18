import request from '@/router/axios';
export const getUserList = (page) => request({
    url: '/api/wxuser/getUserList',
    method: 'get',
    params: page
})