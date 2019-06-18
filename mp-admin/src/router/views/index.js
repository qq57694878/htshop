import Layout from '@/page/index/'
export
default [{
    path: '/wel',
    component: Layout,
    redirect: '/wel/index',
    children: [{
        path: 'index',
        name: '首页',
        component: () =>
        import( /* webpackChunkName: "views" */ '@/page/wel')
}]
}, {
    path: '/info',
        component: Layout,
        redirect: '/info/index',
        children: [{
        path: 'index',
        name: '个人信息',
        component: () =>
        import( /* webpackChunkName: "views" */ '@/views/info/index')
}, {
        path: 'message',
            name: '消息中心',
            component: () =>
    import( /* webpackChunkName: "views" */ '@/views/info/message')
    }]
}, {
    path: '/advanced-router',
        component: Layout,
        children: [{
        path: 'mutative-detail/:id',
        name: '动态路由详情页',
        component: () =>
        import( /* webpackChunkName: "utils" */ '@/views/advanced-router/mutative-detail')
}, {
        path: 'argument-detail',
            name: '参数路由详情页',
            component: () =>
    import( /* webpackChunkName: "utils" */ '@/views/advanced-router/argument-detail')
    }]
}, {
    path: '/order',
        component: Layout,
        children: [{
        path: "detail/:orderId",
        name: '订单详情',
        component: () =>
        import( /* webpackChunkName: "page" */ '@/views/order/detail'),
    props: true
}]

}, {
    path: '/sku',
        component: Layout,
        children: [{
        path: 'add',
        name: '新增商品',
        component: () => import( /* webpackChunkName: "utils" */ '@/views/sku/add')
}, {
        path: 'edit/:skuId',
            name: '编辑商品',
            component: () => import( /* webpackChunkName: "utils" */ '@/views/sku/edit')
    }]
}]