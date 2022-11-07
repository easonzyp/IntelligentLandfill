package com.wise.develop.Landfill.base;

import java.io.Serializable;

/**
 * Created by zyp on 2018/1/16.
 * response 基类
 */

public class BaseResponse implements Serializable {
    private static final long serialVersionUID = 4991385293921216098L;
    private int code;
    private String msg;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}

