package me.catas.cabin.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PageQuery<T> implements Serializable {

    /**
     * 查询参数
     */
    private T param;

    /**
     * 当前的页码
     */
    @NonNull
    private Integer current;

    /**
     * 每页记录数
     */
    @NonNull
    private Integer pageSize;
}
