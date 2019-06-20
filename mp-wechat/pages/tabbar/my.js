const app = getApp()

Page({

  /**
   * 订单导航跳转
   */
  onTargetOrder(e) {
    // 记录formid
    // App.saveFormId(e.detail.formId);
    let urls = {
      all: '/pages/order/list?type=all',
      unpay: '/pages/order/list?type=unpay',
      payed: '/pages/order/list?type=payed',
      cancel: '/pages/order/list?type=cancel'
    };
    // 转跳指定的页面
    wx.navigateTo({
      url: urls[e.currentTarget.dataset.type]
    })
  },
  data: {
    aboutUsTitle: '',
    aboutUsContent: '',
    servicePhoneNumber: '',
    userInfo:{},
    iconSize: 45,
    iconColor: '#999999',
    mobile:13842823735,
    carNo:"辽BK6f88"
  },
  onPullDownRefresh: function () {
    var that = this
    wx.showNavigationBarLoading()
    that.onShow()
    wx.hideNavigationBarLoading() //完成停止加载
    wx.stopPullDownRefresh() //停止下拉刷新
  },
  onLoad() {
    let that = this;
    that.setData({
      version: app.globalData.version,
      background_color: app.globalData.globalBGColor,
      bgRed: app.globalData.bgRed,
      bgGreen: app.globalData.bgGreen,
      bgBlue: app.globalData.bgBlue
    })

    
  },
  onShow() {
    var that = this;
    that.getUserApiInfo();

    that.getServicePhoneNumber();
    that.getUserInfo();

  },
  getUserInfo(){
    var that = this
    //  获取用户信息
    app._get("/mpapi/user/getUserInfo", "").then(res => {
      if (res.data.code = 200) {
        that.setData({
          userInfo: res.data
        })
      }
    })
  },
  aboutUs: function () {
    var that = this
    // wx.showModal({
    //   title: that.data.aboutUsTitle,
    //   content: that.data.aboutUsContent,
    //   showCancel: false
    // });
    wx.navigateTo({
      url: '/pages/about/index',
    })
  },
  makePhoneCall: function () {
    var that = this;
    wx.makePhoneCall({
      phoneNumber: that.data.servicePhoneNumber,
      success: function (res) { },
      fail: function (res) {
        wx.showModal({
          title: '呼叫失败',
          content: '请稍后再试',
          showCancel: false,
        })
      },
      complete: function (res) { },
    })
  },
  getPhoneNumber: function (e) {
    if (!e.detail.errMsg || e.detail.errMsg != "getPhoneNumber:ok") {
      console.log(e.detail.errMsg)
      wx.showModal({
        title: '提示',
        content: '无法获取手机号码',
        showCancel: false
      })
      return;
    }
    var that = this;
    App._post("",{}).then(res=>{
      if (res.data.code == 200) {
        wx.showToast({
          title: '绑定成功',
          icon: 'success',
          duration: 2000
        })

      } else {
        wx.showModal({
          title: '提示',
          content: '绑定失败',
          showCancel: false
        })
      }
    });

  },


 
  getServicePhoneNumber: function () {
    var that = this
    //  获取客服电话
    app._get("/mpapi/config/getServicePhoneNumber","").then(res=>{
      if(res.data.code=200){
        that.setData({
          servicePhoneNumber: res.data.servicePhoneNumber
        })
      }
    })
       
  },
 
  relogin: function () {
    wx.navigateTo({
      url: "/pages/authorize/index"
    })
    this.onLoad()
  },

})