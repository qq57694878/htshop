import request from '@/router/axios';
export const getUserList = (page) => request({
    url: '/api/wxuser/getUserList',
    method: 'get',
    params: page
})

export const resetPassword = (userId) => request({
    url: '/api/wxuser/resetPassword',
    method: 'post',
    params: {userId:userId}
})
