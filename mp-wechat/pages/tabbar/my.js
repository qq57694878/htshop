const app = getApp()

Page({


  data: {
    cardList:[],
    aboutUsTitle: '',
    aboutUsContent: '',
    servicePhoneNumber: '',
    /*userInfo:{
        mobile:13842823735,
        carNo:"辽BK6F88"
    },*/
    userInfo:{},
    iconSize: 45,
    iconColor: '#999999',
    isVip:false

  },
    /**
     * 订单导航跳转
     */
    onTargetOrder(e) {
        let urls = {
            card_list: '/pages/card/list',
            used_card: '/pages/card-record/list',
            buy: '/pages/buy/list'
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
    this.getMyStat();

  },
  getUserInfo(){
    var that = this
    //  获取用户信息
    app._get("/mpapi/user/getUserInfo", "").then(res => {
      var userInfo={};
      var isVip= false;
      if (res.code = 200) {
        if(res.data&&res.data.userId){
            userInfo=res.data;
            isVip =true;
        }
      }
        that.setData({
            userInfo: userInfo,
            isVip: isVip
        })
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


  getMyStat: function () {
    var that = this
    //  获取客服电话
    app._get("/mpapi/stat/mystat", {  }).then(res => {
      if (res.code = 200) {
        that.setData({
          cardCount: res.data.cardCount > 99 ? "99+" : res.data.cardCount
        })
      }
    })
  },
 
  getServicePhoneNumber: function () {
    var that = this
    //  获取客服电话
    app._get("/mpapi/config/getConfig", {"k":"server_phone_number"}).then(res=>{
      if(res.code=200){
        that.setData({
          servicePhoneNumber: res.data
        })
      }
    })
  },
    /**
     *  登出
     */
    logout(){
            app._post('/mpapi/logout',{}).then((response) => {
                if (response.code == 200) {
                    wx.setStorageSync('token', response.data);
                    //刷新页面
                    this.onShow();
                } else {
                    app.showError(response.msg);
                }
            });
    },
    /**
     * 修改密码
     */
    modifyPassword(){
        wx.navigateTo({
            url: '/pages/login/modifyPassword'
        });
    },

})