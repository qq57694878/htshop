const app = getApp()

Page({


  data: {
    cardList:[],
    aboutUsTitle: '',
    aboutUsContent: '',
    servicePhoneNumber: '',
    userInfo:{
        mobile:13842823735,
        carNo:"辽BK6F88"
    },
    iconSize: 45,
    iconColor: '#999999',

  },
    /**
     * 订单导航跳转
     */
    onTargetOrder(e) {
        let urls = {
            card_list: '/pages/card/list',
            used_card: '/pages/card-record/list',
            buy: 'pages/sku/list'
        };
        // 转跳指定的页面
        wx.navigateTo({
            url: urls[e.currentTarget.dataset.type]
        })
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
    /**
     *  登出
     */
    logout(){
            App._get('/mpapi/logout',{}).then((res) => {
                if (this.code == 200) {
                    wx.setStorageSync('token', this.data);
                    //刷新页面
                    this.onShow();
                } else {
                    App.showError(this.msg);
                }
            });
    }

})