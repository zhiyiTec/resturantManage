// 用于验证用户token
function vertifacation_token(userId) {
    var result;
    $.ajax({
        url: host + "/judgeToken",
        data: {
            userId: userId
        },
        dataType: "json",
        type: "post",
        //加上这句话,允许浏览器向服务器跨域请求时携带cookie
        xhrFields: {
            withCredentials: true
        },
        crossDomain: true,
        async: false, //此处必须要有这句话，否则仍然会传值失败
        success: function(data) {
            result = data.re;
        }
    });
    return result;
}


//   判断用户输入的字符是否为空
// 为空返回true
function empty(value) {
    var patt1 = new RegExp(/\s+/g); //判断空格的正则表达式
    if (value == '' || value == null || patt1.test(value)) {
        return true;
    } else {
        return false;
    }
}