package com.cy.sys.pojo.param.org;

import lombok.Data;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

/**
 * <p>
 * 
 * </p>
 *
 * @author CY
 * @since 2020-11-24
 */
@Data
@ToString
public class OrgParam {

    public interface GroupEdit {}

    /**
     * 自增主键
     */
    @NotNull(groups = {GroupEdit.class},message = "部门id不能为空")
    private Long id;

    /**
     * 部门唯一主键
     */
    @NotNull(groups = {GroupEdit.class},message = "部门surrogateId不能为空")
    private Long surrogateId;

    /**
     * 部门名称
     */
    @NotBlank(message = "部门名称不能为空")
    @Length(min = 2,max = 20,message = "部门名称需要在2到20个字符之间")
    private String name;

    /**
     * 上级部门id
     */
    @NotNull(message = "上级部门parentId不能为空")
    private Long parentId;

    /**
     * 上级部门surrogateId
     */
    @NotNull(message = "上级部门parentSurrogateId不能为空")
    private Long parentSurrogateId;

    /**
     * 排序, 部门咋当前层级目录下的顺序
     */
    @NotNull(message = "部门顺序不能为空")
    private Integer seq;

    /**
     * 备注
     */
    @Length(max = 150,message = "部门备注需要在150个字符以内")
    private String remark;

}
