// pages/login/register.js
Page({

    /**
     * 页面的初始数据
     */
    data: {
        newPassword: '',
        password: '',
        keyBoardType: "1",
        repassword: "",
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
    onNewPasswordInput(val) {
        this.setData({newPassword: val});
    },
    onPasswordInput(val) {
        this.setData({password: val});
    },
    onRepasswordInput(val) {
        this.setData({repassword: val});
    },
    /**
     * 修改密码
     */
    modifyPassword() {
        if(this.data.newPassword.length<6){
            App.showInfo("新密码最少6位");
            return;
        }
        if (this.data.password != this.data.repassword) {
            App.showInfo("两次输入的密码不一致");
            return;
        }
        App._post('/mpapi/modifyPasswrod', {
            password: this.data.password,
            newPassword: this.data.newPassword
        }).then((response) => {
            if (response.code == 200) {
                App.showSuccess("密码修改成功", function () {
                    //去首页
                    wx.navigateTo({
                        url: '/pages/tabbar/my'
                    })
                });

            } else {
                App.showError(response.msg);
            }
        });
    },
    back:function(){
        wx.navigateBack({
            delta: 1
        })
    }

})