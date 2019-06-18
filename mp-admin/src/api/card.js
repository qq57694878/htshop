import request from '@/router/axios';
export const getCardList = (page) => request({
    url: '/api/card/getCardList',
    method: 'get',
    params: page
})
export const consumeFrequencyCard = (data) => request({
    url: '/api/card/consumeFrequencyCard',
    method: 'post',
    data: data
})
export const consumeECard = (data) => request({
    url: '/api/card/consumeECard',
    method: 'post',
    data: data
})
export const getCard = (cardNo) => request({
    url: '/api/card/getCard',
    method: 'get',
    params: {cardNo:cardNo}
})