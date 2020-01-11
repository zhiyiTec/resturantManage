// 客户信息管理
var cuPn_msm; //当前页码
var cuBuyerInfo_msm; //当前客户信息
var curBuyerId; //当前客户的id
var cuBuyerName_msm; //当前客户的姓名
var countNumber; //当前用户的积分数量
$(document).on("click", "#clientMa", function() {
    $("#right").empty();
    $("#right").append(" <div id='nav'></div>");
    $("#nav").append(
        " <ol class='breadcrumb p-l-15 '> <li><a href='#' class='' disabled='disabled'>Home</a></li> <li><a href='#'>客户信息管理</a></li> </ol>"
    );
    $("#right").append(" <div id='search' class='p-l-25 '></div>");
    $("#search").append(
        "<form class='navbar-form navbar-left' action='#'><div class='form-group'><input type='text' id='searchInput' placeholder='输入查询内容' class='h-34'/></div> <div class='btn-group'> <button type='button' class='btn btn-default dropdown-toggle' data-toggle='dropdown' aria-haspopup='true' aria-expanded='false'>查询 <span class= 'caret' ></span></button > <ul class='dropdown-menu'><li role='separator' class='divider'></li><li><a href='#' id='searchbyname_msm'>按姓名查询</a></li><li role='separator' class='divider'></li><li><a href='#' id='searchbyphone_msm'>按电话查询</a></li></ul ></div> </form>  "
    );
    $("#search").append(
        "<div class='float-r'> <div class='btn-group p-r-30'> <button type='button' class='btn btn-default dropdown-toggle btn-style' data-toggle='dropdown' aria-haspopup='true' aria-expanded='false'>功能 <span class= 'caret' ></span></button > <ul class='dropdown-menu'><li role='separator' class='divider'></li><li><a href='#' id='empDeleBtn_msm'>批量删除</a></li><li role='separator' class='divider'></li><li><a href='#' id='modifyMP'>修改会员卡密码</a></li><li role='separator' class='divider'></li><li><a href='#' id='addMember_msm'>添加会员</a></li></ul ></div><div>"
    );
    $("#right").append(" <div id='cotent' class='p-l-25 '></div>");
    $("#cotent").append(
        "<table class='table table-striped table-bordered table-hover'> <thead><tr> <th><input type='checkbox' id='check_ALl' /></th><th>用户名</th><th>手机号</th><th>积分数额</th><th>上一次消费金额</th><th>在本店消费总金额</th><th>操作</th></tr> <thead><tbody id='tbody_data'></tbody></table>"
    );
    //    < !--用于显示分页信息 -->
    $("#right").append(
        "<div class='row p-l-25'><div class='col-md-6' id='PageInfo'></div><div class='col-md-6' id='PageNav'></div></div>"
    );
    toPage_msm(1);
});

