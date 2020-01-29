// 奖品管理
var cu_Pn_PrizeManage; //当前页码
var cuBuyerInfo_IM; //当前客户信息
var cu_prize_id; //当前奖品的id
$(document).on("click", "#IM", function() {
    $("#right").empty();
    $("#right").append(" <div id='nav'></div>");
    $("#nav").append(
        " <ol class='breadcrumb p-l-15 '> <li><a href='#' class='' disabled='disabled'>Home</a></li> <li><a href='#'>奖品信息管理</a></li> </ol>"
    );
    $("#right").append(" <div id='search' class='p-l-25 '></div>");
    $("#search").append(
        "<form class='navbar-form navbar-left' action='#'><div class='form-group'><input type='text' id='searchInput_IM' placeholder='输入奖品名称' class='h-34'/></div> <div class='btn-group'> <button type='button' class='btn btn-default'  id='search_prizemanage'>查询 <span class= 'glyphicon glyphicon-search' ></span></button > </div> </form>  "
    );
    $("#search").append(
        "<div class='float-r'> <div class='btn-group p-r-30'> <button type='button' class='btn btn-default dropdown-toggle btn-style' data-toggle='dropdown' aria-haspopup='true' aria-expanded='false'>功能 <span class= 'caret' ></span></button > <ul class='dropdown-menu'><li><a href='#' id='addPrize'>添加奖品</a></li><li><a href='#' id='empDeleBtn_prinzeManage'>批量删除</a></li></ul ></div><div>"
    );
    $("#right").append(" <div id='cotent' class='p-l-25 '></div>");
    $("#cotent").append(
        "<table class='table table-striped table-bordered table-hover'> <thead><tr> <th><input type='checkbox' id='check_ALl' /></th><th>奖品名称</th><th>所需积分</th><th>操作</th></tr> </thead><tbody id='tbody_data'></tbody></table>"
    );
    //    < !--用于显示分页信息 -->
    $("#right").append(
        "<div class='row p-l-25'><div class='col-md-6' id='PageInfo'></div><div class='col-md-6' id='PageNav'></div></div>"
    );
    toPage_IM(1);
});
//用于跳转页面
function toPage_IM(pn) {
    cu_Pn_PrizeManage = pn;
    $.ajax({
        url: host + "/getAllPrize",
        data: {
            pn: pn
        },
        dataType: "json",
        type: "get",
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
            item.award +
            "' data-checkBoxId='" +
            item.id +
            "'/></td>"
        );
        $("#tr" + index).append("<td>" + item.award + "</td>");
        $("#tr" + index).append("<td>" + item.number + "</td>");
        $("#tr" + index).append("<td id='bu" + index + "'></td>");
        $("#bu" + index).append(
            "<button class='btn btn-primary btn-sm edit_prizemanage' data-edit='" +
            item.id +
            "'>编辑</button>"
        );
        $("#bu" + index).append("  ");

        $("#bu" + index).append(
            "<button class='btn btn-danger btn-sm delete_prizemanage' data-delete='" +
            item.id +
            "' data-name='" +
            item.award +
            "'>删除</button>"
        );
    });
}
//解析显示分页信息
function showPageInfo_IM(data) {
    cu_Pn_PrizeManage = data.results.pageNum;
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

    cu_Pn_PrizeManage = pageNumber;

    toPage_IM(pageNumber);
});
//用于奖品编辑功能
$(document).on("click", ".edit_prizemanage", function() {
    //1.查出学生信息加入对话框
    var id = $(this).attr("data-edit");
    cu_prize_id = id;
    $.ajax({
        url: host + "/getPrizeInfoById",
        data: {
            id: id
        },
        dataType: "json",
        type: "get",
        //加上这句话,允许浏览器向服务器跨域请求时携带cookie
        xhrFields: {
            withCredentials: true
        },
        crossDomain: true,
        success: function(data) {

            var prize = data.prize;

            $("#prizemanage_edit_name").val(prize.award);
            $("#prizemanage_edit_jifen").val(prize.number);

        },
        error: function() {
            alert("您的身份信息验证失败请重新登录")
        }
    });



    $("#prizemanage_edit").modal({
        backdrop: "static"
    });
});
//实现修改的功能
$(document).on("click", "#prizemanage_edit_update", function() {
    //1.查出学生信息加入对话框
    var id = cu_prize_id;
    var name = $("#prizemanage_edit_name").val();
    var number = $("#prizemanage_edit_jifen").val();
    $.ajax({
        url: host + "/updatePrizeInfo",
        data: {
            id: id,
            name: name,
            number: number
        },
        dataType: "json",
        type: "get",
        //加上这句话,允许浏览器向服务器跨域请求时携带cookie
        xhrFields: {
            withCredentials: true
        },
        crossDomain: true,
        success: function(data) {

            var re = data.re;
            if (re == 0) {
                alert("更新失败");
            } else {
                alert("更新成功");
                $("#prizemanage_edit").modal("hide");
                toPage_IM(cu_Pn_PrizeManage);
            }


        },
        error: function() {
            alert("更新失败")
        }
    });




});
// 实现删除功能
$(document).on("click", ".delete_prizemanage", function() {
    var emp_id = $(this).attr("data-delete");
    var emp_Name = $(this).attr("data-name");
    var re = confirm("确认要删除【" + emp_Name + "】此奖品的信息吗？");
    if (!re) {
        return false;
    }
    $.ajax({
        url: host + "/deletePrize",
        data: {
            id: emp_id
        },
        dataType: "json",
        type: "get",
        //加上这句话,允许浏览器向服务器跨域请求时携带cookie
        xhrFields: {
            withCredentials: true
        },
        crossDomain: true,
        success: function(data) {
            if (data.re == 0) {
                alert("删除失败");
            } else {

                alert("删除成功");
            }

            toPage_IM(cu_Pn_PrizeManage);
        },
        error: function() {
            alert("删除失败，请刷新后重试");
        }
    });
});
// 奖品的添加功能

