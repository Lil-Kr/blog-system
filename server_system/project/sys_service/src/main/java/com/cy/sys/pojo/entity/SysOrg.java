package com.cy.sys.pojo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.*;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author CY
 * @since 2020-11-24
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class SysOrg extends Model<SysOrg> {

    private static final long serialVersionUID = 1L;

    /**
     * 自增主键
     */
      @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 唯一主键
     */
    private Long surrogateId;

    /**
     * 部门编号
     */
    private String number;

    /**
     * 部门名称
     */
    private String name;

    /**
     * 父id
     */
    private Long parentId;

    /**
     * 部门层级, 0. / 0.1, 0.2
     */
    private String level;

    /**
     * 排序, 部门咋当前层级目录下的顺序
     */
    private Integer seq;

    /**
     * 备注
     */
    private String remark;

    /**
     * 操作人
     */
    private String operator;

    /**
     * 操作ip
     */
    private String operateIp;

    /**
     * 创建时间
     */
    private String createTime;

    /**
     * 更改时间
     */
    private String updateTime;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
