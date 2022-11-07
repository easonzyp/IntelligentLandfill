package com.wise.develop.Landfill.bean;

import com.wise.develop.Landfill.base.BaseResponse;

public class HomeDayListBean extends BaseResponse {
    private String month;
    private String day;

    public HomeDayListBean(String month, String day) {
        this.month = month;
        this.day = day;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }
}
