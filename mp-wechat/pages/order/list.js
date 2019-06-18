var App = getApp()
Page({
  data: {
    activeTab: 0,
    dataType: 'all',
    tabs: [{ type: "all", name: "全部订单" },
    { type: "unpay", name: "待付款" },
    { type: "payed", name: "已完成" },
    { type: "cancel", name: "已取消" }],
    stv: {
      windowWidth: 0,
      lineWidth: 0,
      offset: 0,
      tStart: false
    },
    activeTab: 0,
    loadingStatus: false,
    orderList: [
      [
        {
          orderNo: 11111111111111,
          orderId: 1,
          payStatus: '0',
          orderStatus: '0',
          createTime: new Date().getTime(),
          payPrice: 300,
          skuList: [{
            mainUrl: "http://www.kulongtai.com/1.jpg",
          }, {
            mainUrl: "http://www.kulongtai.com/2.jpg",
          }, {
            mainUrl: "http://www.kulongtai.com/2.jpg",
          }, {
            mainUrl: "http://www.kulongtai.com/2.jpg",
          }, {
            mainUrl: "http://www.kulongtai.com/2.jpg",
          }, {
            mainUrl: "http://www.kulongtai.com/2.jpg",
          }, {
            mainUrl: "http://www.kulongtai.com/2.jpg",
          }],
        },
        {
          orderNo: 11111111111111,
          orderId: 1,
          payStatus: '0',
          orderStatus: '0',
          createTime: new Date().getTime(),
          payPrice: 300,
          skuList: [{
            mainUrl: "http://www.kulongtai.com/1.jpg",
          }, {
            mainUrl: "http://www.kulongtai.com/2.jpg",
          }],
        },
      ], [], [], []]
  },
  onLoad: function (options) {
    try {
      var dataType = options.type || 'all';
      var activeTab = 0;
      this.data.tabs.forEach(function (e, index) {
        if (e.type == dataType) {
          activeTab = index;
          return;// 循环被跳过
        };
      })
      this.setData({ dataType: dataType, activeTab: activeTab });

      let { tabs } = this.data;
      var res = wx.getSystemInfoSync()
      this.windowWidth = res.windowWidth;
      this.data.stv.lineWidth = this.windowWidth / this.data.tabs.length;
      this.data.stv.windowWidth = res.windowWidth;
      this.setData({ stv: this.data.stv })
    } catch (e) {
    }
  },
  onShow: function () {
    // 获取订单列表
     this.setData({
      loadingStatus: true
    })
    this.getOrderList()
  },
  /**
  * 获取订单列表
  */
  getOrderList: function () {
    let _this = this;
    var orderList=[[],[],[],[]];
    App._get('/mpapi/order/getOrderList',{}).then(result=>{
      if(result.code=200){
        var allOrderList = result.data;
        if (allOrderList&&allOrderList.length>0){
          allOrderList.forEach(item=>{
            orderList[0].push(item);
           // 0待付款; 1已取消; 8已完成
            if('0'==item.orderStatus){
              orderList[1].push(item);
            } else if ('8' == item.orderStatus) {
              orderList[2].push(item);
            } else if ('1' == item.orderStatus){
              orderList[3].push(item);
            }
          });
        }
      }
      this.setData({
        loadingStatus: false
      })
      wx.pageScrollTo({
        scrollTop: 0
      });
    });
  },
 
 

  /**
   * 跳转订单详情页
   */
  orderDetail: function (e) {
    let orderId = e.currentTarget.dataset.id;
    wx.navigateTo({
      url: '../order/detail?orderId=' + orderId
    });
  },
  /**
 * 取消订单
 */
  cancelOrder: function (e) {
    let _this = this;
    let orderId = e.currentTarget.dataset.id;
    wx.showModal({
      title: "提示",
      content: "确认取消订单？",
      success: function (o) {
        if (o.confirm) {
          App._post('/mpapi/order/cancel', { orderId }, function (result) {
            _this.getOrderList(_this.data.dataType);
          });
        }
      }
    });
  },


  /**
   * 发起付款
   */
  payOrder: function (e) {
    let _this = this;
    let orderId = e.currentTarget.dataset.id;

    // 显示loading
    wx.showLoading({ title: '正在处理...', });
    App._post_form('/mpapi/order/pay', { orderId }, function (result) {
      if (result.code === -10) {
        App.showError(result.msg);
        return false;
      }
      // 发起微信支付
      wx.requestPayment({
        timeStamp: result.data.timeStamp,
        nonceStr: result.data.nonceStr,
        package: 'prepay_id=' + result.data.prepay_id,
        signType: 'MD5',
        paySign: result.data.paySign,
        success: function (res) {
          // 跳转到已付款订单
          wx.navigateTo({
            url: '../order/detail?orderId=' + orderId
          });
        },
        fail: function () {
          App.showError('订单未支付');
        },
      });
    });
  },

 
  //更新选择标签
  _updateSelectedPage(page) {
    console.log('_updateSelectedPage')
    let { tabs, stv, activeTab } = this.data;
    activeTab = page;
    this.setData({ activeTab: activeTab })
    stv.offset = stv.windowWidth * activeTab;
    this.setData({ stv: this.data.stv })
  },
  handlerTabTap(e) {
    this._updateSelectedPage(e.currentTarget.dataset.index);
  },
  //事件处理函数
  swiperchange: function (e) {
    let { tabs, stv, activeTab } = this.data;
    activeTab = e.detail.current;
    this.setData({ activeTab: activeTab })
    stv.offset = stv.windowWidth * activeTab;
    this.setData({ stv: this.data.stv })
  },
  //去首页
  toIndexPage: function () {
    wx.switchTab({
      url: "/pages/tabbar/home"
    });
  },
  //禁止下拉刷新
  onPullDownRefresh: function () {
    wx.stopPullDownRefresh();
  }
})