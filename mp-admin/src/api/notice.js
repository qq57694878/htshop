import request from '@/router/axios';
export const getNotice = () => request({
    url: '/api/notice/getNotice',
    method: 'get'
})
export const saveNotice = (notice) => request({
    url: '/api/notice/saveNotice',
    method: 'post',
    data: notice
})