package com.xc.file.util;

import javax.validation.constraints.NotNull;

/**
 * @author : 吴后荣
 * @date : 2019/12/19 11:55
 * @description :
 */
public class FileUtils {

    /**
     * 获取文件后缀名
     * @param fileName 文件名称（包含后缀）
     * @return 后缀名
     */
    public static String getSuffix(@NotNull String fileName) {
        int index = fileName.lastIndexOf(".");
        if (index != -1) {
            return fileName.substring(index + 1).toLowerCase();
        }
        return "";
    }
}
