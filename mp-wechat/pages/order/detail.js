let App = getApp();

Page({

  /**
   * 页面的初始数据
   */
  data: {
    orderId: null,
    order: {
      orderNo: 11111111111111,
      orderId: 1,
      payStatus: '0',
      orderStatus: '0',
      createTime: new Date().getTime(),
      payPrice: 300,
      totalPrice:300,
      skuList: [{
        buyNum:1,
        skuPrice:100.00,
        skuName:"保养套餐",
        mainUrl: "http://www.kulongtai.com/1.jpg",
      }, {
          buyNum: 1,
          skuPrice: 100,
          skuName: "洗车",
        mainUrl: "http://www.kulongtai.com/2.jpg",
      }, {
          buyNum: 2,
          skuPrice: 100,
          skuName: "四轮定位",
        mainUrl: "http://www.kulongtai.com/2.jpg",
      }, {
          buyNum: 2,
          skuPrice: 100,
          skuName: "机油机滤",
        mainUrl: "http://www.kulongtai.com/2.jpg",
      }, {
          buyNum: 2,
          skuPrice: 100,
          skuName: "爱车养护",
        mainUrl: "http://www.kulongtai.com/2.jpg",
      }, {
        mainUrl: "http://www.kulongtai.com/2.jpg",
      }, {
          buyNum: 2,
          skuPrice: 100,
          skuName: "洗车",
        mainUrl: "http://www.kulongtai.com/2.jpg",
      }],
    },
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    this.data.orderId = options.orderId;
    this.getOrderDetail(options.orderId);
  },

  /**
   * 获取订单详情
   */
  getOrderDetail: function (id) {
    let _this = this;
    App._get('/mpapi/order/getOrderDetail', { id }).then(result=>{
      _this.setData({order:result.data});
    });
  },

  /**
   * 跳转到商品详情
   */
  skuDetail: function (e) {
    let skuId = e.currentTarget.dataset.id;
    wx.navigateTo({
      url: '../sku/detail?skuId=' + skuId
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
            wx.navigateBack();
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
    let orderId = _this.data.orderId;
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
          // 重新获取订单
          _this.getOrderDetail(order_id);
        },
        fail: function () {
          App.showError('订单未支付');
        },
      });
    });
  },




});