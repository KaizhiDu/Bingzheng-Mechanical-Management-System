package cn.jeeweb.modules.sbgl.entity;

import cn.jeeweb.core.common.entity.AbstractEntity;
import cn.jeeweb.modules.sys.entity.User;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.FieldFill;
import com.baomidou.mybatisplus.enums.IdType;

import java.util.Date;

/**
 * Dscription: 设备管理 - 包工设备占用
 * @author : Kevin Du
 * @version : 1.0
 * @date : 2018/11/2 14:04
 */
@TableName("T_SBGL_BGSBZY")
@SuppressWarnings("serial")
public class SbglBgsbzy extends AbstractEntity<String> {
    /** 主键 */
    @TableId(value = "ID", type = IdType.UUID)
    private String id;

    /** 日期 */
    @TableField(value = "BGID")
    private String bgid;
    /** 设备ID */
    @TableField(value = "SBID")
    private String sbid;
    /** 日工设备ID */
    @TableField(value = "BGSBID")
    private String bgsbid;

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

    public String getBgid() {
        return bgid;
    }

    public void setBgid(String bgid) {
        this.bgid = bgid;
    }

    public String getSbid() {
        return sbid;
    }

    public void setSbid(String sbid) {
        this.sbid = sbid;
    }

    public String getBgsbid() {
        return bgsbid;
    }

    public void setBgsbid(String bgsbid) {
        this.bgsbid = bgsbid;
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
