// pages/card/list.js
const app = getApp()
Page({

    /**
     * 页面的初始数据
     */
    data: {
        skuList: [
        ],
        current:1,
        size:10,
        pages:1,
        no_more: false
    },

    /**
     * 生命周期函数--监听页面加载
     */
    onLoad: function(options) {
      this.setData({ scrollHeight: wx.getSystemInfoSync().windowHeight });
      this.getSkuList(true);
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
  
    },
  getSkuList(isreload) {
        var that = this
        //  获取用户信息 查询有效的
        wx.showLoading({
            title: '加载中',
        });
        app._get("/mpapi/sku/getSkuList", {current:this.data.current,size:this.data.size}).then(res => {
            wx.hideLoading();
            if (res.code = 200) {
              var skuList=this.data.skuList;
              if (isreload) {
                skuList = [];
              } 
              var list = res.data.records;
              if(list){
                  for(var i=0;i<list.length;i++){
                    var o = list[i];
                    o.validTime = this.addMonth(new Date(),o.validMonth).getTime();
               
                    skuList.push(o);
                  }
              }
             
                that.setData({
                    pages:res.data.pages,
                    skuList: skuList
                })
            }
        }).catch(res=>{
            wx.hideLoading();
        });
    },




addMonth (date, num){
    num = parseInt(num);
    var sDate = date;

    var sYear = sDate.getFullYear();
    var sMonth = sDate.getMonth() + 1;
    var sDay = sDate.getDate();

    var eYear = sYear;
    var eMonth = sMonth + num;
    var eDay = sDay;
    while (eMonth > 12) {
        eYear++;
        eMonth -= 12;
    }

    var eDate = new Date(eYear, eMonth - 1, eDay);

    while (eDate.getMonth() != eMonth - 1) {
        eDay--;
        eDate = new Date(eYear, eMonth - 1, eDay);
    }

    return eDate;
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
     * 下拉到底加载数据
     */
    bindDownLoad: function () {
        // 已经是最后一页
        if (this.data.page >= this.data.pages) {
            this.setData({ no_more: true });
            return false;
        }
        this.data.current++;
        this.getSkuList(false );
    },
    buyNow:function(){

        var that = this
        //  获取用户信息 查询有效的
        wx.showLoading({
            title: '加载中',
        });
        app._get("/mpapi/order/orderAndPay", {current:this.data.current,size:this.data.size}).then(res => {
            wx.hideLoading();
            if (res.code = 200) {
                //调起微信支付
                // 发起微信支付
                wx.requestPayment({
                    timeStamp: result.data.timeStamp,
                    nonceStr: result.data.nonceStr,
                    package: 'prepay_id=' + result.data.prepay_id,
                    signType: 'MD5',
                    paySign: result.data.paySign,
                    success: function (res) {
                        // 跳转到已付款订单
                      app.showSuccess('支付成功')
                    },
                    fail: function () {
                        app.showError('订单未支付成功');
                    },
                });
            }
        }).catch(res=>{
            wx.hideLoading();
        });
    },
    goSkuDetail(e){
        let id = e.currentTarget.dataset.id;
        wx.navigateTo({
            url: '/pages/buy/detail?id='+id
        });
    }
})