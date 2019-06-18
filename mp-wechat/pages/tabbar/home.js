let App = getApp();

Page({
  data: {
    notice:"终于上线了！",//通知公告
    // banner轮播组件属性
    indicatorDots: true, // 是否显示面板指示点	
    autoplay: true, // 是否自动切换
    interval: 3000, // 自动切换时间间隔
    duration: 800, // 滑动动画时长
    bannerList:[
      {
        linkUrl: 'sku/detail?skuId=111',
        imgUrl:"http://www.kulongtai.com/1.jpg"
    },
      {
        linkUrl: 'sku/detail?skuId=111',
        imgUrl: "http://www.kulongtai.com/2.jpg"
      }
      
    ],
    tjList:[
      {
        skuName:"温馨一次小保养",
        skuPrice:"350",
        mainUrl: "http://www.kulongtai.com/1.jpg"
      },
      {
        skuName: "温馨一次小保养",
        skuPrice: "350",
        mainUrl: "http://www.kulongtai.com/3.jpg"
      },
       {
        skuName: "温馨一次小保养",
        skuPrice: "350",
        mainUrl: "http://www.kulongtai.com/2.jpg"
      },
      {
        skuName: "温馨一次小保养",
        skuPrice: "350",
        mainUrl: "http://www.kulongtai.com/2.jpg"
      },
      {
        skuName: "温馨一次小保养",
        skuPrice: "350",
        mainUrl: "http://www.kulongtai.com/2.jpg"
      },
      {
        skuName: "温馨一次小保养",
        skuPrice: "350",
        mainUrl: "http://www.kulongtai.com/2.jpg"
      }
    ],
    skuList: [
      {
        skuName: "温馨一次小保养",
        skuPrice: "350",
        mainUrl: "http://www.kulongtai.com/1.jpg"
      },
      {
        skuName: "温馨一次小保养",
        skuPrice: "350",
        mainUrl: "http://www.kulongtai.com/3.jpg"
      },
      {
        skuName: "温馨一次小保养",
        skuPrice: "350",
        mainUrl: "http://www.kulongtai.com/2.jpg"
      },
      {
        skuName: "温馨一次小保养",
        skuPrice: "350",
        mainUrl: "http://www.kulongtai.com/2.jpg"
      },
      {
        skuName: "温馨一次小保养",
        skuPrice: "350",
        mainUrl: "http://www.kulongtai.com/2.jpg"
      },
      {
        skuName: "温馨一次小保养",
        skuPrice: "350",
        mainUrl: "http://www.kulongtai.com/2.jpg"
      }
    ],

    // 页面元素
    items: {},
    newest: {},
    best: {},

    scrollTop: 0,
  },

  onLoad: function () {
    //获取通知
    this.getNotice();
    // 获取首页数据
    this.getIndexData();
  },


  /**
   * 获取通知公告
   */
  getNotice: function () {
    let _this = this;
 
    App._get('/mpapi/notice/getNotice', {}, function (result) {
      if (result.data){
        _this.setData({"notice":result.data.content});
      }
    });
  },
  /**
   * 获取首页数据
   */
  getIndexData: function () {
    let _this = this;
    App._get('index/page', {}, function (result) {
      _this.setData(result.data);
    });
  },


  goTop: function (t) {
    this.setData({
      scrollTop: 0
    });
  },

  scroll: function (t) {
    this.setData({
      indexSearch: t.detail.scrollTop
    }), t.detail.scrollTop > 300 ? this.setData({
      floorstatus: !0
    }) : this.setData({
      floorstatus: !1
    });
  },

  onShareAppMessage: function () {
    return {
      title: "小程序首页",
      desc: "",
      path: "/pages/tabar/home"
    };
  }
});