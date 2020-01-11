package com.boot.liqian.util;

public class JudgeEmpty {
    /**
     * 判断字符串是否为空，为空返回true
     * @param str
     * @return
     */
    public  Boolean judgeString(String str){
        Boolean re=true;
        if(!("").equals(str)&&str!=null){
            re=false;
        }
        return  re;
    }
}
