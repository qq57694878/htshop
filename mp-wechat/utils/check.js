function checkPhone(phone) {
    var myreg = /^[1][3,4,5,6,7,8,9][0-9]{9}$/;
    if (!myreg.test(phone)) {
        return false;
    } else {
        return true
    }
}
function checkCarNo(carNo) {
    var express = /^[京津沪渝冀豫云辽黑湘皖鲁新苏浙赣鄂桂甘晋蒙陕吉闽贵粤青藏川宁琼使领A-Z]{1}[A-Z]{1}[A-Z0-9]{4}[A-Z0-9挂学警港澳]{1}$/;
    if (!express.test(carNo)) {
        return false;
    } else {
        return true
    }
}

module.exports = {
    checkPhone: checkPhone,
    checkCarNo:checkCarNo
};