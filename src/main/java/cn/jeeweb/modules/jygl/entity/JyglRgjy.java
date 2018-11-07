package cn.jeeweb.modules.jygl.entity;

import cn.jeeweb.core.common.entity.AbstractEntity;
import cn.jeeweb.modules.sys.entity.User;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.FieldFill;
import com.baomidou.mybatisplus.enums.IdType;

import java.util.Date;

/**
 * Dscription: 检验管理 - 日工检验
 * @author : Kevin Du
 * @version : 1.0
 * @date : 2018/9/30 15:36
 */
@TableName("T_SCGL_RCRWFP_RG_RW")
@SuppressWarnings("serial")
public class JyglRgjy extends AbstractEntity<String> {
    /** 主键 */
    @TableId(value = "ID", type = IdType.UUID)
    private String id;
    /** 零部件工艺编制ID */
    @TableField(value = "LJGYBZID")
    private String ljgybzid;
    /** 分配设备ID */
    @TableField(value = "FPSBID")
    private String fpsbid;
    /** 应完成量 */
    @TableField(value = "YWCL")
    private String ywcl;
    /** 实际完成量 */
    @TableField(value = "SJWCL")
    private String sjwcl;
    /** 报废量 */
    @TableField(value = "BFL")
    private String bfl;
    /** 排序 */
    @TableField(value = "PX")
    private int px;

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

    public String getLjgybzid() {
        return ljgybzid;
    }

    public void setLjgybzid(String ljgybzid) {
        this.ljgybzid = ljgybzid;
    }

    public String getFpsbid() {
        return fpsbid;
    }

    public void setFpsbid(String fpsbid) {
        this.fpsbid = fpsbid;
    }

    public String getYwcl() {
        return ywcl;
    }

    public void setYwcl(String ywcl) {
        this.ywcl = ywcl;
    }

    public String getSjwcl() {
        return sjwcl;
    }

    public void setSjwcl(String sjwcl) {
        this.sjwcl = sjwcl;
    }

    public int getPx() {
        return px;
    }

    public void setPx(int px) {
        this.px = px;
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

    public String getBfl() {
        return bfl;
    }

    public void setBfl(String bfl) {
        this.bfl = bfl;
    }
}
