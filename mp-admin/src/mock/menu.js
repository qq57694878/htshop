import Mock from 'mockjs'

/**
 * 
 * 动态模拟菜单
 * 
 * label菜单的名字
 * path菜单的路径
 * icon菜单的图标（系统采用的阿里巴巴图表库）
 * compnent组件的地址
 * children子类菜单数组
 * group配置其他路由激活菜单高亮
 */
const first = [
    {
        id: 66,
        label: "商品管理",
        path: '/sku',
        component: 'views/sku/index',
        icon: 'icon-shangpin1',
        children: [],
    },

    {
        id: 68,
        label: "卡券管理",
        path: '/card',
        component: 'views/card/index',
        icon: 'icon-wodeyouhuiquan',
        children: [],
    },
    {
        id: 69,
        label: "用户管理",
        path: '/wxuser',
        component: 'views/wxuser/index',
        icon: 'icon-yonghu',
        children: [],
    },

    {
        id: 71,
        label: "小程序管理",
        path: '/wxmp',
        component: 'views/param-config/index',
        icon: 'icon-xiaochengxu',
        children: [
           /* {
                id: 70,
                label: "小程序配置",
                path: '/param-config',
                component: 'views/param-config/index',
                icon: 'icon-xiaochengxu',
                children: [],
            },*/
           /* {
                id: 71,
                label: "首页设计",
                path: '/app-home',
                component: 'views/page-home/index',
                icon: 'icon-shouye',
                children: [],
            },*/
        ],
    },
    {
        id: 80,
        label: "通知公告管理",
        path: '/notice',
        component: 'views/notice/index',
        icon: 'icon-notice',
        children: [],
    },
    {
        id: 81,
        label: "关于我们",
        path: '/about',
        component: 'views/about/index',
        icon: 'icon-about',
        children: [],
    }
  ]

export default ({ mock }) => {
    if (!mock) return;
    Mock.mock('/user/getMenu', 'get', (res) => {
        return {
            data: first
        }
    })
}