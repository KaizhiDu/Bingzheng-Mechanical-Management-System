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
 * Dscription: 仓库管理 - 未完成工序
 * @author : Kevin Du
 * @version : 1.0
 * @date : 2018/10/11 13:35
 */
@TableName("T_CKGL_WWCGX")
@SuppressWarnings("serial")
public class CkglWwcgx extends AbstractEntity<String> {
    /** 主键 */
    @TableId(value = "ID", type = IdType.UUID)
    private String id;

    /** 半成品ID */
    @TableField(value = "BCPID")
    private String bcpid;
    /** 工艺小类名称 */
    @TableField(value = "GYXLMC")
    private String gyxlmc;
    /** 数量 */
    @TableField(value = "SL")
    private String sl;
    /** 未完成数量 */
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBcpid() {
        return bcpid;
    }

    public void setBcpid(String bcpid) {
        this.bcpid = bcpid;
    }

    public String getGyxlmc() {
        return gyxlmc;
    }

    public void setGyxlmc(String gyxlmc) {
        this.gyxlmc = gyxlmc;
    }

    public String getSl() {
        return sl;
    }

    public void setSl(String sl) {
        this.sl = sl;
    }

    public String getWrksl() {
        return wrksl;
    }

    public void setWrksl(String wrksl) {
        this.wrksl = wrksl;
    }

    public String getSysl() {
        return sysl;
    }

    public void setSysl(String sysl) {
        this.sysl = sysl;
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
