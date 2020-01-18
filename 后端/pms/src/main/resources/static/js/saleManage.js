// 会员充值记录
var name; //当前用户的姓名
var cuPn_SM; //当前页码
var cuBuyerInfo_SM; //当前客户信息
var curBuyerId_SM; //当前客户的id
$(document).on("click", "#sailManage", function() {
    $("#right").empty();
    $("#right").append(" <div id='nav'></div>");
    $("#nav").append(
        " <ol class='breadcrumb p-l-15 '> <li><a href='#' class='' disabled='disabled'>Home</a></li> <li><a href='#'>会员充值管理</a></li></ol>"
    );
    $("#right").append(" <div id='search' class='p-l-25 '></div>");
    $("#search").append(
        "<form class='navbar-form navbar-left' action='#'><div class='form-group'><input type='text' id='searchInput_SM' placeholder='输入查询内容' class='h-34'/></div> <div class='btn-group'> <button type='button' class='btn btn-default dropdown-toggle' data-toggle='dropdown' aria-haspopup='true' aria-expanded='false'>查询 <span class= 'caret' ></span></button > <ul class='dropdown-menu'><li role='separator' class='divider'></li><li><a href='#' id='searchInputByname_IM'>按药品名查询</a></li><li role='separator' class='divider'></li><li><a href='#' id='searchbyID_SM'>按药品编码查询</a></li></ul ></div> </form>  "
    );
    $("#search").append(
        "<div class='float-r'> <div class='btn-group p-r-30'> <button type='button' class='btn btn-default dropdown-toggle btn-style' data-toggle='dropdown' aria-haspopup='true' aria-expanded='false'>功能 <span class= 'caret' ></span></button > <ul class='dropdown-menu'><li role='separator' class='divider'></li><li><a href='#' id='empDeleBtn_SM'>批量删除</a></li><li role='separator' class='divider'></li></ul ></div><div>"
    );
    $("#right").append(" <div id='cotent' class='p-l-25 '></div>");
    $("#cotent").append(
        "<table class='table table-striped table-bordered table-hover'> <thead><tr> <th><input type='checkbox' id='check_ALl' /></th><th>用户名</th><th>电话</th><th>账户余额</th><th>操作</th></tr> </thead><tbody id='tbody_data'></tbody></table>"
    );
    //    < !--用于显示分页信息 -->
    $("#right").append(
        "<div class='row p-l-25'><div class='col-md-6' id='PageInfo'></div><div class='col-md-6' id='PageNav'></div></div>"
    );
    toPage_SM(1);
});
//用于跳转页面
function toPage_SM(pn) {
    cuPn_SM = pn;
    $.ajax({
        url: host + "/getAllRecharge",
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
            showEmpMesage_SM(emp);
            //获取成功之后要做的第二件事情就是：解析并显示分页信息
            showPageInfo_SM(data);
            //获取成功之后要做的第三件事情就是：解析并显示分页条
            showPageNav_SM(data);
        }
    });
}
//解析显示药品信息
function showEmpMesage_SM(data) {
    //每一次构建之前先把数据清空
    $("#tbody_data").empty();
    //此处的写法应该根据浏览器显示的数据进行编写

    $.each(data, function(index, item) {
        $("#tbody_data").append("<tr id='tr" + index + "'></tr>");
        $("#tr" + index).append(
            "<td><input type='checkbox' class='check_item' data-checkBoxName='" +
            item.name +
            "' data-checkBoxId='" +
            item.userid +
            "'/></td>"
        );
        $("#tr" + index).append("<td>" + item.name + "</td>");
        $("#tr" + index).append("<td>" + item.userid + "</td>");
        $("#tr" + index).append("<td>" + item.restmoney + "</td>");
        $("#tr" + index).append("<td id='bu" + index + "'></td>");
        $("#bu" + index).append(
            "<button class='btn btn-danger btn-sm delete_SM' data-delete='" +
            item.userid +
            "' data-name='" +
            item.name +
            "'>删除</button>"
        );
        $("#bu" + index).append("   ")
        $("#bu" + index).append(
            "<button class='btn btn-primary btn-sm recharge_more' data-delete='" +
            item.userid +
            "' data-name='" +
            item.name +
            "'>更多充值信息</button>"
        );
    });
}
//解析显示分页信息
function showPageInfo_SM(data) {
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
function showPageNav_SM(data) {
    //每一次构建之前先把数据清空
    $("#PageNav").empty();
    $("#PageNav").append(
        "<nav aria-label='Page navigation'><ul class='pagination'></ul></nav>"
    );
    if (data.results.pageNum > 1) {
        var dataPN = 1;
        $(".pagination").append(
            "<li><a href='#' data-pn='" + dataPN + "' class='pn_SM'>首页</a></li>"
        );
    }
    //上一页的值
    var prevoiuePage = data.results.pageNum - 1;
    if (data.results.hasPreviousPage) {
        $(".pagination").append(
            "<li ><a href='#' aria-label='Previous' data-pn='" +
            prevoiuePage +
            "' class='pn_SM'> <span aria-hidden='true' class='span-style'>&laquo;</span></a></li>"
        );
    }
    $.each(data.results.navigatepageNums, function(index, item) {
        if (item == data.results.pageNum) {
            $(".pagination").append(
                "<li class='active pn_IM'><a href='#' class='pn_SM' data-pn='" +
                item +
                "'>" +
                item +
                "</a></li>"
            );
        }
        if (item != data.results.pageNum) {
            $(".pagination").append(
                "<li><a href='#' class='pn_SM' data-pn='" +
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
            "' class='pn_SM'> <span aria-hidden='true' class='span-style'>&raquo;</span></a></li>"
        );
        //末页的值
        var lastPage = data.results.pages;
        var total = data.results.total;
        pageTotal = lastPage;
        pages = lastPage;

        $(".pagination").append(
            "<li ><a href='#' class='pn_SM' data-pn='" + lastPage + "'>末页</a></li>"
        );
    }
}
//用于设置翻页的点击事件
$(document).on("click", ".pn_SM", function() {
    var pageNumber = $(this).attr("data-pn");
    cuPn_SM = pageNumber;
    toPage_SM(pageNumber);
});
//用于给动态产生的删除按钮添加点击事件
$(document).on("click", ".delete_SM", function() {
    var emp_id = $(this).attr("data-delete");
    var emp_Name = $(this).attr("data-name");
    var re = confirm("确认要删除【" + emp_Name + "】这条充值信息");
    if (!re) {
        return false;
    }
    $.ajax({
        url: host + "/deleteRecharge",
        data: {
            sI: emp_id
        },
        dataType: "json",
        type: "post",
        //加上这句话,允许浏览器向服务器跨域请求时携带cookie
        xhrFields: {
            withCredentials: true
        },
        crossDomain: true,
        success: function(data) {
            if (data.re == 0) alert("删除失败");
            else alert("删除成功");

            toPage_SM(cuPn_SM);
        },
        error: function() {
            alert("删除失败，请刷新后重试");
        }
    });
});
//实现删除多个员工的按钮功能
$(document).on("click", "#empDeleBtn_SM", function() {
    var checkBox_Checked = $(".check_item:checked"); //此处用于获取选中的复选框
    var emp_nameAsSelected = "";
    var emp_idAsSelected = "";
    var count = 0;
    $.each(checkBox_Checked, function() {
        emp_nameAsSelected += $(this).attr("data-checkBoxName") + ","; //用于获取每一个按钮的name值
        emp_idAsSelected += $(this).attr("data-checkBoxId") + "-";
        count += 1;
    });
    //去除emp_nameAsSelected中多余的逗号
    var emp_nameAsSelectedSub = emp_nameAsSelected.substring(
        0,
        emp_nameAsSelected.length - 1
    );
    emp_idAsSelected = emp_idAsSelected.substring(0, emp_idAsSelected.length - 1);

    var con = confirm(
        "确认删除【" + emp_nameAsSelectedSub + "】这" + count + "个员工吗"
    );
    if (con == false) {
        return false;
    }

    $.ajax({
        url: host + "/deleteSails_SM",
        data: "id=" + emp_idAsSelected,
        type: "post",
        success: function(data) {
            alert("删除成功");

            //跳转到当前页面
            toPage_SM(cuPn_SM);
        }
    });
});
//按药品名查询
$(document).on("click", "#searchInputByname_IM", function() {
    var name = $("#searchInput_SM").val();

    $.ajax({
        url: host + "/searchSailByName",
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
            showEmpMesage_SM(datare);
        },
        error: function() {}
    });
});
//按药品编码查询
$(document).on("click", "#searchbyID_SM", function() {
    var MI = $("#searchInput_SM").val();

    $.ajax({
        url: host + "/searchSailByMI",
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
            showEmpMesage_SM(datare);
        },
        error: function() {}
    });
});

//显示更多充值记录
$(document).on("click", ".recharge_more", function() {
    $("#tbody_data_record").empty();
    var id = $(this).attr("data-delete");
    name = $(this).attr("data-name");
    $.ajax({
        url: host + "/getRechargeRecord",
        data: {

            id: id
        },
        dataType: "json",
        type: "post",
        //加上这句话,允许浏览器向服务器跨域请求时携带cookie
        xhrFields: {
            withCredentials: true
        },
        crossDomain: true,
        success: function(data) {

            $.each(data.re, function(index, item) {

                $("#tbody_data_record").append("<tr id='tr_re" + index + "'></tr>");

                $("#tr_re" + index).append("<td>" + name + "</td>");
                $("#tr_re" + index).append("<td>" + item.userid + "</td>");
                $("#tr_re" + index).append("<td>" + item.recharge_date + "</td>");
                $("#tr_re" + index).append("<td>" + item.money + "元</td>");

            });
            $("#rechargecord_more").modal({
                backdrop: "static"
            });
        },
        error: function() {}
    });
});