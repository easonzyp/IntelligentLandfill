package com.wise.develop.IntelligentLandfill.util;

public class TypeUtil {
    public static String getInspectionStr(String type) {
        if ("1".equals(type)) {
            return "上级督查";
        } else if ("2".equals(type)) {
            return "安全巡检";
        } else {
            return "计划检查";
        }
    }
}
