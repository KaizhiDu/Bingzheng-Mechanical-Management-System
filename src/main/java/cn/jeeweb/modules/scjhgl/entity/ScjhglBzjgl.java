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
 * Dscription: 生产计划管理 - 标准件管理
 * @author : Kevin Du
 * @version : 1.0
 * @date : 2018/12/21 16:27
 */
@TableName("T_SCJHGL_BZJGL")
@SuppressWarnings("serial")
public class ScjhglBzjgl extends AbstractEntity<String> {
    /** 主键 */
    @TableId(value = "ID", type = IdType.UUID)
    private String id;

    /** 计划ID */
    @TableField(value = "HTID")
    private String htid;
    /** 分类大类 */
    @TableField(value = "FLDL")
    private String fldl;
    /** 分类小类 */
    @TableField(value = "FLXL")
    private String flxl;
    /** 规格 */
    @TableField(value = "GG")
    private String gg;
    /** 单位 */
    @TableField(value = "DW")
    private String dw;
    /** 数量 */
    @TableField(value = "SL")
    private String sl;
    /** 入库数量 */
    @TableField(value = "RKSL")
    private String rksl;
    /** 日期 */
    @TableField(value = "RQ")
    private String rq;
    /** 入库年 */
    @TableField(value = "N")
    private String n;
    /** 入库月 */
    @TableField(value = "Y")
    private String y;
    /** 入库日 */
    @TableField(value = "R")
    private String r;
    /** 单用量 */
    @TableField(value = "DYL")
    private String dyl;
    /** 进货商 */
    @TableField(value = "JHS")
    private String jhs;

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

    public String getJhs() {
        return jhs;
    }

    public void setJhs(String jhs) {
        this.jhs = jhs;
    }

    public String getRq() {
        return rq;
    }

    public void setRq(String rq) {
        this.rq = rq;
    }

    public String getRksl() {
        return rksl;
    }

    public void setRksl(String rksl) {
        this.rksl = rksl;
    }

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

    public String getFldl() {
        return fldl;
    }

    public void setFldl(String fldl) {
        this.fldl = fldl;
    }

    public String getFlxl() {
        return flxl;
    }

    public void setFlxl(String flxl) {
        this.flxl = flxl;
    }

    public String getGg() {
        return gg;
    }

    public void setGg(String gg) {
        this.gg = gg;
    }

    public String getDw() {
        return dw;
    }

    public void setDw(String dw) {
        this.dw = dw;
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

    public String getN() {
        return n;
    }

    public void setN(String n) {
        this.n = n;
    }

    public String getY() {
        return y;
    }

    public void setY(String y) {
        this.y = y;
    }

    public String getR() {
        return r;
    }

    public void setR(String r) {
        this.r = r;
    }

    public String getDyl() {
        return dyl;
    }

    public void setDyl(String dyl) {
        this.dyl = dyl;
    }
}
