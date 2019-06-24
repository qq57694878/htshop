// pages/card/list.js
const app = getApp()
Page({

    /**
     * 页面的初始数据
     */
    data: {
        showDialog:false,
        cardList: [{
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
            },
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
        current:1,
        size:10,
        pages:1,
        no_more: false
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
    getMyCardRecordList() {
        var that = this
        wx.showLoading({
            title: '加载中',
        })
        //  获取用户信息 查询无效的
        app._get("/mpapi/card-record/getMyCardRecordList", {current:this.data.current,size:this.data.size,validFlag:"0"}).then(res => {
            wx.hideLoading();
            if (res.code = 200) {
                console.log(res.data);
                that.setData({
                    pages:res.data.pages,
                    cardList: res.data
                })
            }
        }).catch(res=>{
            wx.hideLoading();
        });
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