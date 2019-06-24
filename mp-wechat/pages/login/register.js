// pages/login/register.js
Page({

    /**
     * 页面的初始数据
     */
    data: {
        mobile: '',
        password: '',
        carNo: '',
        isShow: false,
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
    inputOk() {
        this.setData({isShow: false});
    },
    inputdelete() {
        this.setData({carNo: ''});
    },
    inputChange(e) {

        if (e.detail) {
            this.setData({carNo: this.data.carNo + e.detail});
        }
        let keyBoardType = 1
        if (this.data.carNo == '') {
            keyBoardType = 1
        } else {
            keyBoardType = 2
        }
        this.setData({keyBoardType: keyBoardType});
    },
    onCarnoFocus() {
        let keyBoardType = 1
        if (this.data.carNo == '') {
            keyBoardType = 1
        } else {
            keyBoardType = 2
        }
        this.setData({isShow: true, keyBoardType: keyBoardType});
    },
    onMobileInput(val) {
        this.setData({mobile: val});
    },
    onPasswordInput(val) {
        this.setData({password: val});
    },
    onRepasswordInput(val) {
        this.setData({repassword: val});
    },
    /**
     * 注册
     */
    reg() {
        if(this.data.password.length<6){
            App.showInfo("密码最少6位");
            return;
        }
        if (this.data.password != this.data.repassword) {
            App.showInfo("两次输入的密码不一致");
            return;
        }
        if (!App.checkPhone(this.data.mobile)) {
            App.showInfo("电话号码格式不正确");
            return;
        }
        if (!App.checkCarNo(this.data.carNo)) {
            App.showInfo("车牌号格式不正确");
            return;
        }

        // 登录
        wx.login({
            success: res => {
                App._post('/mpapi/reg', {
                    mobile: this.data.mobile,
                    password: this.data.password,
                    carNo: this.data.carNo
                }).then((response) => {
                    if (response.code == 200) {
                        wx.setStorageSync('token', response.data);
                        App.showSuccess("注册成功", function () {
                            //去首页
                            wx.navigateTo({
                                url: '/pages/tabbar/my'
                            })
                        });

                    } else {
                        App.showError(response.msg);
                    }
                });
            }
        })


    },


})