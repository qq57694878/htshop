// pages/card/list.js
Page({

    /**
     * 页面的初始数据
     */
    data: {
       /* page: {
            records: [
                {
                    cardName: "洗车卡",
                    catagory: "1",
                    usedFrequency: 100,
                    afterUsedFrequency: 50,
                    cardNo: 123456789012,
                    createTime: new Date().getTime()
                },
                {
                    cardName: "一次保养卡",
                    catagory: "2",
                    usedFrequency: 100,
                    afterUsedFrequency: 50,
                    afterUsedPrice: 100,
                    usedPrice: 200,
                    cardNo: 123456789034,
                    createTime: new Date().getTime()
                },
            ],
            total: 100,
            current: 1,
            size: 10
        },*/
        cardRecordList:[],
        current:1,
        size:10,
        pages:1,
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
    onShow: function() {
        this.getMyCardRecordList();
    },
    getMyCardRecordList() {
        var that = this
        //  获取用户信息 查询有效的
        wx.showLoading({
            title: '加载中',
        });
        app._get("/mpapi/card-record/getMyCardRecordList", {current:this.data.current,size:this.data.size,validFlag:"1"}).then(res => {
            wx.hideLoading();
            if (res.code = 200) {
                that.setData({
                    pages:res.data.pages,
                    cardRecordList: res.data.records
                })
            }
        })
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
        this.getMyCardRecordList( );
    },
})