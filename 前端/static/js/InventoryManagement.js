// 库存管理
var cuPn_IM; //当前页码
var cuBuyerInfo_IM; //当前客户信息
var curBuyerId_IM; //当前客户的id
$(document).on("click", "#IM", function() {
    $("#right").empty();
    $("#right").append(" <div id='nav'></div>");
    $("#nav").append(
        " <ol class='breadcrumb p-l-15 '> <li><a href='#' class='' disabled='disabled'>Home</a></li> <li><a href='#'>药店销售管理</a></li> <li class='active'>库存管理</li></ol>"
    );
    $("#right").append(" <div id='search' class='p-l-25 '></div>");
    $("#search").append(
        "<form class='navbar-form navbar-left' action='#'><div class='form-group'><input type='text' id='searchInput_IM' placeholder='输入查询内容' class='h-34'/></div> <div class='btn-group'> <button type='button' class='btn btn-default dropdown-toggle' data-toggle='dropdown' aria-haspopup='true' aria-expanded='false'>查询 <span class= 'caret' ></span></button > <ul class='dropdown-menu'><li role='separator' class='divider'></li><li><a href='#' id='searchInputByname_IM'>按药品名查询</a></li><li role='separator' class='divider'></li><li><a href='#' id='searchbyID_IM'>按药品编码查询</a></li></ul ></div> </form>  "
    );
    $("#search").append(
        "<div class='float-r'> <div class='btn-group p-r-30'> <button type='button' class='btn btn-default dropdown-toggle btn-style' data-toggle='dropdown' aria-haspopup='true' aria-expanded='false'>功能 <span class= 'caret' ></span></button > <ul class='dropdown-menu'><li><a href='#' id='notice_IM'>通知</a></li></ul ></div><div>"
    );
    $("#right").append(" <div id='cotent' class='p-l-25 '></div>");
    $("#cotent").append(
        "<table class='table table-striped table-bordered table-hover'> <thead><tr> <th><input type='checkbox' id='check_ALl' /></th><th>药品编码</th><th>药品名称</th><th>药品类别</th><th>生产日期</th><th>保质期</th><th>供应商</th></tr> </thead><tbody id='tbody_data'></tbody></table>"
    );
    //    < !--用于显示分页信息 -->
    $("#right").append(
        "<div class='row p-l-25'><div class='col-md-6' id='PageInfo'></div><div class='col-md-6' id='PageNav'></div></div>"
    );
    toPage_IM(1);
});
//用于跳转页面
function toPage_IM(pn) {
    cuPn_IM = pn;
    $.ajax({
        url: host + "/getMedinceInfo_IM",
        data: {
            pn: pn
        },
        dataType: "json",
        type: "post",
        //加上这句话,允许浏览器向服务器跨域请求时携带cookie
        xhrFields: {
            withCredentials: true
        },
        crossDomain: true,
        success: function(data) {
            var emp = data.results.list;

            //获取成功之后要做的第一件事情就是：解析并显示学生数据
            showEmpMesage_IM(emp);
            //获取成功之后要做的第二件事情就是：解析并显示分页信息
            showPageInfo_IM(data);
            //获取成功之后要做的第三件事情就是：解析并显示分页条
            showPageNav_IM(data);
        }
    });
}
//解析显示药品信息
function showEmpMesage_IM(data) {
    //每一次构建之前先把数据清空
    $("#tbody_data").empty();
    //此处的写法应该根据浏览器显示的数据进行编写

    $.each(data, function(index, item) {
        $("#tbody_data").append("<tr id='tr" + index + "'></tr>");
        $("#tr" + index).append(
            "<td><input type='checkbox' class='check_item' data-checkBoxName='" +
            item.medinceName +
            "' data-checkBoxId='" +
            item.medinceId +
            "'/></td>"
        );
        $("#tr" + index).append("<td>" + item.medinceId + "</td>");
        $("#tr" + index).append("<td>" + item.medinceName + "</td>");
        $("#tr" + index).append("<td>" + item.medinceTypeCotent + "</td>");
        $("#tr" + index).append("<td>" + item.productTime + "</td>");
        $("#tr" + index).append("<td>" + item.overTime + " 天</td>");
        $("#tr" + index).append("<td>" + item.productName + "</td>");
    });
}
//解析显示分页信息
function showPageInfo_IM(data) {
    //每一次构建之前先把数据清空
    $("#PageInfo").empty();
    $("#PageInfo").append(
        "当前第" +
        data.results.pageNum +
        "页 ,总共" +
        data.results.pages +
        "页,共" +
        data.results.total +
        "条记录"
    );
    pageNumber = data.results.pageNum;
}
//解析显示分页条,并且能够可以点击下一页并跳转到对应的页面
function showPageNav_IM(data) {
    //每一次构建之前先把数据清空
    $("#PageNav").empty();
    $("#PageNav").append(
        "<nav aria-label='Page navigation'><ul class='pagination'></ul></nav>"
    );
    if (data.results.pageNum > 1) {
        var dataPN = 1;
        $(".pagination").append(
            "<li><a href='#' data-pn='" + dataPN + "' class='pn_IM'>首页</a></li>"
        );
    }
    //上一页的值
    var prevoiuePage = data.results.pageNum - 1;
    if (data.results.hasPreviousPage) {
        $(".pagination").append(
            "<li ><a href='#' aria-label='Previous' data-pn='" +
            prevoiuePage +
            "' class='pn_IM'> <span aria-hidden='true' class='span-style'>&laquo;</span></a></li>"
        );
    }
    $.each(data.results.navigatepageNums, function(index, item) {
        if (item == data.results.pageNum) {
            $(".pagination").append(
                "<li class='active pn_IM'><a href='#' class='pn_IM' data-pn='" +
                item +
                "'>" +
                item +
                "</a></li>"
            );
        }
        if (item != data.results.pageNum) {
            $(".pagination").append(
                "<li><a href='#' class='pn_IM' data-pn='" +
                item +
                "'>" +
                item +
                "</a></li>"
            );
        }
    });
    //下一页的值
    var nextPage = data.results.pageNum + 1;
    if (data.results.hasNextPage) {
        // datePn = enterpriseId + "-" + nextPage;
        $(".pagination").append(
            "<li ><a href='#' aria-label='Next' data-pn='" +
            nextPage +
            "' class='pn_IM'> <span aria-hidden='true' class='span-style'>&raquo;</span></a></li>"
        );
        //末页的值
        var lastPage = data.results.pages;
        var total = data.results.total;
        pageTotal = lastPage;
        pages = lastPage;

        $(".pagination").append(
            "<li ><a href='#' class='pn_IM' data-pn='" + lastPage + "'>末页</a></li>"
        );
    }
}
//用于设置翻页的点击事件
$(document).on("click", ".pn_IM", function() {
    var pageNumber = $(this).attr("data-pn");
    cuPn_IM = pageNumber;
    toPage_IM(pageNumber);
});
//用于通知的点击事件
$(document).on("click", "#notice_IM", function() {
    $.ajax({
        url: host + "/getLittleMedince",
        data: {},
        dataType: "json",
        type: "post",
        //加上这句话,允许浏览器向服务器跨域请求时携带cookie
        xhrFields: {
            withCredentials: true
        },
        crossDomain: true,
        success: function(data) {
            $("#notice_tab").empty();
            $.each(data.littleMedince, function(index, item) {
                $("#notice_tab").append("<tr id='tr" + index + "'></tr>");
                $("#tr" + index).append("<td>" + item.medinceId + "</td>");
                $("#tr" + index).append("<td>" + item.medinceName + "</td>");
                $("#tr" + index).append("<td>" + item.restNumber + "</td>");
                $("#tr" + index).append("<td id='bu" + index + "'></td>");
            });
            $("#noticeModal_IM").modal({
                backdrop: "static"
            });
        }
    });
});
//实现查询功能

//按药品名查询
$(document).on("click", "#searchInputByname_IM", function() {
    var name = $("#searchInput_IM").val();
    console.log(name);
    $.ajax({
        url: host + "/searchMedinceByName_IM",
        data: {
            pn: 1,
            name: name
        },
        dataType: "json",
        type: "post",
        //加上这句话,允许浏览器向服务器跨域请求时携带cookie
        xhrFields: {
            withCredentials: true
        },
        crossDomain: true,
        success: function(data) {
            var datare = data.results.list;
            showEmpMesage_IM(datare);
        },
        error: function() {}
    });
});
//按药品编码查询
$(document).on("click", "#searchbyID_IM", function() {
    var MI = $("#searchInput_IM").val();

    $.ajax({
        url: host + "/searchMedinceByMI_IM",
        data: {
            pn: 1,
            MI: MI
        },
        dataType: "json",
        type: "post",
        //加上这句话,允许浏览器向服务器跨域请求时携带cookie
        xhrFields: {
            withCredentials: true
        },
        crossDomain: true,
        success: function(data) {
            var datare = data.results.list;
            showEmpMesage_IM(datare);
        },
        error: function() {}
    });
});