var c, t, time, tempUserPhone, alltime = 600,
    tempPgid, tempPlayer2, tempTegid, tepGanetype, tempReceiver, tempQuestionType, tempPlayer2Name;

var tempQuestionArray = new Array(); //试卷列表
var tempNOQ = 1;
var tempAnswered = new Map(); //用于存取题号，以及对应的答案

var PracticeComponent = function(info, urls) {
    this.urls = urls;
    this.userPhone = info.userPhone;
    tempUserPhone = info.userPhone;
    this.playerName = "";
    this.deptName = "";
    this.NOQ = 1; //页面上对应的题号
    this.ganetype = 1;
    this.questionArray = new Array(); //试卷列表
    this.pgid = 0;
    this.alltime = 600;
    this.answered = new Map(); //用于存取题号，以及对应的答案
    this.player2 = 0;
    this.player2Name = "";
    this.tegid = "";
    this.receive = "";
    this.a = true;
    this.sig = true;
    this.questionType;
    this.chanllengData;




    this.initPractice();



    this.next();
    this.pre();
    this.submit();
    this.backToMain();
    this.lookMoreInfo();
    this.challenge();
    this.fillCheckInClick();
    this.Leaderboard();
    this.qustionInfo();

}
PracticeComponent.prototype = {
    constructor: PracticeComponent,
    initPractice: function() {
        var _this = this;
        $.ajax({
            url: this.urls.getSessionId,
            data: "getSessionIdInfo=" + _this.userPhone,
            type: "get",
            //加上这句话,允许浏览器向服务器跨域请求时携带cookie
            xhrFields: {
                withCredentials: true
            },
            crossDomain: true,
            success: function(data) {
                if (!data.status) {
                    alert("请先登录，谢谢!!!");
                    window.location.href = 'index.html';

                } else {
                    _this.getUser();
                }

            }
        })
    },

    getUser: function() {
        var _this = this;
        $.ajax({
            url: _this.urls.getUser,
            data: "user=" + _this.userPhone,
            type: "post",

            success: function(data) {
                _this.playerName = data.userName;
                _this.deptName = data.deptName;
                $("#userName").text(data.userName);
                $("#deptName").text(data.deptName);
                _this.confirmPlayer2();
            },
            error: function() {
                alert("非法登录");
                window.location.href = 'index.html';
                throw "登录异常";
            }
        });
    },
    //用于加载是否有挑战邀请
    confirmPlayer2: function() {
        var _this = this;
        $.ajax({
            url: _this.urls.confirmPlayer2,
            data: "confirmPlayer2Info=" + _this.userPhone,
            type: "post",
            success: function(data) {
                _this.chanllengData = data;
                if (data.challenged) {
                    var r = confirm("您有挑战邀请，是否查看？？？");
                    if (r) {
                        _this.fillChanllengData(_this.chanllengData);
                    }
                }
            },
            error: function() {
                alert("获取挑战信息失败,请重新登录");
                window.location.href = 'index.html';
            }
        });
    },
    //用于填充挑战请求模态框是否有挑战邀请
    fillChanllengData: function(data) {
        var _this = this;
        $("#getChanllengeModel_tb").empty();
        $.each(data.result, function(index, value) {
            $("#getChanllengeModel_tb").append("<tr id='getChanllengeModel_tb_tr" + index + "'></tr>");
            $("#getChanllengeModel_tb_tr" + index).append("<td>" + value.player1Name + '/' + value.player1 + "</td>");
            $("#getChanllengeModel_tb_tr" + index).append("<td>" + value.player1dept + "</td>");
            if (value.ganetype == 0) {
                $("#getChanllengeModel_tb_tr" + index).append("<td>个人赛</td>");
            } else {
                $("#getChanllengeModel_tb_tr" + index).append("<td>团体赛</td>");
            }
            $("#getChanllengeModel_tb_tr" + index).append("<td><a class='btn btn-default getChanllengePlayer1 challenge' href='#' role='button' data-player1='" + value.player1 + "' data-pgid='" + value.pgid + "' data-gantype='" + value.ganetype + "' data-receive='receiver' data-player1name='" + value.player1Name + "'>接受挑战</a></td>");

        })

        //此处弹出模态框
        $('#getChanllengeModel').modal({
            backdrop: "static"
        })

    },
    //用于个人赛开始按钮
    personalbegin: function() {
        var _this = this;
        // $(document).on("touchstart", "#personalbegin", function(event) {
        // event.preventDefault();
        // event.stopPropagation();
        _this.NOQ = 1;
        tempNOQ = 1;
        c = _this.alltime;
        _this.ganetype = $(this).attr("data-type");
        tepGanetype = $(this).attr("data-type");
        $.ajax({
            url: _this.urls.getUserInfo,
            data: "phoneNumber=" + _this.userPhone,
            type: "get",
            success: function(data) {
                $("#userInfo").empty();
                $.each(data.userInfo, function(index, item) {
                    $("#userInfo").append("<tr id='tr" + index + "'></tr>");
                    $("#tr" + index).append("<td>" + item.userName + '/' + item.phoneNumber + "</td>");
                    $("#tr" + index).append("<td>" + item.deptname + "</td>");
                    $("#tr" + index).append("<td><button class='btn btn-default challenge' data-info=" + item.phoneNumber + " type='button' data-gantype='0' data-player2Name='" + item.userName + "'>点击挑战对方</button></td>");
                });
                //此处弹出模态框
                $('#selectModel').modal({
                    backdrop: "static"
                })
            },
            error: function() {
                alert("获取对手信息失败,将重新登录");
                window.location.href = 'index.html';
            }
        });
        // })
    },

    //格式化当前时间，变为yyyy-mm-dd
    getNowDate: function() {
        // 获取当前日期
        var date = new Date();
        // 获取当前月份
        var nowMonth = date.getMonth() + 1;
        // 获取当前是几号
        var strDate = date.getDate();
        // 添加分隔符“-”
        var seperator = "-";
        // 对月份进行处理，1-9月在前面添加一个“0”
        if (nowMonth >= 1 && nowMonth <= 9) {
            nowMonth = "0" + nowMonth;
        }
        // 对月份进行处理，1-9号在前面添加一个“0”
        if (strDate >= 0 && strDate <= 9) {
            strDate = "0" + strDate;
        }
        // 最后拼接字符串，得到一个格式为(yyyy-MM-dd)的日期
        var nowDate = date.getFullYear() + seperator + nowMonth + seperator + strDate;
        return nowDate;
    },

    //发送ajax请求将比赛信息保存到personalGame表中
    sendAjaxToSaveGameInfo: function(saveChallengeInfo) {
        var _this = this;
        $.ajax({
            url: _this.urls.saveChallenge,
            type: "post",
            data: "saveChallengeInfo=" + saveChallengeInfo,
            success: function(data) {

                _this.pgid = data.pgid;
                tempPgid = data.pgid;
                _this.sendAjaxGetQuestion();

            },
            error: function() {
                alert("存储答题信息失败，请重新登录后再次尝试");
                window.location.href = 'index.html';
            }
        });
    },

    //用于创建集成框架
    createFram: function() {
        $("#cotent").append("<div class='backGroundImg6' ><p style='font-size: 18px;color:#333333;padding-left: 17px;padding-top: 7px;'>距离结束还剩：<strong><span id='time' style='color:#b33030'></span></strong></p> </div>");
        $("#cotent").append(" <div class='backGroundImg7' id='backGroundImg7'></div>")
        $("#backGroundImg7").append("<div id='Question' style='color:#333333;font-size: 20px;padding-left: 39px;padding-right:39px;'></div>")
        $("#backGroundImg7").append("<div id='select' style='color:#333333;font-size: 20px;padding-top: 64px;padding-right:14%;padding-left:2%'></div>");
        $("#select").append("<ul id='ul_se'></ul>");
        $("#backGroundImg7").append(" <div id='btn' style='margin-top:25%'  class=''></div>");
    },
    //用于实现下一题的功能
    next: function() {
        var _this = this;
        $(document).on("click", "#next", function() {
            var result = getCurrentAnswer();

            _this.answered.set(_this.NOQ, result);
            tempAnswered.set(_this.NOQ, result);
            _this.NOQ += 1;
            tempNOQ += 1;

            if (tempNOQ <= 9) {

                addToCotent(tempQuestionArray);

            } else {
                $("#liNe").empty();



                _this.a = false;
                judgeDicC();
                if (_this.receive == 'receiver') {

                    $("#liNe").append("	<a href='#' data-pre='1' id='submit_receiver' class='btn ' role='button' style='font-size: 14px;background-color: #f18e23;color:white;padding:0.5em 2em 0.5em 2em;'>提交答案</a>");
                    tempNOQ = 10;
                } else {

                    $("#liNe").append("	<a href='#' data-pre='1' id='submit' class='btn ' role='button' style='font-size: 14px;background-color: #f18e23;color:white;padding:0.5em 2em 0.5em 2em;'>提交答案</a>");
                    tempNOQ = 10;

                }

            }

            if (tempAnswered.get(tempNOQ) != null) {
                _this.fillCheckPre(tempAnswered.get(tempNOQ));
            }

        })

    },
    //用于实现上一题的功能
    pre: function() {
        var _this = this;
        $(document).on("click", "#pre", function() {
            $("#liNe").empty()
            $("#liNe").append("<a href='#' data-pre='1' id='next' class='btn ' role='button' style='font-size: 14px;background-color: #f18e23;color:white;padding:0.5em 2em 0.5em 2em;'>下一题</a>");
            _this.a = true;
            // sendAn(); //先将本题信息加入缓存
            var result = getCurrentAnswer();
            _this.answered.set(_this.NOQ, result);
            tempAnswered.set(tempNOQ, result);

            _this.NOQ -= 1;
            tempNOQ -= 1;
            if (tempNOQ >= 9) {
                tempNOQ = 9;
            }
            if (tempNOQ <= 0) {
                _this.NOQ += 1;
                tempNOQ = 1;
                _this.Toast('已经是第一题啦', 1000);
            } else {

                addToCotent(tempQuestionArray);
                _this.fillCheckPre(tempAnswered.get(tempNOQ));
            }

        })


    },
    //用于实现已做的答案填充
    fillCheckPre: function(data) {
        var _this = this;
        if (tempQuestionType == 2) {
            _this.fillCheck(data, "checkbox")
        } else {
            _this.fillCheck(data, "radio")
        }


    },
    //用于填充check
    fillCheck: function(data, el) {
        var _this = this;
        if (data.indexOf(",") == -1) {
            $('input:' + el).each(function() {

                if ($(this).val() == data) {
                    $(this).prop("checked", true)
                }
            })
        } else {
            var re = new Array();
            re = data.split(",");


            $.each(re, function(index, item) {
                $('input:' + el).each(function() {
                    if ($(this).val() == item) {
                        $(this).prop("checked", true)
                    }
                })
            })
        }


    },
    //通过点击文字填充对应的checkBox
    fillCheckInClick: function() {
        var _this = this;
        $(document).on("touchstart", ".optionSpan", function() {
            var item = $(this).attr("data-item");
            var _thisA = $(this);

            if (_this.a) {
                if (tempQuestionType == 2) {
                    if (_thisA.attr("data-sig") == 'no') {
                        $('input:checkbox').each(function(index, value) {
                            if ($(this).val() == item) {
                                $(this).prop("checked", true);
                            }

                        })
                        _this.setStatus("checkbox", _thisA, item);
                    } else {
                        $('input:checkbox').each(function(index, value) {
                            if ($(this).val() == item) {
                                $(this).prop("checked", false);
                            }

                        })
                        _this.setStatus("checkbox", _thisA, item);
                    }

                } else {
                    if (_thisA.attr("data-sig") == 'no') {
                        $('input:radio').each(function(index, value) {
                            if ($(this).val() == item) {
                                $(this).prop("checked", true);
                            }

                        })
                        _this.setStatus("radio", _thisA, item);
                    } else {
                        $('input:radio').each(function(index, value) {
                            if ($(this).val() == item) {
                                $(this).prop("checked", false);
                            }

                        })
                        _this.setStatus("radio", _thisA, item);
                    }
                }
            }



        })
    },
    //判断当前复选框是否被选中
    judgeChecked: function(el) {
        var status = new Map();
        $('input:' + el).each(function(index, value) {
            if ($(this).is(':checked')) {
                status.set($(this).val(), true);
            } else {
                status.set($(this).val(), false);
            }
        })
        return status;
    },
    //通过复选框的状态设置信号值
    setStatus: function(el, thisA, val) {
        var _this = this;
        var judMap = _this.judgeChecked(el);
        if (judMap.get(val)) {
            thisA.attr("data-sig", 'yes')
        } else {
            thisA.attr("data-sig", 'no')
        }
    },
    //用于清空数组
    arraySplice: function() {
        var _this = this;
        _this.questionArray.splice(0, _this.questionArray.length); //清空数组
        tempQuestionArray.splice(0, tempQuestionArray.length);
        _this.answered.clear(); //清空数组
        tempAnswered.clear(); //清空数组
    },

    //用于实现提交按钮的功能
    submit: function() {
        var _this = this;
        //用于实现提交功能
        $(document).on("click", "#submit", function() {

            var r = confirm("确认提交？？？");
            if (r == true) {

                time = alltime - c;
                if (time > alltime) {
                    time = alltime;
                }
                if (time <= 0) {
                    time = 1;
                }

                stopCount();
                var result = getCurrentAnswer();
                _this.answered.set(tempNOQ, result);
                tempAnswered.set(tempNOQ, result);
                if (tepGanetype == 2) {
                    sendQu(tempQuestionArray, tempAnswered, tempPgid, tempUserPhone, time, tepGanetype, '空', '空');
                }
                if (tepGanetype == 0) {
                    sendQu(tempQuestionArray, tempAnswered, tempPgid, tempUserPhone, time, tepGanetype, tempPlayer2, '空');
                }
                if (tepGanetype == 1) {
                    sendQu(tempQuestionArray, tempAnswered, tempPgid, tempUserPhone, time, tepGanetype, tempPlayer2, tempPgid);
                }

            }

        })
        //接受挑战的提交按钮
        $(document).on("click", "#submit_receiver", function() {
            var r = confirm("确认提交？？？");

            if (r == true) {

                time = alltime - c;
                if (time > alltime) {
                    time = alltime;
                }
                if (time <= 0) {
                    time = 1;
                }
                stopCount();
                var map = stringfyAnswer(tempQuestionArray, tempAnswered);

                var questionNumber = map.get("questionNumber");
                var value = map.get("value");
                var dealReceiverAnsInfo = questionNumber + "_" + value + "_" + tempPgid + "_" + tempUserPhone + "_" + time + "_" + tepGanetype;
                dealReceiverAns(dealReceiverAnsInfo);

            }

        })
    },

    //用于实现回到主界面的功能
    backToMain: function() {
        var _this = this;
        $(document).on("click", "#backToMain", function() {
            $("#cotent").empty();
            c = alltime;
            $("#cotent").append("<div class='backGroundImg1'><div style='padding-left: 101px;padding-top:217px;color:#B33030;font-size: 16px;position: absolute;'><span id='userName'></span></div><div style='padding-left: 101px;padding-top:250px;color:#B33030;font-size: 13px;position: absolute;opacity: 0.6;'>单位：<span id='deptName'></span></div></div>");
            $("#cotent").append(" <div class='backGroundImg2 sortable-handler' id='' data-type='0' onclick='personalbegin()'><p style='color: #FFFFFF;font-size: 32px;padding-top:32px;padding-left:42px'>个人赛</p></div>");
            $("#cotent").append("<div class='backGroundImg3 sortable-handler'  data-type='1' onclick='teambegin()'><p style='color: #FFFFFF;font-size: 32px;padding-top:32px;padding-left:42px'>团队赛</p></div>");
            $("#cotent").append(" <div style='width: 100%;padding: 0 5%;display:flex;justify-content: space-between;'><div class='backGroundImg4 img sortable-handler' id='exercise' data-type='2'><img src='images/个人练习@2x.png' style='width:100%' /> <p style='color: #FFFFFF;font-size: 20px;position:absolute;top:8px;width:100%;padding-left:44%;'>个人练习</p></div><div class='backGroundImg4 img sortable-handler' id='Leaderboard'><img src='images/排行榜@2x.png' style='width:100%' /><p style='color: #FFFFFF;font-size: 20px;position:absolute;top:8px;width:100%;padding-left:57%;' data-type='2'>排行榜</p></div></div>");
            $("#userName").text(_this.playerName);
            $("#deptName").text(_this.deptName);
            location.reload(); //刷新当前页面
        })
    },
    //用于实现查看更多的功能
    lookMoreInfo: function() {
        var _this = this;
        $(document).on("click", "#lookMoreInfo", function() {
            $("#ScoreInfo").empty();
            $("#timeInfo").empty();
            $("#whoWin").empty();
            $("#getWrongInfoModel_TB").empty();
            $("#timeInfo").text(time + "s");
            var getWhoWinInfo = tempUserPhone + "-" + tempPgid;
            $.ajax({
                url: host + "/hongsong/getWhoWin",
                data: "getWhoWinInfo=" + getWhoWinInfo,
                type: "post",
                success: function(data) {

                    if (tepGanetype != 2) {
                        $("#whoWin").text(data.winResult);
                    }
                    var rate = data.personale.player1Rate * 100 + "%";
                    if (tepGanetype == 2) {
                        $("#whoWin").text("您的正确率为：" + rate);
                    }

                    $("#getWrongInfoModel_userInfo").text("【" + _this.playerName + "】");
                    if (data.Score == null || data.Score == 'null') {
                        data.Score = 0;
                    }
                    $("#ScoreInfo").text(data.Score);
                    $.each(data.result, function(index, item) {

                        $("#getWrongInfoModel_TB").append("<tr id='trIn" + index + "'><tr>");
                        $("#trIn" + index).append("<td>" + item.noq + "</td>");
                        $("#trIn" + index).append("<td>" + item.wrongAnswer + "</td>");
                        $("#trIn" + index).append("<td>" + item.resultAnswer + "</td>");
                    })
                    //此处弹出模态框
                    $('#getWrongInfoModel').modal({
                        backdrop: "static"
                    })

                },
                error: function() {
                    //alert("获取比赛结果失败")
                }
            });

        })
    },

    //答题详情
    qustionInfo: function() {
        var _this = this;
        $(document).on("touchstart", "#qustionInfo", function(event) {
            event.preventDefault();
            var lookBack = tempUserPhone + "-" + tempPgid;
            window.location.href = 'review.html?lookBack=' + lookBack;
            // window.open('review.html?lookBack=' + lookBack, 'newwindow', 'height=400,width=800,top=20%,right=10%,toolbar=no,menubar=no,scrollbars=no,resizable = no, location = no, status = no')

        })
    },
    //排行榜
    Leaderboard: function() {
        var _this = this;
        $(document).on("touchstart", "#Leaderboard1111", function(event) {
            event.preventDefault();
            // var imgElement = this;
            // $(imgElement).on('click', function(event) {
            //     event.preventDefault();
            // });
            _this.Toast('敬请期待', 2000);
        })
    },
    //自制弹窗
    Toast: function(msg, duration) {
        duration = isNaN(duration) ? 3000 : duration;
        var m = document.createElement('div');
        m.innerHTML = "<span style='padding:20%'>" + msg + "</span>";
        m.style.cssText = "width:50%;padding:6%;min-width: 150px;opacity: 0.5;color: rgb(255, 255, 255);line-height: 30px;text-align: center;border-radius: 55px;position: fixed;top: 50%;left: 30%;z-index: 999999;background: rgb(0, 0, 0);font-size: 22px;";
        document.body.appendChild(m);
        setTimeout(function() {
            var d = 0.5;
            m.style.webkitTransition = '-webkit-transform ' + d + 's ease-in, opacity ' + d + 's ease-in';
            m.style.opacity = '0';
            setTimeout(function() { document.body.removeChild(m) }, d * 1000);
        }, duration);
    },
    //用于处理发起请求按钮
    challenge: function() {
        var _this = this;
        $(document).on("click", ".challenge", function() {
            var cthis = this;
            _this.player2 = $(this).attr("data-info");
            tempPlayer2 = $(this).attr("data-info");
            var judgePlayer2ToPlayer1Info = _this.userPhone + "-" + _this.player2;
            $.ajax({
                url: _this.urls.judgePlayer2ToPlayer1,
                type: "get",
                data: "judgePlayer2ToPlayer1Info=" + judgePlayer2ToPlayer1Info,
                success: function(data) {
                    if (data.cS) {
                        bootbox.alert("您有该玩家的挑战请求，请先结束该挑战!");
                        $('#selectModel').modal("hide");
                        $('#teamChallengeModel').modal("hide");
                        $('#getChanllengeModel').modal("hide");
                        $.ajax({
                            url: _this.urls.confirmPlayer2,
                            data: "confirmPlayer2Info=" + _this.userPhone,
                            type: "post",
                            success: function(data) {
                                _this.chanllengData = data;
                                _this.fillChanllengData(_this.chanllengData);
                            },
                            error: function() {
                                alert("获取挑战信息失败,请重新登录");
                                window.location.href = 'index.html';
                            }
                        });



                    } else {
                        $('.challenge').addClass('disabled');
                        _this.NOQ = 1;
                        tempNOQ = 1;

                        tempPlayer2 = $(cthis).attr("data-info");
                        _this.ganetype = $(cthis).attr("data-gantype");
                        tepGanetype = $(cthis).attr("data-gantype");
                        _this.pgid = $(cthis).attr("data-pgid");

                        tempPgid = $(cthis).attr("data-pgid");
                        _this.player2Name = $(cthis).attr("data-player2Name");
                        tempPlayer2Name = $(cthis).attr("data-player2Name");
                        if (_this.ganetype == 1) {
                            _this.tegid = $(cthis).attr("data-tegid");
                            tempTegid = $(cthis).attr("data-tegid");
                        } else {
                            _this.tegid = "空"
                        }
                        _this.receive = $(cthis).attr("data-receive");
                        tempReceiver = $(cthis).attr("data-receive");
                        // var r;
                        if (_this.receive == 'receiver') {
                            var cName = $(cthis).attr("data-player1name");

                            bootbox.confirm("确认接受【" + cName + "】的挑战 ? 确认之后不可取消", function(result) {
                                _this.dealModel(result)
                            });

                        } else {

                            bootbox.confirm("确认挑战【" + tempPlayer2Name + "】 ? 确认之后不可取消", function(result) {
                                _this.dealModel(result)

                            });
                        }
                        _this.arraySplice();
                        c = alltime;
                    }


                },
                error: function() {
                    alert("获取挑战信息失败，请重新登录后再次尝试");
                    window.location.href = 'index.html';
                }
            });


        })
    },
    //用于处理接收挑战之后的请求
    dealModel: function(r) {
        var _this = this;
        if (r) {
            $('#selectModel').modal("hide");
            $('#teamChallengeModel').modal("hide");
            $('#getChanllengeModel').modal("hide");
            if (_this.receive != 'receiver') {
                var saveChallengeInfo = _this.userPhone + "-" + _this.player2 + "-" + _this.ganetype + "-" + _this.tegid;
                _this.sendAjaxToSaveGameInfo(saveChallengeInfo);
            } else {
                _this.sendAjaxGetQuestion();
            }



        } else {
            $('.challenge').removeClass('disabled');
            return false;
        }
    },
    sendAjaxGetQuestion: function() {
        var _this = this;
        $.ajax({
            url: _this.urls.getQuestion,
            type: "get",
            success: function(data) {

                _this.questionArray = data.examList;
                tempQuestionArray = data.examList;
                $("#cotent").empty();
                $("#time").empty();
                _this.createFram()
                timedCount();
                addToCotent(data.examList);
                $("#btn").append(" <nav aria-label='...'><ul class= 'pager' display='none' style='margin:0 0;display:flex;justify-content:space-around;'><li><a href='#' data-pre='1' id='pre' class='btn ' role='button' style='font-size: 14px;background-color: #b33030;color:white;padding:0.5em 2em 0.5em 2em;'>上一题</a></li><li id='liNe'><a href='#' data-pre='1' id='next' class='btn ' role='button' style='font-size: 14px;background-color: #f18e23;color:white;padding:0.5em 2em 0.5em 2em;'>下一题</a></li></ul > </nav >");


            },
            error: function() {
                alert("获取试卷内容失败，请重新登录后再次尝试");
                window.location.href = 'index.html';
            }
        });
    },

}

