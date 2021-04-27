package me.catas.cabin.response;

import lombok.Data;

@Data
public class ShowType {

    /**
     * 不提示错误
     */
    public static final Integer SILENT = 0;

    /**
     * 警告信息提示
     */
    public static final Integer WAIN = 1;

    /**
     * 错误信息提示
     */
    public static final Integer ERROR = 2;

    /**
     * 通知提示
     */
    public static final Integer NOTIFICATION = 4;

    /**
     * 页面跳转
     */
    public static final Integer PAGE = 9;

}
