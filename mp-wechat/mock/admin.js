
import properties from "../utils/properties.js"
export default ({ Mock,ismock }) => {
  if (!ismock) return;
  console.log("执行mock数据" + properties.hostUrl + '/order/list');
  Mock.mock(properties.hostUrl +'/order/list', (res) => {
    console.log("mock模拟订单列表start");
    var orderList = [];
    for(var i=1;i<=10;i++){
      var o = {
        id: "" + i,
        statusStr: "未付款",
        dateAdd: '2019-05-01',
        orderNumber: "1112",
        status: "0",
        amountReal: 100
      };
      orderList.push(o);
    }
    console.log("--------------------------------");
    var goodsMap ={};
    for(var i=1;i<=10;i++){
      goodsMap["" + i] = [{
        "pic": "http://www.kulongtai.com/"+i+".jpg"
      }]
    }
    console.log("--------------------------------");
    
    console.log("mock模拟订单列表end");
    return {
      data:{
        code: 0,
        data: {
          orderList: orderList,
          goodsMap: goodsMap,
          logisticsMap:{}
        }
      }
    }
  })

}