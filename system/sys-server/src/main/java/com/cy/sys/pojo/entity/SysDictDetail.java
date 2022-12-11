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
 * @since 2020-11-29
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SysDictDetail extends Model<SysDictDetail> {

    private static final long serialVersionUID = 1L;

    /**
     * 数据字典id唯一主键
     */
    @TableId(value = "surrogate_id", type = IdType.AUTO)
    private Long surrogateId;

    /**
     * 数据字典主表id
     */
    private Long parentId;

    /**
     * 数据字典明细名称
     */
    private String name;

    /**
     * 备注
     */
    private String remark;


    @Override
    protected Serializable pkVal() {
        return this.surrogateId;
    }

}
