 function formatMoney(num) {
    /*    num = parseFloat(num);
     num = num.toFixed(2);
     num = num.toLocaleString();
     return num;// 返回的是字符串23245.12保留2位小数，可以补全第一位0，会显示0.00，但是千分位符不显示了。*/
    return parseFloat(num).toFixed(2).toLocaleString();
}
 function formatMoney3(num) {
    /*    num = parseFloat(num);
     num = num.toFixed(2);
     num = num.toLocaleString();
     num  = num.replace(/^-?\d+/g, m => m.replace(/(?=(?!\b)(\d{3})+$)/g, ','));
     return num;// 返回的是字符串23245.12保留2位小数，可以补全第一位0，会显示0.00，但是千分位符不显示了。*/
    return parseFloat(num).toFixed(2).toLocaleString().replace(/^-?\d+/g, m => m.replace(/(?=(?!\b)(\d{3})+$)/g, ','));
}
console.log(formatMoney3(-1));
console.log(formatMoney3(-1.0));
console.log(formatMoney3("0"));
console.log(formatMoney3("-10000"));
console.log(formatMoney3(-10000000.0123));