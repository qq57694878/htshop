var a = {
 healthDatas: {
    "01": "",
        "02": "",
        "03": "",
        "04A": "",
        "04B": "",
        "04C": "",
        "005a": "",
        "05B1": "",
        "05B2": "",
        "05B3": "",
        "05B4": "",
        "05C": "",
        "06": ""
},
    deImpartInfoList:function() {
    const impartInfoList = [];
    for (let i in this.healthDatas) {
        let item = this.healthDatas[i];
        const impartInfoObj = {
            code: i,
            param: ""
        };
        switch (i) {
            case "005a":
                if (item === '否') {
                    impartInfoObj.param = `N,${this.A5}`;
                } else {
                    impartInfoObj.param = "Y";
                }
                impartInfoList.push(impartInfoObj);
                break;
            case "05B1":
                if (item) {
                    impartInfoObj.param = `Y,${this.B1}`;
                    impartInfoList.push(impartInfoObj);
                }
                break;
            case "05B2":
                if (item) {
                    impartInfoObj.param = `Y,${this.B2}`;
                    impartInfoList.push(impartInfoObj);
                }
                break;
            case "05B3":
                if (item) {
                    impartInfoObj.param = `Y`;
                    impartInfoList.push(impartInfoObj);
                }

                break;
            case "05B4":
                if (item) {
                    impartInfoObj.param = `Y,${this.B4}`;
                    impartInfoList.push(impartInfoObj);
                }
                break;
            case "05C":
                let code = "05C1";
                if (item === "2") {
                    code = "05C2";
                } else if (item === "3") {
                    code = "05C3";
                }
                impartInfoList.push({ code: code, param: "Y" });
                break;
            case "06":
                if (!item) {
                    impartInfoObj.param = `无`;
                } else {
                    impartInfoObj.param = item;
                }
                impartInfoList.push(impartInfoObj);
                break;
            default:
                impartInfoObj.param = item;
                impartInfoList.push(impartInfoObj);
                break;
        }
    }
    return impartInfoList;
}
}
console.log(a.deImpartInfoList());