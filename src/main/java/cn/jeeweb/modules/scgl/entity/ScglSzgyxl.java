package cn.jeeweb.modules.scgl.entity;

import cn.jeeweb.core.common.entity.AbstractEntity;
import cn.jeeweb.modules.sys.entity.User;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.FieldFill;
import com.baomidou.mybatisplus.enums.IdType;

import java.util.Date;

/**
* @Description:    生产管理-设置工艺小类
* @Author:         杜凯之
* @CreateDate:     2018/9/11 16:47
* @Version:        1.0
*/
@TableName("T_SCGL_SZGYXL")
@SuppressWarnings("serial")
public class ScglSzgyxl extends AbstractEntity<String> {
    /** 主键 */
    @TableId(value = "ID", type = IdType.UUID)
    private String id;

    /** 工艺大类代码 */
    @TableField(value = "GYDLID")
    private String gydlid;
    /** 工艺小类代码 */
    @TableField(value = "GYXLID")
    private String gyxlid;
    /** 数量 */
    @TableField(value = "SL")
    private String sl;

    private String gydldm;
    private String gydlmc;
    private String gyxldm;
    private String gyxlmc;

    public String getGydldm() {
        return gydldm;
    }

    public void setGydldm(String gydldm) {
        this.gydldm = gydldm;
    }

    public String getGydlmc() {
        return gydlmc;
    }

    public void setGydlmc(String gydlmc) {
        this.gydlmc = gydlmc;
    }

    public String getGyxldm() {
        return gyxldm;
    }

    public void setGyxldm(String gyxldm) {
        this.gyxldm = gyxldm;
    }

    public String getGyxlmc() {
        return gyxlmc;
    }

    public void setGyxlmc(String gyxlmc) {
        this.gyxlmc = gyxlmc;
    }

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

    public String getGydlid() {
        return gydlid;
    }

    public void setGydlid(String gydlid) {
        this.gydlid = gydlid;
    }

    public String getGyxlid() {
        return gyxlid;
    }

    public void setGyxlid(String gyxlid) {
        this.gyxlid = gyxlid;
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
}
