
import properties from "../utils/properties.js"
export default ({ Mock,ismock }) => {
  if (!ismock) return;
 
  Mock.mock(properties.hostUrl +'/cart/list', (res) => {

    var goods_list = [];
    for(var i=1;i<=10;i++){
      var o = {
        goods_id:""+i,
        goods_name: "车辆小保养套餐卡" + i,
        goods_price: 1000.00,
        total_num: 2,
        goods_sku_id: "" + i,
        image: [{ file_path:"http://www.kulongtai.com/"+i+".jpg"}],
      };
      goods_list.push(o);
    }


    return {
      data:{
        code: 200,
        data: {
          goods_list: goods_list,
        }
      }
    }
  });

  Mock.mock(properties.hostUrl + '/cart/add', (res) => {
    return {
      data: {
        code: 200,
        data: {

        }
      }
    }
  });
  Mock.mock(properties.hostUrl + '/cart/sub', (res) => {
    return {
      data: {
        code: 200,
        data: {
         
        }
      }
    }
  });
  Mock.mock(properties.hostUrl + '/cart/delete', (res) => {
    return {
      data: {
        code: 200,
        data: {

        }
      }
    }
  });
  
}