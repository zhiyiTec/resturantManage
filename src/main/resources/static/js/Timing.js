var i = 600;

function fun() {
    if (i == 0) {


    }
    document.getElementById("mes").innerHTML = i + "s";
    i--;
}

function startTiming() {
    //setTimeout('myrefresh()', 10000); 
    //还有几秒；
    var intervalid;
    intervalid = setInterval("fun()", 1000); //指定1秒刷新一次
    return i;
}