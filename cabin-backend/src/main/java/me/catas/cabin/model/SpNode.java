package me.catas.cabin.model;

import lombok.*;

/**
 * 服务节点实体类
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class SpNode extends BaseModel {

    private static final long serialVersionUID = 1L;

    /**
     * 名称
     */
    private String name;

    /**
     * host
     */
    private String host;

    /**
     * 0: 可用, 1暂停
     */
    private Integer status;

    /**
     * 节点备注
     */
    private String remark;

}
