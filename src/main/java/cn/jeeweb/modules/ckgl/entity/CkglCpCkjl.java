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
 * Dscription: 仓库管理 - 成品出库记录
 * @author : Kevin Du
 * @version : 1.0
 * @date : 2018/10/26 14:28
 */
@TableName("T_CKGL_CP_CKJL")
@SuppressWarnings("serial")
public class CkglCpCkjl extends AbstractEntity<String> {
    /** 主键 */
    @TableId(value = "ID", type = IdType.UUID)
    private String id;

    /** 计划ID */
    @TableField(value = "JHID")
    private String jhid;
    /** 计划名称 */
    @TableField(value = "JHBH")
    private String jhbh;
    /** 零部件ID */
    @TableField(value = "LBJID")
    private String lbjid;
    /** 零部件名称 */
    @TableField(value = "LBJMC")
    private String lbjmc;
    /** 零部件图号 */
    @TableField(value = "LBJTH")
    private String lbjth;
    /** 入库数量 */
    @TableField(value = "RKSL")
    private String rksl;
    /** 日期 */
    @TableField(value = "RQ")
    private String rq;

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

    public String getJhid() {
        return jhid;
    }

    public void setJhid(String jhid) {
        this.jhid = jhid;
    }

    public String getJhbh() {
        return jhbh;
    }

    public void setJhbh(String jhbh) {
        this.jhbh = jhbh;
    }

    public String getLbjid() {
        return lbjid;
    }

    public void setLbjid(String lbjid) {
        this.lbjid = lbjid;
    }

    public String getLbjmc() {
        return lbjmc;
    }

    public void setLbjmc(String lbjmc) {
        this.lbjmc = lbjmc;
    }

    public String getLbjth() {
        return lbjth;
    }

    public void setLbjth(String lbjth) {
        this.lbjth = lbjth;
    }

    public String getRksl() {
        return rksl;
    }

    public void setRksl(String rksl) {
        this.rksl = rksl;
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
}
