package cn.jeeweb.modules.htglold.entity;

import cn.jeeweb.modules.sys.entity.User;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.FieldFill;
import com.baomidou.mybatisplus.enums.IdType;

import java.util.Date;

/**
 * Dscription: 合同管理 - 合同明细
 * @author : Kevin Du
 * @version : 1.0
 * @date : 2019/1/11 13:40
 */
@TableName("T_HTGL_HTMX2")
@SuppressWarnings("serial")
public class HtglHtmx2 {
    /** 主键 */
    @TableId(value = "ID", type = IdType.UUID)
    private String id;

    /** 合同ID */
    @TableField(value = "HTID")
    private String htid;
    /** 类型 */
    @TableField(value = "LX")
    private String lx;
    /** 次序 */
    @TableField(value = "CX")
    private int cx;
    /** 备注 */
    @TableField(value = "BZ")
    private String bz;
    /** 金额 */
    @TableField(value = "JE")
    private String je;
    /** 日期 */
    @TableField(value = "RQ")
    private String rq;
    /** 日期2 */
    @TableField(value = "RQ2")
    private String rq2;
    /** 乙方 */
    @TableField(value = "YF")
    private String yf;

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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getHtid() {
        return htid;
    }

    public void setHtid(String htid) {
        this.htid = htid;
    }

    public String getLx() {
        return lx;
    }

    public void setLx(String lx) {
        this.lx = lx;
    }

    public int getCx() {
        return cx;
    }

    public void setCx(int cx) {
        this.cx = cx;
    }

    public String getBz() {
        return bz;
    }

    public void setBz(String bz) {
        this.bz = bz;
    }

    public String getJe() {
        return je;
    }

    public void setJe(String je) {
        this.je = je;
    }

    public String getRq() {
        return rq;
    }

    public void setRq(String rq) {
        this.rq = rq;
    }

    public String getRq2() {
        return rq2;
    }

    public void setRq2(String rq2) {
        this.rq2 = rq2;
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

    public String getYf() {
        return yf;
    }

    public void setYf(String yf) {
        this.yf = yf;
    }
}
