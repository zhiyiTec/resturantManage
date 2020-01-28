//仅用于获取数据的异步传输
function ajax_get(url, data, transmission_type) {
    var re;
    $.ajax({
        url: url,
        data: data,
        dataType: "json",
        type: transmission_type,
        async: false, //此处必须要有这句话，否则任然会传值失败
        //加上这句话,允许浏览器向服务器跨域请求时携带cookie
        xhrFields: {
            withCredentials: true
        },
        crossDomain: true,
        success: function(data) {
            re = data;
        },
        error: function() {}
    });
    return re;
}