
import properties from "../utils/properties.js"
export default ({ Mock,ismock }) => {
  if (!ismock) return;
 
  Mock.mock(properties.hostUrl +'/goods/detail', (res) => {
 

    return {
      data:{
        code: 200,
        data: {
          detail:{
            goods_id:2,
            goods_sku_id:1,
           goods_price :100,
            line_price :99,
            stock_num :1000,
            goods_name:"青岛啤酒500ml装12瓶",
            goods_sales:133,
            content:"<img src='http://www.kulongtai.com/1.jpg'/>",
            image: [{ file_path: "http://www.kulongtai.com/1.jpg"}, { file_path:"http://www.kulongtai.com/2.jpg"}]

          }
        }
      }
    }
  });

  
}