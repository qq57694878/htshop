const CODE_TABLE = [
    ["order_status",[["0","待付款"],["1","已取消"],["8","已完成"]]],
    ["pay_status",[["0","未支付"],["1","已支付"]]],
    ["catagory",[["1","洗车卡"],["2","保养卡"]]],
    ["sku_status",[["1","上架"],["2","下架"]]],//商品状态（1上架2下架）
    ["gender",[["0","未知"],["1","男"],["2","女"]]],//性别：0 未知， 1男， 2 女
    ["user_status",[["0","启用"],["1","禁用"],["2","注销"]]],//用户状态 0 可用, 1 禁用, 2 注销
    ["valid_flag",[["0","无效"],["1","有效"]]],//用有效标记 0无效：1：有效
    ["buss_type",[["1","次数卡"],["2","E卡"]]],//商品业务类型 1次数卡 2 E卡
]
/*根据码值获得说明
code 码值
type 类型
*/
export function code2value(code,type){
    let result="";
    let map=new Map(CODE_TABLE);
    let c = map.get(type)
    if(c){
        let subMap = new Map(c);
        result = subMap.get(code+"");
    }
    return result;
}
export function type2options4query(type){
    let result=[{label:"全部",value:""}];
    let map=new Map(CODE_TABLE);
    let c = map.get(type)
    if(c){
        c.forEach(
            ([value, label]) => result.push({label:label,value:value})
        );
    }
    return result;
}
export function type2options(type){
    let result=[];
    let map=new Map(CODE_TABLE);
    let c = map.get(type)
    if(c){
        c.forEach(
            ([value, label]) => result.push({label:label,value:value})
        );
    }
    return result;
}

