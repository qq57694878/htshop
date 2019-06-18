// pages/card/list.js
Page({

  /**
   * 页面的初始数据
   */
  data: {
    page: {
      records: [
        {
          cardName: "一次保养卡",
          bussType: "1",
          usedFrequency: 100,
          afterUsedFrequency: 50,
          cardNo: 123456789012,
          createTime:new Date().getTime()
        },
        {
          cardName: "E卡200",
          bussType: "2",
          afterUsedPrice: 100,
          usedPrice: 200,
          cardNo: 123456789034,
          createTime: new Date().getTime()
        },
      ],
      total: 100,
      current: 1,
      size: 10
    },
    no_more: true
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