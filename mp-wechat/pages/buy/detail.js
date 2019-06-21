let App = getApp(),
    wxParse = require("../../wxParse/wxParse.js");

Page({

    /**
     * 页面的初始数据
     */
    data: {
         skuId:"",
        floorstatus: false, // 返回顶部
          detail: {}
    },

    /**
     * 返回顶部
     */
    goTop(t) {
        this.setData({
            scrollTop: 0
        });
    },


    /**
     * 生命周期函数--监听页面加载
     */
    onLoad(options) {
        let _this = this;
        this.data.skuId = options.id;
        console.log(" this.data.skuId "+ this.data.skuId );
    },
        /**
         * 生命周期函数--监听页面显示
         */
        onShow: function() {
            this.getSkuDetail();
        },
    /**
     * 获取商品信息
     */
    getSkuDetail() {
        let _this = this;
        App._get('/mpapi/sku/getSku', {
            id: _this.data.skuId
        }).then(function (result) {
          console.log(result);
            _this.setData({detail:result.data});
            // 富文本转码
            if (_this.data.detail.skuContent.length > 0) {
                wxParse.wxParse('content', 'html', _this.data.detail.skuContent, _this, 0);
            }
        });
    },


    /**
     * 显示/隐藏 返回顶部按钮
     */
    scroll(e) {
        this.setData({
            floorstatus: e.detail.scrollTop > 200
        })
    },



    /**
     * 分享当前页面
     */
    onShareAppMessage: function () {
        // 构建页面参数
        let _this = this;
        return {
            title: _this.data.detail.skuName,
            path: "/pages/buy/detail?skuId=" + _this.data.skuId
        };
    },

})