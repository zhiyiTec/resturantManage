/**
 * 提示框
 * @param {*} message 
 * @param {*} url 
 */
function botx_confirm(message, url) {
    bootbox.confirm({
        message: message,
        buttons: {
            confirm: {
                label: '登录',
                className: 'btn-success'
            },
            cancel: {
                label: '取消',
                className: 'btn-danger'
            }
        },
        callback: function(result) {

            if (result) window.location.href = url;
            else return
        }
    });
}

function botx_alert(message, size) {
    bootbox.alert({
        message: message,
        size: size
    });
}