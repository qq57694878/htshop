import request from '@/router/axios';
export const getCardRecordList = (page) => request({
    url: '/api/card-record/getCardRecordList',
    method: 'get',
    params: page
})
