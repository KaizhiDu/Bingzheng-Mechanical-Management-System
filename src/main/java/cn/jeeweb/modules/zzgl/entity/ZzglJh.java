package cn.jeeweb.modules.zzgl.entity;

import cn.jeeweb.core.common.entity.AbstractEntity;
import cn.jeeweb.modules.sys.entity.User;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.FieldFill;
import com.baomidou.mybatisplus.enums.IdType;

import java.util.Date;

/**
 * Dscription: 资金管理 - 借还
 * @author : Kevin Du
 * @version : 1.0
 * @date : 2019/1/7 14:36
 */
@TableName("T_ZZGL_JH")
@SuppressWarnings("serial")
public class ZzglJh extends AbstractEntity<String> {
    /** 主键 */
    @TableId(value = "ID", type = IdType.UUID)
    private String id;

    /** 姓名 */
    @TableField(value = "NAME")
    private String name;
    /** 钱 */
    @TableField(value = "MONEY")
    private String money;
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
    /**资金账户*/
    @TableField(value = "ZJZH")
    private String zjzh;
    /**资金名称*/
    @TableField(value = "ZJMC")
    private String zjmc;
    /**备注*/
    @TableField(value = "BZ")
    private String bz;

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

    public String getBz() {
        return bz;
    }

    public void setBz(String bz) {
        this.bz = bz;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
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

    public String getZjzh() {
        return zjzh;
    }

    public void setZjzh(String zjzh) {
        this.zjzh = zjzh;
    }

    public String getZjmc() {
        return zjmc;
    }

    public void setZjmc(String zjmc) {
        this.zjmc = zjmc;
    }
}
