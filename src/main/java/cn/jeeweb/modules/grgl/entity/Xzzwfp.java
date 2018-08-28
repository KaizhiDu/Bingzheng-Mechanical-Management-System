package cn.jeeweb.modules.grgl.entity;

import cn.jeeweb.core.common.entity.AbstractEntity;
import cn.jeeweb.modules.sys.entity.User;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.FieldFill;
import com.baomidou.mybatisplus.enums.IdType;

import java.util.Date;

/**
 * @Description:    员工薪资职位分配
 * @Author:         杜凯之
 * @CreateDate:     2018/8/18 15:06
 * @Version:        1.0
 */
@TableName("T_GRGL_XZZWFP")
@SuppressWarnings("serial")
public class Xzzwfp extends AbstractEntity<String> {
    /** 主键 */
    @TableId(value = "ID", type = IdType.UUID)
    private String id;
    /** 员工ID */
    @TableField(value = "YGID")
    private String ygid;
    /** 职务ID */
    @TableField(value = "ZWID")
    private String zwid;
    /** 底薪 */
    @TableField(value = "DX")
    private String dx;
    /** 时薪 */
    @TableField(value = "SX")
    private String sx;
    /** 餐补 */
    @TableField(value = "CB")
    private String cb;
    /** 房补 */
    @TableField(value = "FB")
    private String fb;
    /** 补贴 */
    @TableField(value = "BT")
    private String bt;
    /** 保险 */
    @TableField(value = "BX")
    private String bx;
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

    public String getYgid() {
        return ygid;
    }

    public void setYgid(String ygid) {
        this.ygid = ygid;
    }

    public String getZwid() {
        return zwid;
    }

    public void setZwid(String zwid) {
        this.zwid = zwid;
    }

    public String getDx() {
        return dx;
    }

    public void setDx(String dx) {
        this.dx = dx;
    }

    public String getSx() {
        return sx;
    }

    public void setSx(String sx) {
        this.sx = sx;
    }

    public String getCb() {
        return cb;
    }

    public void setCb(String cb) {
        this.cb = cb;
    }

    public String getFb() {
        return fb;
    }

    public void setFb(String fb) {
        this.fb = fb;
    }

    public String getBt() {
        return bt;
    }

    public void setBt(String bt) {
        this.bt = bt;
    }

    public String getBx() {
        return bx;
    }

    public void setBx(String bx) {
        this.bx = bx;
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
}