$(document).on("click", "#addPrize", function() {

    $("#prizemanage_add").modal({
        backdrop: "static"
    });
});
$(document).on("click", "#prizemanage_add_update", function() {

    var name = $("#prizemanage_add_name").val();
    var number = $("#prizemanage_add_jifen").val();

    $.ajax({
        url: host + "/addPrizeInfo",
        data: {
            name: name,
            number: number
        },
        dataType: "json",
        type: "get",
        //加上这句话,允许浏览器向服务器跨域请求时携带cookie
        xhrFields: {
            withCredentials: true
        },
        crossDomain: true,
        success: function(data) {
            if (data.re == 0) {
                alert("添加失败");
            } else {
                $("#prizemanage_add").modal("hide");
                alert("添加成功");
                $("#prizemanage_add_name").val("");
                $("#prizemanage_add_jifen").val("");
            }

            toPage_IM(cu_Pn_PrizeManage);
        },
        error: function() {
            alert("添加失败，请刷新后重试");
        }
    });
});

//实现查询功能

//按药品名查询
$(document).on("click", "#searchInputByname_IM", function() {
    var name = $("#searchInput_IM").val();

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
//批量删除
$(document).on("click", "#empDeleBtn_prinzeManage", function() {
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
        "确认删除【" + emp_nameAsSelectedSub + "】这" + count + "个奖品的信息吗"
    );
    if (con == false) {
        return false;
    }

    $.ajax({
        url: host + "/deletePrizes",
        data: {
            ids: emp_idAsSelected
        },

        type: "get",
        success: function(data) {
            if (data.re == count) {
                alert("删除成功");
                //跳转到当前页面
                toPage_IM(cu_Pn_PrizeManage);
            } else {

                alert("删除失败");
            }



        }
    });
});
//查询
$(document).on("click", "#search_prizemanage", function() {
    var content = $("#searchInput_IM").val();
    console.log(content)
    $.ajax({
        url: host + "/searchPrizeByName",
        data: {
            content: content,

        },

        type: "get",
        success: function(data) {
            var datare = data.results.list;
            console.log(datare)
            showEmpMesage_IM(datare);

        }
    });
});