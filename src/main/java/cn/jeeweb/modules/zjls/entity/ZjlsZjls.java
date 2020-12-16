package cn.jeeweb.modules.zjls.entity;

import cn.jeeweb.core.common.entity.AbstractEntity;
import cn.jeeweb.modules.sys.entity.User;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.FieldFill;
import com.baomidou.mybatisplus.enums.IdType;

import java.util.Date;

/**
 * Dscription: 资金流水
 * @author : Kevin Du
 * @version : 1.0
 * @date : 2019/2/17 18:36
 */
@TableName("T_ZJLS_ZJLS")
@SuppressWarnings("serial")
public class ZjlsZjls extends AbstractEntity<String> {
    /** 主键 */
    @TableId(value = "ID", type = IdType.UUID)
    private String id;

    /** 日期 */
    @TableField(value = "RQ")
    private String rq;
    /**年*/
    @TableField(value = "N")
    private String n;
    /**月*/
    @TableField(value = "Y")
    private String y;
    /**日*/
    @TableField(value = "R")
    private String r;
    /**明细*/
    @TableField(value = "MX")
    private String mx;
    /**明细2*/
    @TableField(value = "MX2")
    private String mx2;
    /**公司*/
    @TableField(value = "GS")
    private String gs;
    /**1*/
    @TableField(value = "ONE")
    private String one;
    /**2*/
    @TableField(value = "TWO")
    private String two;
    /**类型*/
    @TableField(value = "LX")
    private String lx;


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


    public String getMx2() {
        return mx2;
    }

    public void setMx2(String mx2) {
        this.mx2 = mx2;
    }

    public String getLx() {
        return lx;
    }

    public void setLx(String lx) {
        this.lx = lx;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRq() {
        return rq;
    }

    public void setRq(String rq) {
        this.rq = rq;
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

    public String getMx() {
        return mx;
    }

    public void setMx(String mx) {
        this.mx = mx;
    }

    public String getOne() {
        return one;
    }

    public void setOne(String one) {
        this.one = one;
    }

    public String getTwo() {
        return two;
    }

    public void setTwo(String two) {
        this.two = two;
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

    public String getGs() {
        return gs;
    }

    public void setGs(String gs) {
        this.gs = gs;
    }
}
