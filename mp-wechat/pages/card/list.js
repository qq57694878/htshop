// pages/card/list.js
const app = getApp()
Page({

  /**
   * 页面的初始数据
   */
  data: {
      showDialog:false,
    cardList: [
      /*  {
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
      },*/
    ],
      cardRecordList:[
          {
              afterUsedFrequency:19,
              usedFrequency:1,
              cardNo:"789456123012",
              createTime: new Date().getTime(),
          },
          {
              afterUsedFrequency:18,
              usedFrequency:1,
              cardNo:"789456123012",
              createTime: new Date().getTime(),
          }
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
    //  获取用户信息 查询有效的
      wx.showLoading({
          title: '加载中',
      });
    app._get("/mpapi/card/getMyCardList", {validFlag:"1"}).then(res => {
        wx.hideLoading();
      if (res.code = 200) {
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

  },
    /**
     * 显示消费记录
     */
    showUsedRecord: function(e){
        let cardId = e.currentTarget.dataset.id;
        wx.showLoading({
            title: '加载中',
        })
        app._get("/mpapi/card-record/getCardRecordList",{cardId:cardId}).then(res=>{
            wx.hideLoading();
          var list = res.data;
          if(list==null){
            list=[];
          }
            this.setData({showDialog:true,cardRecordList:list});
      }).catch(res=>{
            wx.hideLoading();
        });

    },
    closeUsedRecord: function(){
        this.setData({showDialog:false});
    }
})