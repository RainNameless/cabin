package me.catas.cabin.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
public enum BizCode implements Serializable {

    /**
     * 状态码解读：
     * 第一位：1代表原封不动提示错误码与错误信息给用户，2代表仅在控制台打印，展示统一话术,如系统正在开小差...
     * 第二至三位：代表功能模块('00'代表全局)
     * 第四至六位：代表模块下的错误码
     */
    FAILED("100999", "操作失败", ShowType.ERROR),
    NODE_HOST_EXIST("100101", "节点Host已存在", ShowType.ERROR);

    /**
     * 状态码
     */
    @Getter
    private String code;

    /**
     * 信息
     */
    @Getter
    private String msg;

    /**
     * 错误展示类型
     */
    @Getter
    private Integer type;
}
