//app.js
import properties from "./utils/properties.js"
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
        _get("/mpapi/login", { code: res.code }).then(function (res) {
          if (res.code == 200) {
            wx.setStorageSync('token', res.data);
          }
        });
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