package cn.jeeweb.modules.ckgl.entity;

import cn.jeeweb.core.common.entity.AbstractEntity;
import cn.jeeweb.modules.sys.entity.User;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.FieldFill;
import com.baomidou.mybatisplus.enums.IdType;

import java.util.Date;

/**
 * Dscription: 仓库管理 - 原材料明细
 * @author : Kevin Du
 * @version : 1.0
 * @date : 2018/10/14 15:40
 */
@TableName("T_CKGL_YCL_MX")
@SuppressWarnings("serial")
public class CkglYclMx extends AbstractEntity<String> {
    /** 主键 */
    @TableId(value = "ID", type = IdType.UUID)
    private String id;

    /** 原材料ID */
    @TableField(value = "YCLID")
    private String yclid;
    /** 明细 */
    @TableField(value = "MX")
    private String mx;
    /** 时间 */
    @TableField(value = "SJ")
    private Date sj;
    /**年*/
    @TableField(value = "N")
    private String n;
    /**月*/
    @TableField(value = "Y")
    private String y;
    /**日*/
    @TableField(value = "R")
    private String r;
    /**进货商*/
    @TableField(value = "JHS")
    private String jhs;
    /**进销*/
    @TableField(value = "JX")
    private String jx;
    /** 分类大类 */
    @TableField(value = "FLDL")
    private String fldl;
    /** 分类小类 */
    @TableField(value = "FLXL")
    private String flxl;
    /** 规格 */
    @TableField(value = "GG")
    private String gg;

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

    public String getYclid() {
        return yclid;
    }

    public void setYclid(String yclid) {
        this.yclid = yclid;
    }

    public String getMx() {
        return mx;
    }

    public void setMx(String mx) {
        this.mx = mx;
    }

    public Date getSj() {
        return sj;
    }

    public void setSj(Date sj) {
        this.sj = sj;
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

    public String getJhs() {
        return jhs;
    }

    public void setJhs(String jhs) {
        this.jhs = jhs;
    }

    public String getJx() {
        return jx;
    }

    public void setJx(String jx) {
        this.jx = jx;
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
}
