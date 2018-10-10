package cn.jeeweb.modules.scjhgl.entity;

import cn.jeeweb.core.common.entity.AbstractEntity;
import cn.jeeweb.modules.sys.entity.User;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.FieldFill;
import com.baomidou.mybatisplus.enums.IdType;

import java.util.Date;

/**
 * Dscription: 生产计划管理 - 部件管理
 * @author : Kevin Du
 * @version : 1.0
 * @date : 2018/10/8 13:06
 */
@TableName("T_SCJHGL_LJGL")
@SuppressWarnings("serial")
public class ScjhglBjgl extends AbstractEntity<String> {
    /** 主键 */
    @TableId(value = "ID", type = IdType.UUID)
    private String id;

    /** 合同ID */
    @TableField(value = "HTID")
    private String htid;
    /** 零件图号 */
    @TableField(value = "LJTH")
    private String ljth;
    /** 零件名称 */
    @TableField(value = "LJMC")
    private String ljmc;
    /** 单用量 */
    @TableField(value = "DYL")
    private String dyl;
    /** 是否是部件 */
    @TableField(value = "SFSBJ")
    private String sfsbj;
    /** 部件组成 */
    @TableField(value = "BJZC")
    private String bjzc;
    /** 数量 */
    @TableField(value = "SL")
    private String sl;
    /** 未入库数量 */
    @TableField(value = "WRKSL")
    private String wrksl;
    /** 剩余数量 */
    @TableField(value = "SYSL")
    private String sysl;

    /** 更新者 */
    @TableField(value = "update_by", el = "updateBy.id", fill = FieldFill.UPDATE)
    private User updateBy;
    /** 创建时间 */
    @TableField(value = "create_date", fill = FieldFill.UPDATE)
    private Date createDate;
    /** 创建者 */
    @TableField(value = "create_by", el = "createBy.id", fill = FieldFill.INSERT)
    private User createBy;
    /** 更新时间 */
    @TableField(value = "update_date", fill = FieldFill.UPDATE)
    private Date updateDate;

    @Override
    public String getId() {
        return id;
    }

    @Override
    public void setId(String id) {
        this.id = id;
    }

    public String getHtid() {
        return htid;
    }

    public void setHtid(String htid) {
        this.htid = htid;
    }

    public String getLjmc() {
        return ljmc;
    }

    public void setLjmc(String ljmc) {
        this.ljmc = ljmc;
    }

    public String getSl() {
        return sl;
    }

    public void setSl(String sl) {
        this.sl = sl;
    }

    public User getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(User updateBy) {
        this.updateBy = updateBy;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public User getCreateBy() {
        return createBy;
    }

    public void setCreateBy(User createBy) {
        this.createBy = createBy;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public String getLjth() {
        return ljth;
    }

    public void setLjth(String ljth) {
        this.ljth = ljth;
    }

    public String getDyl() {
        return dyl;
    }

    public void setDyl(String dyl) {
        this.dyl = dyl;
    }

    public String getSfsbj() {
        return sfsbj;
    }

    public void setSfsbj(String sfsbj) {
        this.sfsbj = sfsbj;
    }

    public String getBjzc() {
        return bjzc;
    }

    public void setBjzc(String bjzc) {
        this.bjzc = bjzc;
    }

    public String getSysl() {

        return sysl;
    }

    public void setSysl(String sysl) {
        this.sysl = sysl;
    }

    public String getWrksl() {
        return wrksl;
    }

    public void setWrksl(String wrksl) {
        this.wrksl = wrksl;
    }
}