//用于开始计时
function timedCount() {
    $("#time").text(c + "s")

    c = c - 1;
    t = setTimeout("timedCount()", 1000); //每1秒迭代一次
    if (c < 0) {
        stopCount();
        time = alltime - c;
        if (time > alltime) {
            time = alltime
        }
        if (time <= 0) {
            time = 1;
        }
        var result = getCurrentAnswer();

        tempAnswered.set(tempNOQ, result);
        if (tempReceiver != 'receiver') {
            sendQu(tempQuestionArray, tempAnswered, tempPgid, tempUserPhone, time, tepGanetype, tempPlayer2, tempTegid);
        } else {
            var dealReceiverAnsInfo = tempQuestionArray + "_" + tempAnswered + "_" + tempPgid + "_" + tempUserPhone + "_" + time + "_" + tepGanetype;
            dealReceiverAns(dealReceiverAnsInfo);
        }


    }

}
//用于停止计时
function stopCount() {
    clearTimeout(t)
}
//格式化当前时间，变为yyyy-mm-dd
function getNowDate() {
    // 获取当前日期
    var date = new Date();
    // 获取当前月份
    var nowMonth = date.getMonth() + 1;
    // 获取当前是几号
    var strDate = date.getDate();
    // 添加分隔符“-”
    var seperator = "-";
    // 对月份进行处理，1-9月在前面添加一个“0”
    if (nowMonth >= 1 && nowMonth <= 9) {
        nowMonth = "0" + nowMonth;
    }
    // 对月份进行处理，1-9号在前面添加一个“0”
    if (strDate >= 0 && strDate <= 9) {
        strDate = "0" + strDate;
    }
    // 最后拼接字符串，得到一个格式为(yyyy-MM-dd)的日期
    var nowDate = date.getFullYear() + seperator + nowMonth + seperator + strDate;
    return nowDate;
}
//创建集成框架
function createFram() {
    $("#cotent").append("<div class='backGroundImg6' ><p style='font-size: 18px;color:#333333;padding-left: 17px;padding-top: 7px;'>距离结束还剩：<strong><span id='time' style='color:#b33030'></span></strong></p> </div>");
    $("#cotent").append(" <div class='backGroundImg7' id='backGroundImg7'></div>")
    $("#backGroundImg7").append("<div id='Question' style='color:#333333;font-size: 20px;padding-left: 39px;padding-right:39px;'></div>")
    $("#backGroundImg7").append("<div id='select' style='color:#333333;font-size: 20px;padding-top: 64px;padding-right:14%;padding-left:2%'></div>");
    $("#select").append("<ul id='ul_se'></ul>");
    $("#backGroundImg7").append(" <div id='btn' style='margin-top:25%'  class=''></div>");
}
//用于接受挑战的提交
function dealReceiverAns(dealReceiverAnsInfo) {
    $.ajax({
        url: host + "/hongsong/dealReceiverAns",
        data: "dealReceiverAnsInfo=" + dealReceiverAnsInfo,
        type: "post",
        success: function(data) {
            $("#cotent").empty();
            $("#cotent").append(" <div class='' id='bH1' style='position: relative;margin-left:7%;margin-right:7%;margin-top:22%'></div>")

            $("#bH1").append(" <img src='images/backToHome.png' style='width: 100%' />");
            $("#bH1").append("  <div class='bH2' style='position: absolute;top:50%;text-align:center;width:100%'></div>");
            $(".bH2").append("  <p style='font-size: 1.5em;color:white;'>用时<span id='reTime' style='font-size: 1.5em;color:white;'>" + time + "s" + "</span></p>");
            $("#bH1").append(" <div class='bH3'style='margin-top:-46%;text-align: center '></div>");
            $(".bH3").append("<button style='margin-top: 7%;background-color: #b23030;width: 80%;height: 2.5em;font-size: 1.25em;color:white;border: 1px solid #b23030;border-radius:20px;' id='lookMoreInfo'>查看答题情况</button> ");
            $(".bH3").append(" <button style='margin-top: 7%;background-color: #FFFFFF;width: 80%;height: 2.5em;font-size: 1.25em;color:#b23030;border: 1px solid #b23030;border-radius:20px;' id='backToMain'>回到主界面</button> ");

        },
        error: function() {
            //alert("获取比赛结果失败")
        }
    });
}
//用于实现将题号以及回答的结果封装成字符串
function stringfyAnswer(list, map) {
    var _this = this;
    var map1 = new Map();
    var questionNumber = "";
    $.each(list, function(index, item) {
        if (index >= list.length - 1) {
            questionNumber += item.qn;
        } else {
            questionNumber += item.qn + "-";
        }

    })
    var value = "";

    for (var i = 0; i < list.length; i++) {
        var j = i + 1;
        if (i >= list.length - 1) {
            if (map.get(j) == '' || map.get(j) == null) {
                value += "空"
            } else {
                value += map.get(j);
            }

        } else {
            if (map.get(j) == '' || map.get(j) == null) {
                value += "空" + "-"
            } else {
                value += map.get(j) + "-";
            }

        }
    }
    map1.set("questionNumber", questionNumber);
    map1.set("value", value);
    return map1;
}
//用于实现提交答案，并且进入到答题结束页面的功能
function sendQu(list, map, pgid, user, time, ganetype, player2, tegid) {
    var map = stringfyAnswer(list, map);

    var questionNumber = map.get("questionNumber");
    var value = map.get("value");
    var dealAnsInfo = questionNumber + "_" + value + "_" + pgid + "_" + user + "_" + time + "_" + ganetype + "_" + player2 + "_" + tegid;
    $.ajax({
        url: URLS.dealAns,
        type: "post",
        data: "dealAnsInfo=" + dealAnsInfo,
        success: function(data) {
            $("#cotent").empty();
            $("#cotent").append(" <div class='' id='bH1' style='position: relative;margin-left:7%;margin-right:7%;margin-top:22%'></div>")

            $("#bH1").append(" <img src='images/backToHome.png' style='width: 100%' />");
            $("#bH1").append("  <div class='bH2' style='position: absolute;top:50%;text-align:center;width:100%'></div>");
            $(".bH2").append("  <p style='font-size: 1.5em;color:white;'>用时<span id='reTime' style='font-size: 1.5em;color:white;'>" + time + "s" + "</span></p>");
            $("#bH1").append(" <div class='bH3'style='margin-top:-46%;text-align: center '></div>");
            $(".bH3").append("<button style='margin-top: 7%;background-color: #b23030;width: 80%;height: 2.5em;font-size: 1.25em;color:white;border: 1px solid #b23030;border-radius:20px;' id='lookMoreInfo'>查看答题情况</button> ");
            $(".bH3").append(" <button style='margin-top: 7%;background-color: #FFFFFF;width: 80%;height: 2.5em;font-size: 1.25em;color:#b23030;border: 1px solid #b23030;border-radius:20px;' id='backToMain'>回到主界面</button> ");

        },
        error: function() {
            alert("获取试卷内容失败");

        }
    });
}
//用于清空数组
function arraySplice() {

    tempQuestionArray.splice(0, tempQuestionArray.length);

    tempAnswered.clear(); //清空数组
}
//用于获取当前题目的答案
function getCurrentAnswer() {
    var answer = new Array();
    if (tempQuestionType == 2) {
        $('input:checkbox').each(function() {
            if ($(this).is(':checked') == true) {

                answer.push($(this).val());
            }

        })
    } else {
        $('input:radio').each(function() {
            if ($(this).is(':checked') == true) {
                answer.push($(this).val());
            }

        })
    }

    var result = "";
    for (var i = 0; i < answer.length; i++) {
        if (answer[i + 1] == null) {
            result += answer[i]
        } else {
            result += answer[i] + ","
        }
    }
    return result;
}
// 用于个人赛开始按钮
function personalbegin() {
    $.ajax({
        url: URLS.getSessionId,
        data: "getSessionIdInfo=" + tempUserPhone,
        type: "get",
        //加上这句话,允许浏览器向服务器跨域请求时携带cookie
        xhrFields: {
            withCredentials: true
        },
        crossDomain: true,
        success: function(data) {
            if (!data.status) {
                alert("您的账号已经在其他设备登录，或者session请求超时，请您重新登录，谢谢!!!");
                window.location.href = 'index.html';

            } else {
                tempNOQ = 1;
                c = alltime;
                tepGanetype = $("#personalbegin").attr("data-type");
                $.ajax({
                    url: URLS.getUserInfo,
                    data: "phoneNumber=" + tempUserPhone,
                    type: "get",
                    success: function(data) {
                        $("#userInfo").empty();
                        $.each(data.userInfo, function(index, item) {
                            $("#userInfo").append("<tr id='tr" + index + "'></tr>");
                            $("#tr" + index).append("<td>" + item.userName + '/' + item.phoneNumber + "</td>");
                            $("#tr" + index).append("<td>" + item.deptname + "</td>");
                            $("#tr" + index).append("<td><button class='btn btn-default challenge' data-info=" + item.phoneNumber + " type='button' data-gantype='0' data-player2Name='" + item.userName + "'>点击挑战对方</button></td>");
                        });
                        //此处弹出模态框
                        $('#selectModel').modal({
                            backdrop: "static"
                        })
                    },
                    error: function() {
                        alert("获取对手信息失败,将重新登录");
                        window.location.href = 'index.html';
                    }
                });

            }

        }
    });




}
//团队赛开始按钮
function teambegin() {
    $.ajax({
        url: URLS.getSessionId,
        data: "getSessionIdInfo=" + tempUserPhone,
        type: "get",
        //加上这句话,允许浏览器向服务器跨域请求时携带cookie
        xhrFields: {
            withCredentials: true
        },
        crossDomain: true,
        success: function(data) {
            if (!data.status) {
                alert("您的账号已经在其他设备登录，或者session请求超时，请您重新登录，谢谢!!!");
                window.location.href = 'index.html';

            } else {

                var teamInfo = tempUserPhone + "#" + getNowDate();
                c = alltime;
                $.ajax({
                    url: URLS.teamChanllenge,
                    data: "teamInfo=" + teamInfo,
                    type: "post",
                    success: function(data) {

                        tempTegid = data.tegid;
                        $("#teamChallengeModel_tb").empty();
                        if (data.challengCondition) {
                            $.each(data.changInfo0, function(index, item) {
                                $("#teamChallengeModel_tb").append("<tr id='teamChallengeModeltr" + index + "'></tr>");
                                $("#teamChallengeModeltr" + index).append("<td>" + item.userName + '/' + item.phoneNumber + "</td>");
                                $("#teamChallengeModeltr" + index).append("<td>" + item.deptname + "</td>");
                                $("#teamChallengeModeltr" + index).append("<td><button class='btn btn-default challenge' data-info=" + item.phoneNumber + " type='button' data-gantype='1' data-player2Name='" + item.userName + "'   data-tegid='" + data.lTeamgames[0].tegid + "'>点击挑战对方</button></td>");

                            })
                            //此处弹出模态框
                            $('#teamChallengeModel').modal({
                                backdrop: "static"
                            })
                        } else {
                            alert("您未在规定的部门或时间进行比赛");
                        }

                    },
                    error: function() {
                        alert("获取团队赛信息失败，将强制重新登录");
                        window.location.href = 'index.html';
                    }
                });



            }

        }
    })





}
//用于处理个人练习赛开始按钮
function exercise() {







    $.ajax({
        url: URLS.getSessionId,
        data: "getSessionIdInfo=" + tempUserPhone,
        type: "get",
        //加上这句话,允许浏览器向服务器跨域请求时携带cookie
        xhrFields: {
            withCredentials: true
        },
        crossDomain: true,
        success: function(data) {
            if (!data.status) {
                alert("您的账号已经在其他设备登录，或者session请求超时，请您重新登录，谢谢!!!");
                window.location.href = 'index.html';

            } else {
                tempNOQ = 1;
                c = alltime;
                tepGanetype = $("#exercise").attr("data-type");
                arraySplice();
                tempTegid = '空';
                tempPlayer2 = '空';
                var r = confirm("确认开始，开始之后必须答完所有题目才能退出");
                if (r) {
                    var saveChallengeInfo = tempUserPhone + "-" + tempPlayer2 + "-" + tepGanetype + "-" + tempTegid;
                    sendAjaxToSaveGameInfo(saveChallengeInfo);
                } else {
                    return false;
                }
            }

        }
    });




}
//发送ajax请求将比赛信息保存到personalGame表中
function sendAjaxToSaveGameInfo(saveChallengeInfo) {

    $.ajax({
        url: URLS.saveChallenge,
        type: "post",
        data: "saveChallengeInfo=" + saveChallengeInfo,
        success: function(data) {


            tempPgid = data.pgid;
            sendAjaxGetQuestion();

        },
        error: function() {
            alert("存储答题信息失败，请重新登录后再次尝试");
            window.location.href = 'index.html';
        }
    });
}
//用于将题目内容加载到页面
function addToCotent(arr) {

    $("#Question").empty();
    $("#select").empty();
    $("#select").append("<ul id='ul_se'></ul>");
    $("#Question").append("<font  style='color:#333333;font-size:16px'>Q" + tempNOQ + ".</font><span style='color:#333333;font-size:16px'>" + arr[tempNOQ - 1].content + "</span>")
    var option = new Array();

    tempQuestionType = arr[tempNOQ - 1].testType;
    option = arr[tempNOQ - 1].option.split(",");

    if (tempQuestionType == 2) {

        $.each(option, function(index, item) {
            // $("#ul_se").append("<li><span style='margin-right:10px;display:inline-block;vertical-align:middle;' id='spCheck'><input type='checkbox'  style='width:20px;height:20px;' name='opt' value='" + item + "' class='check' ></span><a data-item='" + item + "' class='optionSpan' data-sig='no'><span style='font-size: 16px;color: #333333'>" + item + "</span></a> </li>");
            $("#ul_se").append("<li><a data-item='" + item + "' class='optionSpan' data-sig='no'><span style='margin-right:10px;display:inline-block;vertical-align:middle;' id='spCheck'><input type='checkbox'  style='width:20px;height:20px;' name='opt' value='" + item + "' class='check' ></span><span style='font-size: 16px;color: #333333'>" + item + "</span></a></li>")
        })
    } else {
        $.each(option, function(index, item) {
            // $("#ul_se").append("<li><span style='margin-right:10px;display:inline-block;vertical-align:middle;' id='spCheck'><input type='radio'  style='width:20px;height:20px;' name='opt' value='" + item + "' class='radio' ></span><a data-item='" + item + "' class='optionSpan' data-sig='no'><span style='font-size: 16px;color: #333333'>" + item + "</span></a> </li>");
            $("#ul_se").append("<li><a data-item='" + item + "' class='optionSpan' data-sig='no'><span style='margin-right:10px;display:inline-block;vertical-align:middle;' id='spCheck'><input type='radio'  style='width:20px;height:20px;' name='opt' value='" + item + "' class='radio' ></span><span style='font-size: 16px;color: #333333'>" + item + "</span></a></li>")
        })
    }

}

