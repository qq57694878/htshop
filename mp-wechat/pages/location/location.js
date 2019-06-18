var countTooGetLocation = 0;
var total_micro_second = 0;
var starRun = 0;
var totalSecond  = 0;
var oriMeters = 0.0;
/* 毫秒级倒计时 */
function count_down(that) {

    if (starRun == 0) {
      return;
    }

    if (countTooGetLocation >= 100) {
      var time = date_format(total_micro_second);
      that.updateTime(time);
    }

  	if (countTooGetLocation >= 5000) { //1000为1s
        that.getLocation();
        countTooGetLocation = 0;
  	}   
    

 setTimeout
  	setTimeout(function(){
		countTooGetLocation += 10;
    total_micro_second += 10;
		count_down(that);
    }
    ,10
    )
}


// 时间格式化输出，如03:25:19 86。每10ms都会调用一次
function date_format(micro_second) {
  	// 秒数
  	var second = Math.floor(micro_second / 1000);
  	// 小时位
  	var hr = Math.floor(second / 3600);
  	// 分钟位
  	var min = fill_zero_prefix(Math.floor((second - hr * 3600) / 60));
  	// 秒位
	var sec = fill_zero_prefix((second - hr * 3600 - min * 60));// equal to => var sec = second % 60;


	return hr + ":" + min + ":" + sec + " ";
}


function getDistance(lat1, lng1, lat2, lng2) { 
    var dis = 0;
    var radLat1 = toRadians(lat1);
    var radLat2 = toRadians(lat2);
    var deltaLat = radLat1 - radLat2;
    var deltaLng = toRadians(lng1) - toRadians(lng2);
    var dis = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin(deltaLat / 2), 2) + Math.cos(radLat1) * Math.cos(radLat2) * Math.pow(Math.sin(deltaLng / 2), 2)));
    return dis * 6378137;

    function toRadians(d) {  return d * Math.PI / 180;}
} 

function fill_zero_prefix(num) {
	return num < 10 ? "0" + num : num
}
function formatLocation(longitude, latitude) {
  longitude = longitude.toFixed(2)
  latitude = latitude.toFixed(2)

  return {
    longitude: longitude.toString().split('.'),
    latitude: latitude.toString().split('.')
  }
}
const device = wx.getSystemInfoSync()
const width = device.windowWidth
const height = device.windowHeight

//****************************************************************************************
//****************************************************************************************

Page({
  data: {
    height: height,
    clock: '',
    isLocation:false,
    latitude: 0,
    longitude: 0,
    markers: [],
    meters: 0.00,
    time: "0:00:00",
    markerTarget: {
      latitude: 38.924616,
      longitude: 121.618041,
      iconPath: "/images/location_red_blue.png",
      width: 20,
      height: 30,
      callout:{
        display:"ALWAYS",
        content:"韩泰轮胎"
      },
      label:{
        content:"辽宁省大连市西岗区站北街道双星街263号",
        anchorX: -100, 
        anchorY: -75,
        color:"#888"
      }
    }
  },

//****************************
  onLoad:function(options){
    // 页面初始化 options为页面跳转所带来的参数
    this.getTgLocation()
    console.log("onLoad")
    count_down(this);
  },
  //****************************
  openLocation:function (){
    var that = this
 
    wx.getLocation({
      type: 'gcj02', // 默认为 wgs84 返回 gps 坐标，gcj02 返回可用于 wx.openLocation 的坐标
      success: function(res){
          wx.openLocation({
            latitude: res.latitude, // 纬度，范围为-90~90，负数表示南纬
            longitude: res.longitude, // 经度，范围为-180~180，负数表示西经
            scale: 18, // 缩放比例
          })
      },
    })
  },
  openTgLocation: function () {
    var that = this
 
    wx.openLocation({
      latitude: that.data.markerTarget.latitude,
      longitude: that.data.markerTarget.longitude,
      scale: 18, // 缩放比例
    })
  },


//****************************
  starRun :function () {
    if (starRun == 1) {
      return;
    }
    starRun = 1;
    count_down(this);
    this.getLocation();
  },


 //****************************
  stopRun:function () {
    starRun = 0;
    count_down(this);
  },


//****************************
  updateTime:function (time) {

    var data = this.data;
    data.time = time;
    this.data = data;
    this.setData ({
      time : time,
    })

  },


//****************************
  getLocation:function () {
    wx.getLocation({
      type: 'gcj02', // 默认为 wgs84 返回 gps 坐标，gcj02 返回可用于 wx.openLocation 的坐标
      success: function(res){
        console.log(res)
        //make datas 
        var newMarker = that.data.markerTarget;
        var oriMarkers = that.data.markers;
        

        var len = oriMarkers.length;
        var lastCover;
        if (len == 0) {
          oriMarkers.push();
        }
        len = oriMarkers.length;
        var lastCover = oriMarkers[len-1];
        
     

        var newMeters = getDistance(lastCover.latitude,lastCover.longitude,res.latitude,res.longitude)/1000;
        
        if (newMeters < 0.0015){
            newMeters = 0.0;
        }

        oriMeters = oriMeters + newMeters; 

        var meters = new Number(oriMeters);
        var showMeters = meters.toFixed(2);

        oriMarkers.push(newMarker);
        
        that.setData({
          latitude: res.latitude,
          longitude: res.longitude,
          markers: oriMarkers,
          meters:showMeters,
        });
      },
    })
  },
//****************************
  getTgLocation: function () {
    var that = this
    wx.getLocation({
      type: 'gcj02', // 默认为 wgs84 返回 gps 坐标，gcj02 返回可用于 wx.openLocation 的坐标
      success: function (res) {
   
       var newMarker = that.data.markerTarget;

        var newMeters = getDistance(newMarker.latitude, newMarker.longitude, res.latitude, res.longitude) / 1000;
        if (newMeters < 0.0015) {
          newMeters = 0.0;
        }
   

        var meters = new Number(newMeters);
        var showMeters = meters.toFixed(2);

        var oriMarkers = that.data.markers;
        oriMarkers.push(newMarker);
 

        that.setData({
          latitude: that.data.markerTarget.latitude,//res.latitude,
          longitude: that.data.markerTarget.longitude,//res.longitude,
          markers: oriMarkers,
          meters: showMeters,
        });
      },
    })
  }
  
})



