// pages/card/list.js
const app = getApp()
Page({

  /**
   * 页面的初始数据
   */
  data: {

    cardList: [{
        cardName: "一次保养卡",
        bussType: "1",
        totalFrequency: 100,
        restFrequency: 50,
        cardNo: 123456789012,
      validTime:new Date().getTime(),
      catagory:'2'
      },
      {
        cardName: "洗车20",
        bussType: "1",
        totalFrequency: 100,
        restFrequency: 50,
        cardNo: 123456789012,
        validTime: new Date().getTime(),
        catagory: '1'
      },
    ],

    no_more: true
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function(options) {

  },

  /**
   * 生命周期函数--监听页面初次渲染完成
   */
  onReady: function() {

  },

  /**
   * 生命周期函数--监听页面显示
   */
  onShow: function() {
    this.getMyCardList();
  },
  getMyCardList() {
    var that = this
    //  获取用户信息
    app._get("/mpapi/card/getMyCardList", {}).then(res => {
      if (res.code = 200) {
        console.log(res.data);
        that.setData({
          cardList: res.data
        })
      }
    })
  },

  /**
   * 生命周期函数--监听页面隐藏
   */
  onHide: function() {

  },

  /**
   * 生命周期函数--监听页面卸载
   */
  onUnload: function() {

  },

  /**
   * 页面相关事件处理函数--监听用户下拉动作
   */
  onPullDownRefresh: function() {

  },

  /**
   * 页面上拉触底事件的处理函数
   */
  onReachBottom: function() {

  },

  /**
   * 用户点击右上角分享
   */
  onShareAppMessage: function() {

  }
})