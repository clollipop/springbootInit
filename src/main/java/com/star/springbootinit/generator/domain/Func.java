package generator.domain;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 权限表
 * @TableName func
 */
@Data
public class Func implements Serializable {
    /**
     * 权限id
     */
    private Long funcId;

    /**
     * 父菜单ID
     */
    private Long parentId;

    /**
     * 权限类型
     */
    private String funcName;

    /**
     * 功能代码 user:create
     */
    private String funcUrl;

    /**
     * 功能类型1：目录、2：菜单、3：按钮
     */
    private Integer funcType;

    /**
     * 用户状态：0正常 1停用
     */
    private Integer status;

    /**
     * 创建者
     */
    private String createBy;

    /**
     * 更新者
     */
    private String updateBy;

    /**
     * 排序
     */
    private Integer sortNum;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    private static final long serialVersionUID = 1L;
}