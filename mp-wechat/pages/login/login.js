let App = getApp();
Page({

    /**
     * 页面的初始数据
     */
    data: {
        mobile: "",
        password: "",
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

    },
    /**
     * 登录
     */
    loginByPassword() {
        App._post('/mpapi/loginByPassword', {mobile: this.data.mobile, password: this.data.password}).then((res) => {
            if (this.code == 200) {
                wx.setStorageSync('token', this.data);
                //去首页
                wx.navigateTo({
                    url: '/pages/tabbar/my'
                })
            } else {
                App.showError(this.msg);
            }
        });
    },

    goReg() {
        wx.navigateTo({
            url: './register',
        })
    },
    onMobileInput(val){
        this.setData({mobile:val});
    },
    onPasswordInput(val){
        this.setData({password:val});
    }
})