function sendAjaxGetQuestion() {

    $.ajax({
        url: URLS.getQuestion,
        type: "get",
        success: function(data) {
            tempQuestionArray = data.examList;
            $("#cotent").empty();
            $("#time").empty();
            createFram()
            timedCount();
            addToCotent(data.examList);
            $("#btn").append(" <nav aria-label='...'><ul class= 'pager' display='none' style='margin:0 0;display:flex;justify-content:space-around;'><li><a href='#' data-pre='1' id='pre' class='btn ' role='button' style='font-size: 14px;background-color: #b33030;color:white;padding:0.5em 2em 0.5em 2em;'>上一题</a></li><li id='liNe'><a href='#' data-pre='1' id='next' class='btn ' role='button' style='font-size: 14px;background-color: #f18e23;color:white;padding:0.5em 2em 0.5em 2em;'>下一题</a></li></ul > </nav >");


        },
        error: function() {
            alert("获取试卷内容失败，请重新登录后再次尝试");
            window.location.href = 'index.html';
        }
    });
}
//禁选checkBox
function disCheck(el) {
    var checkbox = $('input:' + el);
    checkbox.attr("disabled", "disabled");
}
//通过测试类型，禁用不同样式的checkbox
function judgeDicC() {
    if (tempQuestionType == 2) {
        disCheck("checkbox")
    } else {
        disCheck("radio")
    }
}
//排行榜
function Leaderboard() {

    Toast('敬请期待', 2000);

}
//自制弹窗
function Toast(msg, duration) {
    duration = isNaN(duration) ? 3000 : duration;
    var m = document.createElement('div');
    m.innerHTML = "<span style='padding:20%'>" + msg + "</span>";
    m.style.cssText = "width:50%;padding:6%;min-width: 150px;opacity: 0.5;color: rgb(255, 255, 255);line-height: 30px;text-align: center;border-radius: 55px;position: fixed;top: 50%;left: 30%;z-index: 999999;background: rgb(0, 0, 0);font-size: 22px;";
    document.body.appendChild(m);
    setTimeout(function() {
        var d = 0.5;
        m.style.webkitTransition = '-webkit-transform ' + d + 's ease-in, opacity ' + d + 's ease-in';
        m.style.opacity = '0';
        setTimeout(function() { document.body.removeChild(m) }, d * 1000);
    }, duration);
}