// pages/card/list.js
Page({

  /**
   * 页面的初始数据
   */
  data: {
    page:{
      records:[
        {
          cardName:"一次保养卡",
          bussType:"1",
          totalFrequency:100,
          restFrequency:50,
          cardNo:123456789012,
        },
        {
          cardName: "E卡$200",
          bussType: "2",
          balancePrice: 100,
          facePrice: 200,
          cardNo: 123456789034,
        },
      ],
      total:100,
      current:1,
      size:10
    },
    no_more:true
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {

  },

  /**
   * 生命周期函数--监听页面初次渲染完成
   */
  onReady: function () {

  },

  /**
   * 生命周期函数--监听页面显示
   */
  onShow: function () {

  },

  /**
   * 生命周期函数--监听页面隐藏
   */
  onHide: function () {

  },

  /**
   * 生命周期函数--监听页面卸载
   */
  onUnload: function () {

  },

  /**
   * 页面相关事件处理函数--监听用户下拉动作
   */
  onPullDownRefresh: function () {

  },

  /**
   * 页面上拉触底事件的处理函数
   */
  onReachBottom: function () {

  },

  /**
   * 用户点击右上角分享
   */
  onShareAppMessage: function () {

  }
})