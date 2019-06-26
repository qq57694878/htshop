/**
 * 全局配置文件
 */
export default {
    title: "微信小程序管理后台",
    logo: "A",
    indexTitle: '微信小程序管理后台',
    lockPage: '/lock',
    tokenTime: 6000,
    info: {
        title: "微信小程序管理后台",
        list: [
            '小程序商品和订单管理，小程序运营人员管理',
        ]
    },
    //http的status默认放行不才用统一处理的,
    statusWhiteList: [400],
    //配置首页不可关闭
    isFirstPage: false,
    fistPage: {
        label: "首页",
        value: "/wel/index",
        params: {},
        query: {},
        group: [],
        close: false
    },
    //配置菜单的属性
    menu: {
        props: {
            label: 'label',
            path: 'path',
            icon: 'icon',
            children: 'children'
        }
    },
    //uploadImageUrl:'http://localhost:9999/api/upload',
    //uploadKindUrl:'http://localhost:9999/api/public/upload4kind'
    uploadImageUrl:'https://admin.ganjinnuo.com/api/upload',
    uploadKindUrl:'https://admin.ganjinnuo.com//api/public/upload4kind'
}