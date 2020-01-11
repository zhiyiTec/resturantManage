// 进货管理
var cuPn_PM; //当前页码
var cuBuyerInfo_PM; //当前客户信息
var curBuyerId; //当前客户的id
var cuBuyerName_PM; //当前客户的姓名
var countNumber; //当前用户的积分数量
$(document).on("click", "#PurchaseManage", function() {
    $("#right").empty();
    $("#right").append(" <div id='nav'></div>");
    $("#nav").append(
        " <ol class='breadcrumb p-l-15 '> <li><a href='#' class='' disabled='disabled'>Home</a></li> <li><a href='#'>进退货管理</a></li> <li class='active'>进货管理</li></ol>"
    );
    $("#right").append(" <div id='search' class='p-l-25 '></div>");
    $("#search").append(
        "<form class='navbar-form navbar-left' action='#'><div class='form-group'><input type='text' id='searchInput' placeholder='输入查询内容' class='h-34'/></div> <div class='btn-group'> <button type='button' class='btn btn-default dropdown-toggle' data-toggle='dropdown' aria-haspopup='true' aria-expanded='false'>查询 <span class= 'caret' ></span></button > <ul class='dropdown-menu'><li role='separator' class='divider'></li><li><a href='#' id='searchbyname_PM'>按药名ID查询</a></li></ul ></div> </form>  "
    );
    $("#search").append(
        "<div class='float-r'> <div class='btn-group p-r-30'> <button type='button' class='btn btn-default dropdown-toggle btn-style' data-toggle='dropdown' aria-haspopup='true' aria-expanded='false'>功能 <span class= 'caret' ></span></button > <ul class='dropdown-menu'><li role='separator' class='divider'></li><li><a href='#' id='empDeleBtn_PM'>批量删除</a></li></ul ></div><div>"
    );
    $("#right").append(" <div id='cotent' class='p-l-25 '></div>");
    $("#cotent").append(
        "<table class='table table-striped table-bordered table-hover'> <thead><tr> <th><input type='checkbox' id='check_ALl' /></th><th>进货日期</th><th>药品名</th><th>药品类别</th><th>供应商</th><th>操作</th></tr> <thead><tbody id='tbody_data'></tbody></table>"
    );
    //    < !--用于显示分页信息 -->
    $("#right").append(
        "<div class='row p-l-25'><div class='col-md-6' id='PageInfo'></div><div class='col-md-6' id='PageNav'></div></div>"
    );
    toPage_PM(1);
});

//用于跳转页面
function toPage_PM(pn) {
    cuPn_PM = pn;
    $.ajax({
        url: host + "/getAllPurchaseInfo",
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
            showEmpMesage_PM(emp);
            //获取成功之后要做的第二件事情就是：解析并显示分页信息
            showPageInfo_PM(data);
            //获取成功之后要做的第三件事情就是：解析并显示分页条
            showPageNav_PM(data);
        }
    });
}
//解析显示学生信息
function showEmpMesage_PM(data) {
    //每一次构建之前先把数据清空
    $("#tbody_data").empty();
    //此处的写法应该根据浏览器显示的数据进行编写

    $.each(data, function(index, item) {
        $("#tbody_data").append("<tr id='tr" + index + "'></tr>");
        $("#tr" + index).append(
            "<td><input type='checkbox' class='check_item' data-checkBoxName='" +
            item.medinceName +
            "' data-checkBoxId='" +
            item.purchaseId +
            "'/></td>"
        );
        $("#tr" + index).append("<td>" + item.purchaseTime + "</td>");
        $("#tr" + index).append("<td>" + item.medinceName + "</td>");
        $("#tr" + index).append("<td>" + item.medinceTypeCotent + "</td>");
        $("#tr" + index).append("<td>" + item.productorName + "</td>");
        $("#tr" + index).append("<td id='bu" + index + "'></td>");

        $("#bu" + index).append(
            "<button class='btn btn-danger btn-sm delete_PM' data-delete='" +
            item.purchaseId +
            "' data-name='" +
            item.purchaseId +
            "'>删除</button>"
        );
    });
}
//解析显示分页信息
function showPageInfo_PM(data) {
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
function showPageNav_PM(data) {
    //每一次构建之前先把数据清空
    $("#PageNav").empty();
    $("#PageNav").append(
        "<nav aria-label='Page navigation'><ul class='pagination'></ul></nav>"
    );
    if (data.results.pageNum > 1) {
        var dataPN = 1;
        $(".pagination").append(
            "<li><a href='#' data-pn='" + dataPN + "' class='pn_PM'>首页</a></li>"
        );
    }
    //上一页的值
    var prevoiuePage = data.results.pageNum - 1;
    if (data.results.hasPreviousPage) {
        $(".pagination").append(
            "<li ><a href='#' aria-label='Previous' data-pn='" +
            prevoiuePage +
            "' class='pn_PM'> <span aria-hidden='true' class='span-style'>&laquo;</span></a></li>"
        );
    }
    $.each(data.results.navigatepageNums, function(index, item) {
        if (item == data.results.pageNum) {
            $(".pagination").append(
                "<li class='active pn_PM'><a href='#' class='pn_PM' data-pn='" +
                item +
                "'>" +
                item +
                "</a></li>"
            );
        }
        if (item != data.results.pageNum) {
            $(".pagination").append(
                "<li><a href='#' class='pn_PM' data-pn='" +
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
            "' class='pn_PM'> <span aria-hidden='true' class='span-style'>&raquo;</span></a></li>"
        );
        //末页的值
        var lastPage = data.results.pages;
        var total = data.results.total;
        pageTotal = lastPage;
        pages = lastPage;

        $(".pagination").append(
            "<li ><a href='#' class='pn_PM' data-pn='" + lastPage + "'>末页</a></li>"
        );
    }
}
//用于设置翻页的点击事件
$(document).on("click", ".pn_PM", function() {
    var pageNumber = $(this).attr("data-pn");
    cuPn_PM = pageNumber;
    toPage_PM(pageNumber);
});
//用于给动态产生的删除按钮添加点击事件
$(document).on("click", ".delete_PM", function() {
    var emp_id = $(this).attr("data-delete");
    var emp_Name = $(this).attr("data-name");
    var re = confirm("确认要删除【" + emp_Name + "】这条进货信息");
    if (!re) {
        return false;
    }
    $.ajax({
        url: host + "/deletePurchase",
        data: {
            pI: emp_id
        },
        dataType: "json",
        type: "post",
        //加上这句话,允许浏览器向服务器跨域请求时携带cookie
        xhrFields: {
            withCredentials: true
        },
        crossDomain: true,
        success: function(data) {
            alert("删除成功");

            toPage_PM(cuPn_PM);
        },
        error: function() {
            alert("删除失败，请刷新后重试");
        }
    });
});

//实现删除多个员工的按钮功能
$(document).on("click", "#empDeleBtn_PM", function() {
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
        "确认删除【" + emp_nameAsSelectedSub + "】这" + count + "个进货信息吗"
    );
    if (con == false) {
        return false;
    }

    $.ajax({
        url: host + "/deletePurchases",
        data: "id=" + emp_idAsSelected,
        type: "post",
        success: function(data) {
            alert("删除成功");

            //跳转到当前页面
            toPage_PM(cuPn_PM);
        }
    });
});

//按药品名查询
$(document).on("click", "#searchbyname_PM", function() {
    var name = $("#searchInput").val();

    $.ajax({
        url: host + "/searchPurchaseByName",
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
            showEmpMesage_PM(datare);
        },
        error: function() {}
    });
});