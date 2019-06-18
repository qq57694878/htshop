var Mock = require("../wxmock/WxMock.js"); 
import admin from "./admin.js"
import cart from "./cart.js"
import goods from "./goods.js"
console.log("执行admin mock");

wx.setStorageSync("token", 1);
admin({ Mock,ismock:true});
cart({ Mock, ismock: true });
goods({ Mock, ismock: true });