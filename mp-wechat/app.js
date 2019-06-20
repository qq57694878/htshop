//app.js
import properties from "./utils/properties.js"
import aa from "./mock/index.js"
import {_get,_post,_delete,_put} from "./utils/request.js"
import {checkPhone,checkCarNo} from "./utils/check.js"
App({
    checkPhone:checkPhone,
    checkCarNo:checkCarNo,
  onLaunch: function () {
    // 展示本地存储能力
    this.updateProject();

    // 登录
    wx.login({
      success: res => {
        // 发送 res.code 到后台换取 openId, sessionKey, unionId
        _get("/mpapi/login",null).then(function(res){
           if(res.code==200){
             wx.setStorageSync('token', token);
             this.updateUserInfo();
           }
        });
      }
    })
 
  },
  updateUserInfo(){
    // 获取用户信息
    wx.getSetting({
      success: res => {
        if (res.authSetting['scope.userInfo']) {
          // 已经授权，可以直接调用 getUserInfo 获取头像昵称，不会弹框
          wx.getUserInfo({
            success: res => {
              // 可以将 res 发送给后台解码出 unionId
              let userInfo = res.userInfo;
          
              var body = {
                nickname:userInfo.nickeName,
                avatar: userInfo.avatarUrl,
                gender: userInfo.gender,
                province:userInfo.province,
                city:userInfo.city,
                country:userInfo.country
              }
              _post("/mpapi/user/updateUserInfo", body).then(function(){

              });

              // 由于 getUserInfo 是网络请求，可能会在 Page.onLoad 之后才返回
              // 所以此处加入 callback 以防止这种情况
              if (this.userInfoReadyCallback) {
                this.userInfoReadyCallback(res)
              }
            }
          })
        }
      }
    })
  },
  globalData: {
      ismock:true,
      globalBGColor: '#00afb4',
      bgRed: 0,
      bgGreen: 175,
      bgBlue: 180,
      userInfo: null,
      version:"1.0",
    },
    _get:_get,
    _post:_post,
    _delete:_delete,
    _put:_put,
  /**
* 显示成功提示框
*/
  showSuccess(msg, callback) {
    wx.showToast({
      title: msg,
      icon: 'success',
      duration: 1500,
      success() {
        callback && callback();
      }
    });
  },
    showInfo(msg, callback) {
        wx.showToast({
            title: msg,
            icon: 'none',
            duration: 2000,
            success() {
                callback && callback();
            }
        });
    },
  /**
   * 显示失败提示框
   */
  showError(msg, callback) {
    wx.showModal({
      title: '友情提示',
      content: msg,
      showCancel: false,
      success(res) {
        callback && callback();
      }
    });
  },
  //版本更新管理
  updateProject: function () {
    const updateManager = wx.getUpdateManager()

    updateManager.onUpdateReady(function () {
      wx.showModal({
        title: '更新提示',
        content: '版本已更新，是否重启应用？',
        success: function (res) {
          if (res.confirm) {
            // 新的版本已经下载好，调用 applyUpdate 应用新版本并重启
            updateManager.applyUpdate()
          }
        }
      })
    })

    updateManager.onUpdateFailed(function () {
      // 新的版本下载失败
      console.log('onUpdateFailed：新的版本下载失败')
      wx.showModal({
        title: '更新提示',
        content: '新的版本下载失败；',
        success: function (res) {
          if (res.confirm) {
          }
        }
      })
    })

  },
})