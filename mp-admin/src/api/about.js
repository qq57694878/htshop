import request from '@/router/axios';
export const getAbout = () => request({
    url: '/api/about/getAbout',
    method: 'get'
})
export const saveAbout = (data) => request({
    url: '/api/about/saveAbout',
    method: 'post',
    data: data
})