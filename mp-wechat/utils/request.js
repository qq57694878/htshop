const properties = require('properties');
function  post(suburl,data){
  wx.showNavigationBarLoading(); //在标题栏中显示加载
  var token = wx.getStorageSync('token');
  // if (!token){
  //   wx.showToast({
  //     title: 'token获取失败',
  //     duration: 2000
  //   })
  //   return;
  // }
  return new Promise((resolve, reject) => {
    wx.request({
      url: properties.hostUrl + suburl,
      data: data?data:"",
      header: {
        'Authorization': "Bearer "+token,
        'content-type': 'application/json' // 默认值
      },
      method: 'POST',
      dataType: 'json',
      success: function (res) {
        resolve(res.data)
      },
      fail: function (res) {
        wx.showToast({
          icon:'none',
          title: '加载失败',
          duration: 2000
        })
        reject(res.data)
      },
      complete: function () {
        wx.hideNavigationBarLoading(); //完成停止加载
      }
    })
  });
}
function getjson(suburl, data) {
  wx.showNavigationBarLoading(); //在标题栏中显示加载
  var token = wx.getStorageSync('token');
  // if (!token) {
  //   wx.showToast({
  //     title: 'token获取失败',
  //     duration: 2000
  //   })
  //   return;
  // }
  return new Promise((resolve, reject) => {
    wx.request({
      url: properties.hostUrl + suburl,
      data: data ? data : "",
      header: {
        'Authorization': "Bearer " + token,
      },
      method: 'GET',
      dataType: 'json',
      success: function (res) {
        resolve(res.data)
      },
      fail: function (res) {
        wx.showToast({
          icon: 'none',
          title: '加载失败',
          duration: 2000
        })
        reject(res.data)
      },
      complete: function () {
        wx.hideNavigationBarLoading(); //完成停止加载
      }
    })
  });
}
function _delete(suburl, data) {
  wx.showNavigationBarLoading(); //在标题栏中显示加载
  var token = wx.getStorageSync('token');
  // if (!token) {
  //   wx.showToast({
  //     title: 'token获取失败',
  //     duration: 2000
  //   })
  //   return;
  // }
  return new Promise((resolve, reject) => {
    wx.request({
      url: properties.hostUrl + suburl,
      data: data ? data : "",
      header: {
        'Authorization': "Bearer " + token,
      },
      method: 'DELETE',
      dataType: 'json',
      success: function (res) {
        resolve(res.data)
      },
      fail: function (res) {
        wx.showToast({
          icon: 'none',
          title: '加载失败',
          duration: 2000
        })
        reject(res.data)
      },
      complete: function () {
        wx.hideNavigationBarLoading(); //完成停止加载
      }
    })
  });
}
function put(suburl, data) {
  wx.showNavigationBarLoading(); //在标题栏中显示加载
  var token = wx.getStorageSync('token');
  // if (!token) {
  //   wx.showToast({
  //     title: 'token获取失败',
  //     duration: 2000
  //   })
  //   return;
  // }
  return new Promise((resolve, reject) => {
    wx.request({
      url: properties.hostUrl + suburl,
      data: data ? data : "",
      header: {
        'Authorization': "Bearer " + token,
      },
      method: 'PUT',
      dataType: 'json',
      success: function (res) {
        resolve(res.data)
      },
      fail: function (res) {
        wx.showToast({
          icon: 'none',
          title: '加载失败',
          duration: 2000
        })
        reject(res.data)
      },
      complete: function () {
        wx.hideNavigationBarLoading(); //完成停止加载
      }
    })
  });
}
module.exports = {
  _post: post,
  _get: getjson,
  _delete:_delete,
  _put:put
};