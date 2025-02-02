import request from '@/router/axios';
export const loginByUsername = (username, password, code, redomStr) => request({
    url: '/api/login',
    method: 'post',
    data: {
        username,
        password,
        code,
        redomStr
    }
})

export const getUserInfo = () => request({
    url: '/user/getUserInfo',
    method: 'get'
});

export const RefeshToken = () => request({
    url: '/api/refeshToken',
    method: 'post'
})

export const modifyPassword = (form) => request({
    url: '/api/modifyPassword',
    method: 'post',
    data: form
})

export const getMenu = (type = 0) => request({
    url: '/user/getMenu',
    method: 'get',
    data: {
        type
    }
});

export const getMenuAll = () => request({
    url: '/user/getMenu',
    method: 'get',
    data: {
        type: 0
    }
});

export const getTableData = (page) => request({
    url: '/user/getTable',
    method: 'get',
    data: {
        page
    }
});

export const logout = () => request({
    url: '/user/logout',
    method: 'get'
})