//用于跳转页面
function toPage_msm(pn) {
    cuPn_msm = pn;
    $.ajax({
        url: host + "/getAllBuyer",
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
            showEmpMesage_msm(emp);
            //获取成功之后要做的第二件事情就是：解析并显示分页信息
            showPageInfo_msm(data);
            //获取成功之后要做的第三件事情就是：解析并显示分页条
            showPageNav_msm(data);
        }
    });
}
//解析显示学生信息
function showEmpMesage_msm(data) {
    //每一次构建之前先把数据清空
    $("#tbody_data").empty();
    //此处的写法应该根据浏览器显示的数据进行编写

    $.each(data, function(index, item) {

        var cumoney, summoney;
        if (item.cumoney == null) cumoney = 0;
        else cumoney = item.cumoney;
        if (item.sunmoney == null) summoney = 0;
        else summoney = item.sunmoney;
        $("#tbody_data").append("<tr id='tr" + index + "'></tr>");
        $("#tr" + index).append(
            "<td><input type='checkbox' class='check_item' data-checkBoxName='" +
            item.buyerName +
            "' data-checkBoxId='" +
            item.userId +
            "'/></td>"
        );
        $("#tr" + index).append("<td>" + item.buyerName + "</td>");
        $("#tr" + index).append("<td>" + item.userId + "</td>");
        $("#tr" + index).append("<td>" + item.integral + "</td>");
        $("#tr" + index).append("<td>" + cumoney + "元</td>");
        $("#tr" + index).append("<td>" + summoney + "元</td>");
        $("#tr" + index).append("<td id='bu" + index + "'></td>");
        $("#bu" + index).append(
            "<button class='btn btn-primary btn-sm edit_msm' data-edit='" +
            item.userId +
            "'>编辑</button>"
        );
        $("#bu" + index).append("  ");

        $("#bu" + index).append(
            "<button class='btn btn-danger btn-sm delete_msm' data-delete='" +
            item.userId +
            "' data-name='" +
            item.buyerName +
            "'>删除</button>"
        );
        $("#bu" + index).append("  ");
        $("#bu" + index).append(
            "<button class='btn btn-success btn-sm award_msm' data-delete='" +
            item.userId +
            "' data-name='" +
            item.buyerName +
            "' >积分兑换</button>"
        );
    });
}
//解析显示分页信息
function showPageInfo_msm(data) {
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
function showPageNav_msm(data) {
    //每一次构建之前先把数据清空
    $("#PageNav").empty();
    $("#PageNav").append(
        "<nav aria-label='Page navigation'><ul class='pagination'></ul></nav>"
    );
    if (data.results.pageNum > 1) {
        var dataPN = 1;
        $(".pagination").append(
            "<li><a href='#' data-pn='" + dataPN + "' class='pn_msm'>首页</a></li>"
        );
    }
    //上一页的值
    var prevoiuePage = data.results.pageNum - 1;
    if (data.results.hasPreviousPage) {
        $(".pagination").append(
            "<li ><a href='#' aria-label='Previous' data-pn='" +
            prevoiuePage +
            "' class='pn_msm'> <span aria-hidden='true' class='span-style'>&laquo;</span></a></li>"
        );
    }
    $.each(data.results.navigatepageNums, function(index, item) {
        if (item == data.results.pageNum) {
            $(".pagination").append(
                "<li class='active pn_msm'><a href='#' class='pn_msm' data-pn='" +
                item +
                "'>" +
                item +
                "</a></li>"
            );
        }
        if (item != data.results.pageNum) {
            $(".pagination").append(
                "<li><a href='#' class='pn_msm' data-pn='" +
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
            "' class='pn_msm'> <span aria-hidden='true' class='span-style'>&raquo;</span></a></li>"
        );
        //末页的值
        var lastPage = data.results.pages;
        var total = data.results.total;
        pageTotal = lastPage;
        pages = lastPage;

        $(".pagination").append(
            "<li ><a href='#' class='pn_msm' data-pn='" + lastPage + "'>末页</a></li>"
        );
    }
}
//用于设置翻页的点击事件
$(document).on("click", ".pn_msm", function() {
    var pageNumber = $(this).attr("data-pn");
    cuPn_msm = pageNumber;
    toPage_msm(pageNumber);
});
//编辑用户信息
$(document).on("click", ".edit_msm", function() {
    //1.查出学生信息加入对话框
    var productorId = $(this).attr("data-edit");

    $("#cout_bI").empty();
    curBuyerId = productorId;
    getCurrentInfo_msm(productorId);

    $("#empAddModal_bI").modal({
        backdrop: "static"
    });
});

function getCurrentInfo_msm(getUserOnlyInfo) {
    $.ajax({
        url: host + "/getBuyerById",
        data: {
            bI: getUserOnlyInfo
        },
        dataType: "json",
        type: "post",
        //加上这句话,允许浏览器向服务器跨域请求时携带cookie
        xhrFields: {
            withCredentials: true
        },
        crossDomain: true,
        success: function(data) {
            cuBuyerInfo_msm = data.buyer;

            $("#MTNA_bI").val(data.buyer.buyerName);
            $("#mC_bI").val(data.buyer.userId);

        }
    });
}
$(document).on("click", "#AddM_bI", function() {
    var name = $("#MTNA_bI").val();
    var cout = $("#cout_bI").val();

    $.ajax({
        url: host + "/updateUser",
        data: {
            id: curBuyerId,
            name: name,
            cout: cout
        },
        dataType: "json",
        type: "post",
        //加上这句话,允许浏览器向服务器跨域请求时携带cookie
        xhrFields: {
            withCredentials: true
        },
        crossDomain: true,
        success: function(data) {
            alert("更新成功");
            $("#empAddModal_bI").modal("hide");
            toPage_msm(cuPn_msm);
        },
        error: function() {
            alert("更新失败，请刷新后重试");
        }
    });
});
//用于给动态产生的删除按钮添加点击事件
$(document).on("click", ".delete_msm", function() {
    var emp_id = $(this).attr("data-delete");
    var emp_Name = $(this).attr("data-name");
    var re = confirm("确认要删除【" + emp_Name + "】此客人的信息吗？");
    if (!re) {
        return false;
    }
    $.ajax({
        url: host + "/deleteBuyer",
        data: {
            bI: emp_id
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

            toPage_msm(cuPn_msm);
        },
        error: function() {
            alert("删除失败，请刷新后重试");
        }
    });
});
//实现删除多个员工的按钮功能
$(document).on("click", "#empDeleBtn_msm", function() {
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
        url: host + "/deleteByers",
        data: "id=" + emp_idAsSelected,
        type: "post",
        success: function(data) {
            alert("删除成功");

            //跳转到当前页面
            toPage_msm(cuPn_msm);
        }
    });
});

//实现修改会员卡密码的功能
$(document).on("click", "#modifyMP", function() {


    $("#modifyPModal_bI").modal({
        backdrop: "static"
    });
});
$(document).on("click", "#modifyPCon_bI", function() {
    var phone = $("#modifyPP_bI").val();
    var newPass = $("#modifyPNP_bI").val();
    $.ajax({
        url: host + "/judgePM",
        data: {
            phone: phone
        },
        dataType: "json",
        type: "post",
        //加上这句话,允许浏览器向服务器跨域请求时携带cookie
        xhrFields: {
            withCredentials: true
        },
        crossDomain: true,
        success: function(data) {
            if (data.status == 0) {
                $.ajax({
                    url: host + "/modifyMP",
                    data: {
                        phone: phone,
                        newPass: newPass
                    },
                    dataType: "json",
                    type: "post",
                    //加上这句话,允许浏览器向服务器跨域请求时携带cookie
                    xhrFields: {
                        withCredentials: true
                    },
                    crossDomain: true,
                    success: function(data) {
                        $("#modifyPP_bI").val("");
                        $("#modifyPNP_bI").val("");
                        alert("修改成功");
                        $("#modifyPModal_bI").modal("hide");
                        toPage_msm(cuPn_msm);
                    },
                    error: function() {
                        alert("修改失败，请刷新后重试");
                    }
                });
            } else {
                $('#modifyPP_bI').popover('show')
            }
        },
        error: function() {
            alert("修改失败，请刷新后重试");
        }
    });
});
// 添加会员
$(document).on("click", "#addMember_msm", function() {
    $("#addModal_bI").modal({
        backdrop: "static"
    });
});

$(document).on("click", "#addMember_bI", function() {
    var phone = $("#addPP_bI").val();

    var name = $("#addN_bI").val();
    var IC = $("#addIC_bI").val();
    var pas = $("#addP_bI").val();
    var conPass = $("#con_bI").val();
    $.ajax({
        url: host + "/judgeBuyerIsRegit",
        data: {
            phone: phone
        },
        dataType: "json",
        type: "post",
        //加上这句话,允许浏览器向服务器跨域请求时携带cookie
        xhrFields: {
            withCredentials: true
        },
        crossDomain: true,
        success: function(data) {
            console.log(data)
            if (data.status == 0) {
                if (pas != conPass) {
                    $('#con_bI').popover('show')
                } else {
                    $.ajax({
                        url: host + "/addUserAndBuyer",
                        data: {
                            phone: phone,
                            name: name,
                            IC: IC,
                            pas: pas
                        },
                        dataType: "json",
                        type: "post",
                        //加上这句话,允许浏览器向服务器跨域请求时携带cookie
                        xhrFields: {
                            withCredentials: true
                        },
                        crossDomain: true,
                        success: function(data) {
                            alert("添加成功");
                            $("#addModal_bI").modal("hide");
                            toPage_msm(cuPn_msm);
                        },
                        error: function() {
                            alert("添加失败，请刷新后重试");
                        }
                    });
                }
            } else {
                $('#addPP_bI').popover('show')
            }
        },
        error: function() {
            alert("修改失败，请刷新后重试");
        }
    });
});
//实现查询功能
//按姓名查询
//按供应商查询
$(document).on("click", "#searchbyname_msm", function() {
    var name = $("#searchInput").val();

    $.ajax({
        url: host + "/seachBuyerByName",
        data: {
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
            showEmpMesage_msm(datare);
        },
        error: function() {}
    });
});
//按电话查询
$(document).on("click", "#searchbyphone_msm", function() {
    var phone = $("#searchInput").val();
    $.ajax({
        url: host + "/seachBuyerByPhone",
        data: {
            phone: phone
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
            showEmpMesage_msm(datare);
        },
        error: function() {}
    });
});
//积分兑换
//编辑用户信息
$(document).on("click", ".award_msm", function() {
    //1.查出学生信息加入对话框
    var id = $(this).attr("data-delete");
    var name = $(this).attr("data-name");

    curBuyerId = id;
    cuBuyerName_msm = name;
    fillAwardList(id);
    getCurrentInfoAward_msm(id);

    $("#addModalAward_bI").modal({
        backdrop: "static"
    });
});

$(document).on("click", "#award_bI", function() {
    var award = $("#awardPrize_bi").val();
    $.ajax({
        url: host + "/getAwardNumberByName",
        data: {
            award: award
        },
        dataType: "json",
        type: "post",
        //加上这句话,允许浏览器向服务器跨域请求时携带cookie
        xhrFields: {
            withCredentials: true
        },
        crossDomain: true,
        success: function(data) {
            var number = data.award.number;
            if (countNumber >= number) {
                countNumber -= number;
                $.ajax({
                    url: host + "/AwardSuc",
                    data: {
                        id: curBuyerId,
                        number: countNumber
                    },
                    dataType: "json",
                    type: "post",
                    //加上这句话,允许浏览器向服务器跨域请求时携带cookie
                    xhrFields: {
                        withCredentials: true
                    },
                    crossDomain: true,
                    success: function(data) {
                        alert("兑换成功");
                        $("#awardNu_bI").val(countNumber);
                    },
                    error: function() {
                        alert("积分加载异常");
                    }
                });
            } else {
                alert("积分数量不足");
            }
        }
    });
});

function getCurrentInfoAward_msm(getUserOnlyInfo) {
    $.ajax({
        url: host + "/getBuyerById",
        data: {
            bI: getUserOnlyInfo
        },
        dataType: "json",
        type: "post",
        //加上这句话,允许浏览器向服务器跨域请求时携带cookie
        xhrFields: {
            withCredentials: true
        },
        crossDomain: true,
        success: function(data) {
            cuBuyerInfo_msm = data.buyer;
            countNumber = data.buyer.integral;
            $("#awardN_bI").val(data.buyer.buyerName);
            $("#awardMC_bI").val(data.buyer.userId);
            $("#awardNu_bI").val(data.buyer.integral);
        }
    });
}
//填充奖品列表
function fillAwardList(id) {
    $.ajax({
        url: host + "/getAward",
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
            $("#awardPrize_bi").empty();
            // if (data.award.award == null) {
            //     console.log("ssssssss")
            //     $("#awardPrize_bi").append(
            //         "<option>" + "没有可兑换的商品" + "</option>"
            //     );
            // } else {
            $.each(data.award, function(index, item) {
                $("#awardPrize_bi").append(
                    "<option data-deptid=" + item.award + ">" + item.award + "</option>"
                );
            });
            // }

        }
    });
}